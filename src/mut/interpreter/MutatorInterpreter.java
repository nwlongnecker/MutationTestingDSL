/**
 * 
 */
package mut.interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mut.files.FileReader;
import mut.files.InMemoryFileManager;
import mut.lexparse.LexerParserFactory;
import mut.lexparse.MutatorParser;
import mut.mutator.MutationRunner;
import mut.statistics.FileStatistics;
import mut.statistics.StatisticsCollector;
import mut.statistics.Survivor;
import mut.util.Msg;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Mutator interpreter. Walks the tree and runs the appropriate command
 * @author Nathan
 */
public class MutatorInterpreter extends mut.lexparse.MutatorBaseVisitor<Collection<String>> {
	
	private final InterpreterState state;
	private final Msg msg;
	
	public MutatorInterpreter(InterpreterState state) {
		this.state = state;
		this.msg = state.getMsg();
	}

	@Override
	public Collection<String> visitMutFile(MutatorParser.MutFileContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitCommand(MutatorParser.CommandContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitSource(MutatorParser.SourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.setSourceFiles(files);
		return null;
	}
	
	@Override
	public Collection<String> visitTest(MutatorParser.TestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.setTestFiles(files);
		return null;
	}
	
	@Override
	public Collection<String> visitUse(MutatorParser.UseContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.addUseFiles(files);
		for (String filepath : files) {
			String useCode = FileReader.readFile(filepath, msg);
			MutatorParser parser = LexerParserFactory.makeParser(new ANTLRInputStream(useCode));
			ParserRuleContext tree = parser.mutFile();
			tree.accept(this);
		}
		return null;
	}
	
	@Override
	public Collection<String> visitAddSource(MutatorParser.AddSourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.addSourceFiles(files);
		return null;
	}
	
	@Override
	public Collection<String> visitRemoveSource(MutatorParser.RemoveSourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.removeSourceFiles(files);
		return null;
	}
	
	@Override
	public Collection<String> visitAddTest(MutatorParser.AddTestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.addTestFiles(files);
		return null;
	}
	
	@Override
	public Collection<String> visitRemoveTest(MutatorParser.RemoveTestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.removeTestFiles(files);
		return null;
	}

	@Override
	public Collection<String> visitListSource(MutatorParser.ListSourceContext ctx) {
		msg.printList("Sources: ", state.getSourceFiles());
		return null;
	}
	
	@Override
	public Collection<String> visitListTest(MutatorParser.ListTestContext ctx) {
		msg.printList("Tests: ", state.getTestFiles());
		return null;
	}
	
	@Override
	public Collection<String> visitStrain(MutatorParser.StrainContext ctx) {
		state.getSymbolTable().add(ctx.ID().getText(), ctx.mutate());
		return null;
	}
	
	@Override
	public Collection<String> visitMutate(MutatorParser.MutateContext ctx) {
		// If it is invoking a strain
		if (ctx.idList() != null) {
			Collection<String> strains = ctx.idList().accept(this);
			for (String strain : strains) {
				List<MutatorParser.MutateContext> mutations = state.getSymbolTable().get(strain);
				for (MutatorParser.MutateContext mutate : mutations) {
					mutate.accept(this);
				}
			}
		} else {
			// If its a standard mutation node
			// Get the mutation list
			Collection<String> mutateFrom = ctx.symbolList(0).accept(this);
			Collection<String> mutateTo = ctx.symbolList(1).accept(this);
			
			// Do the mutations
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			if (compiler == null) {
				throw new RuntimeException("No system compiler provided, try running with jdk instead of jre");
			}
			InMemoryFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null), state.getFileSystem(), msg);

			StatisticsCollector statisticsCollector = new StatisticsCollector();
			state.getStatistics().add(0, statisticsCollector);
			MutationRunner runner = new MutationRunner(state, mutateFrom, mutateTo, fileManager, statisticsCollector);
			// If we are testing, keep everything single threaded for predictability
			if (InterpreterState.TESTING) {
				runner.run();
			} else {
				// Otherwise, multithread for better performance
				runner.start();
			}
		}
		
		return null;
	}
	
	@Override
	public Collection<String> visitReport(MutatorParser.ReportContext ctx) {
		if (ctx.fileList() != null && !ctx.fileList().isEmpty()) {
			Collection<String> files = ctx.fileList().accept(this);
			// Report all for this file
			if (ctx.ALL() != null) {
				int total = 0;
				int totalSurvived = 0;
				int totalKilled = 0;
				int totalStillborn = 0;
				for (String filename : files) {
					for (StatisticsCollector sc : state.getStatistics()) {
						FileStatistics fs = sc.get(filename);
						total += fs.getTotal();
						totalSurvived += fs.getSurvived();
						totalKilled += fs.getKilled();
						totalStillborn += fs.getStillborn();
						for (Survivor survivor : fs.getSurvivors()) {
							msg.msgln(filename + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
						}
					}
				}
				report(total, totalSurvived, totalKilled, totalStillborn);
			} else {
				// Or report the last mutation for this file
				int total = 0;
				int totalSurvived = 0;
				int totalKilled = 0;
				int totalStillborn = 0;
				for (String filename : files) {
					FileStatistics fs = state.getStatistics().get(0).get(filename);
					total += fs.getTotal();
					totalSurvived += fs.getSurvived();
					totalKilled += fs.getKilled();
					totalStillborn += fs.getStillborn();
					for (Survivor survivor : fs.getSurvivors()) {
						msg.msgln(filename + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
					}
				}
				report(total, totalSurvived, totalKilled, totalStillborn);
			}
		} else {
			// Report all
			if (ctx.ALL() != null) {
				int total = 0;
				int totalSurvived = 0;
				int totalKilled = 0;
				int totalStillborn = 0;
				for (StatisticsCollector sc : state.getStatistics()) {
					total += sc.getTotal();
					totalSurvived += sc.getSurvived();
					totalKilled += sc.getKilled();
					totalStillborn += sc.getStillborn();
				}
				report(total, totalSurvived, totalKilled, totalStillborn);
			} else {
				// Or report the last one
				StatisticsCollector sc = state.getStatistics().get(0);
				report(sc.getTotal(), sc.getSurvived(), sc.getKilled(), sc.getStillborn());
			}
		}
		return null;
	}

	private void report(int total, int survived, int killed, int stillborn) {
		int percentSurvived = survived * 100 / total;
		int percentKilled = killed * 100 / total;
		int percentStillborn = stillborn * 100 / total;
		msg.msgln("Total survived: " + survived + " / " + total + " = " + percentSurvived + "%");
		msg.msgln("Total killed: " + killed + " / " + total + " = " + percentKilled + "%");
		msg.msgln("Total stillborn: " + stillborn + " / " + total + " = " + percentStillborn + "%");
	}
	
	@Override
	public Collection<String> visitIdList(MutatorParser.IdListContext ctx) {
		Collection<String> ids = new HashSet<String>();
		for(TerminalNode id : ctx.ID()) {
			ids.add(id.getText());
		}
		return ids;
	}
	
	@Override
	public Collection<String> visitSymbolList(MutatorParser.SymbolListContext ctx) {
		Collection<String> symbols = new HashSet<String>();
		for(TerminalNode symbol : ctx.SYMBOL()) {
			symbols.add(symbol.getText());
		}
		return symbols;
	}
	
	@Override
	public Collection<String> visitFileList(MutatorParser.FileListContext ctx) {
		List<TerminalNode> files = ctx.FILEPATH();
		Collection<String> fileList = new HashSet<String>();
		for (TerminalNode file : files) {
			File f = new File(file.getText());
			if(f.exists()) {
				fileList.add(file.getText());
			} else {
				msg.msgln("File " + file.getText() + " does not exist!");
			}
		}
		for (TerminalNode symbol : ctx.SYMBOL()) {
			String regex = symbol.getText();
			fileList.addAll(matchDirRegex(".", regex));
		}
		return explodeDirs(fileList);
	}
	
	private Collection<String> matchDirRegex(String currentDir, String regex) {
		Collection<String> fileList = new HashSet<String>();
		int slashIndex = regex.indexOf('/');
		System.out.println("Regex: " + regex + " first index of / is " + slashIndex);
		int backslashIndex = regex.indexOf("\\");
		System.out.println("Regex: " + regex + " first index of \\ is " + backslashIndex);
		String nextDirRegex;
		String remainingRegex;
		if (slashIndex >= 0 || backslashIndex >= 0) {
			if ((slashIndex < backslashIndex || backslashIndex < 0) && slashIndex >= 0) {
				nextDirRegex = regex.substring(0, slashIndex);
				remainingRegex = regex.substring(slashIndex + 1);
			} else  {
				nextDirRegex = regex.substring(0, backslashIndex);
				remainingRegex = regex.substring(backslashIndex + 1);
			}
		} else {
			return matchRegexInDir(currentDir, regex);
		}
		Collection<String> matchedDirs = matchRegexInDir(currentDir, nextDirRegex);
		for(String dir : matchedDirs) {
			for (String file : matchDirRegex(currentDir + "/" + dir, remainingRegex)) {
				fileList.add(dir + "/" + file);
			}
		}

		return fileList;
	}
	
	private Collection<String> matchRegexInDir(String dir, String regex) {
		Collection<String> fileList = new HashSet<String>();
		File f = new File(dir);
		Pattern p = Pattern.compile(regex.replace("*", ".*"));
		List<String> list = Arrays.asList(f.list());
		for(int i = 0; i < list.size(); i++) {
			if (p.matcher(list.get(i)).matches()) {
				System.out.println("Matched " + list.get(i));
				fileList.add(list.get(i));
			}
		}
		return fileList;
	}
	
	private Collection<String> explodeDirs(Collection<String> files) {
		Collection<String> filesToAdd = new HashSet<String>();
		for (String file : files) {
			File f = new File(file);
			if (f.isDirectory()) {
				List<String> list = Arrays.asList(f.list());
				for(int i = 0; i < list.size(); i++) {
					String dir = file;
					if (!file.endsWith("/")) {
						dir = file + "/";
					}
					list.set(i, dir + list.get(i));
				}
				filesToAdd.addAll(explodeDirs(list));
			} else if (f.isFile()) {
				filesToAdd.add(file);
			} else {
				msg.err(file + " is not a valid file!");
			}
		}
		return filesToAdd;
	}
}
