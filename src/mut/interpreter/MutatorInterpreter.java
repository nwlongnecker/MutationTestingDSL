/**
 * 
 */
package mut.interpreter;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mut.files.FileReader;
import mut.files.InMemoryFileManager;
import mut.lexparse.LexerParserFactory;
import mut.lexparse.MutatorParser;
import mut.mutator.MutationRunner;
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
	
	public MutatorInterpreter(InterpreterState state) {
		this.state = state;
	}

	@Override
	public Collection<String> visitMutFile(MutatorParser.MutFileContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitCommand(MutatorParser.CommandContext ctx) {
		return visitChildren(ctx);
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
				Msg.err(file + " is not a valid file!");
			}
		}
		return filesToAdd;
	}
	
	@Override
	public Collection<String> visitSource(MutatorParser.SourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.setSourceFiles(explodeDirs(files));
		return null;
	}
	
	@Override
	public Collection<String> visitTest(MutatorParser.TestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.setTestFiles(explodeDirs(files));
		return null;
	}
	
	@Override
	public Collection<String> visitUse(MutatorParser.UseContext ctx) {
		Collection<String> files = explodeDirs(ctx.fileList().accept(this));
		state.addUseFiles(files);
		for (String filepath : files) {
			String useCode = FileReader.readFile(filepath);
			MutatorParser parser = LexerParserFactory.makeParser(new ANTLRInputStream(useCode));
			ParserRuleContext tree = parser.mutFile();
			tree.accept(this);
		}
		return null;
	}
	
	@Override
	public Collection<String> visitAddSource(MutatorParser.AddSourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.addSourceFiles(explodeDirs(files));
		return null;
	}
	
	@Override
	public Collection<String> visitRemoveSource(MutatorParser.RemoveSourceContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.removeSourceFiles(explodeDirs(files));
		return null;
	}
	
	@Override
	public Collection<String> visitAddTest(MutatorParser.AddTestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.addTestFiles(explodeDirs(files));
		return null;
	}
	
	@Override
	public Collection<String> visitRemoveTest(MutatorParser.RemoveTestContext ctx) {
		Collection<String> files = ctx.fileList().accept(this);
		state.removeTestFiles(explodeDirs(files));
		return null;
	}

	@Override
	public Collection<String> visitListSource(MutatorParser.ListSourceContext ctx) {
		Msg.printList("Sources: ", state.getSourceFiles());
		return null;
	}
	
	@Override
	public Collection<String> visitListTest(MutatorParser.ListTestContext ctx) {
		Msg.printList("Tests: ", state.getTestFiles());
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
			InMemoryFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));

			MutationRunner runner = new MutationRunner(state, mutateFrom, mutateTo, fileManager);
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
				Msg.msgln("File " + file.getText() + " does not exist!");
			}
		}
		return fileList;
	}
}
