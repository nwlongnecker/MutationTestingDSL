/**
 * 
 */
package mut.interpreter;

import java.util.Collection;

import mut.lexparse.MutatorBaseVisitor;
import mut.lexparse.MutatorParser;
import mut.runtime.MutatorRuntime;

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
	public Collection<String> visitMutFile(MutatorParser.MutFileContext ctx) { return visitChildren(ctx); }
	
	@Override
	public Collection<String> visitCommand(MutatorParser.CommandContext ctx) { return visitChildren(ctx); }
	
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
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitAddSource(MutatorParser.AddSourceContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitRemoveSource(MutatorParser.RemoveSourceContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitAddTest(MutatorParser.AddTestContext ctx) {
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitRemoveTest(MutatorParser.RemoveTestContext ctx) {
		return visitChildren(ctx);
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
		return visitChildren(ctx);
	}
	
	@Override
	public Collection<String> visitIdList(MutatorParser.IdListContext ctx) { return visitChildren(ctx); }
	
	@Override
	public Collection<String> visitSymbolList(MutatorParser.SymbolListContext ctx) { return visitChildren(ctx); }
	
	@Override
	public Collection<String> visitFileList(MutatorParser.FileListContext ctx) { return visitChildren(ctx); }
}
