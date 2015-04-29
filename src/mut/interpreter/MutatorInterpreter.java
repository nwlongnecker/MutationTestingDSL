/**
 * 
 */
package mut.interpreter;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mut.files.FileReader;
import mut.files.InMemoryFileManager;
import mut.files.InMemoryFileSystem;
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
			InMemoryFileSystem fileSystem = state.getFileSystem();
			try {
				fileSystem = (InMemoryFileSystem) state.getFileSystem().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			InMemoryFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null), fileSystem, msg);

			StatisticsCollector statisticsCollector = new StatisticsCollector();
			state.getStatistics().add(0, statisticsCollector);
			MutationRunner runner = new MutationRunner(state, mutateFrom, mutateTo, fileManager, statisticsCollector);
			// If we are testing, keep everything single threaded for predictability
			if (InterpreterState.TESTING) {
				runner.run();
			} else {
				// Otherwise, multithread for better performance
				state.addThread(runner);
				runner.start();
			}
		}
		
		return null;
	}
	
	@Override
	public Collection<String> visitReport(MutatorParser.ReportContext ctx) {
		List<Thread> runningThreads = state.getRunningThreads();
		while (!runningThreads.isEmpty()) {
			try {
				runningThreads.remove(0).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		if (total == 0) {
			msg.msgln("No tests were run!");
			return;
		}
		double percentSurvived = round(survived * 100.0 / total, 2);
		double percentKilled = round(killed * 100.0 / total, 2);
		double percentStillborn = round(stillborn * 100.0 / total, 2);
		msg.msgln("Total survived: " + survived + " / " + total + " = " + percentSurvived + "%");
		msg.msgln("Total killed: " + killed + " / " + total + " = " + percentKilled + "%");
		msg.msgln("Total stillborn: " + stillborn + " / " + total + " = " + percentStillborn + "%");
		msg.msgln("Mutation score: " + round((killed * 100.0 / (total - stillborn)), 2) + "%");
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
 
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
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
			fileList.addAll(FileReader.matchDirRegex(".", regex));
		}
		return FileReader.explodeDirs(fileList);
	}
}
