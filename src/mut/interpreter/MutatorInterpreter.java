/**
 * 
 */
package mut.interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import mut.MutatorMainTest;
import mut.lexparse.MutatorBaseVisitor;
import mut.lexparse.MutatorParser;
import mut.runtime.MutatorRuntime;
import mut.utility.Utility;

/**
 * Mutator interpreter. Walks the tree and runs the appropriate command
 * @author Nathan
 */
public class MutatorInterpreter extends MutatorBaseVisitor<Collection<String>> {
	
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
		MutatorRuntime.printList("Sources: ", state.getSourceFiles());
		return null;
	}
	
	@Override
	public Collection<String> visitListTest(MutatorParser.ListTestContext ctx) {
		MutatorRuntime.printList("Tests: ", state.getTestFiles());
		return null;
	}
	
	@Override
	public Collection<String> visitStrain(MutatorParser.StrainContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitMutate(MutatorParser.MutateContext ctx) {
		// Do the mutations
		
		
//		// Find the test classes
//		JUnitCore junit = new JUnitCore();
//		Collection<Class<?>> classes = new HashSet<Class<?>>();
//		for (String file : state.getTestFiles()) {
//			Class<?> c = Utility.getClass(file);
//			if (c != null) {
//				classes.add(c);
//				MutatorRuntime.printMessage("Running " + file + " on mutated code");
//			}
//		}
//		//Run JUnit tests
//		if (!classes.isEmpty()) {
//			Result result = junit.run(classes.toArray(new Class<?>[0]));
//			MutatorRuntime.reportResults(result);
//		}
		return null;//visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitIdList(MutatorParser.IdListContext ctx) { return visitChildren(ctx); }
	
	@Override
	public Collection<String> visitSymbolList(MutatorParser.SymbolListContext ctx) { return visitChildren(ctx); }
	
	@Override
	public Collection<String> visitFileList(MutatorParser.FileListContext ctx) {
		List<TerminalNode> files = ctx.FILEPATH();
		Collection<String> fileList = new HashSet<String>();
		for (TerminalNode file : files) {
			File f = new File(file.getText());
//			if(f.exists()) {
				fileList.add(file.getText());
//			} else {
//				MutatorRuntime.printMessage("File " + file.getText() + " does not exist!");
//			}
		}
		return fileList;
	}
}
