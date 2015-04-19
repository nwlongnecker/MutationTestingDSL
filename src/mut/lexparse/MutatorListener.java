// Generated from Mutator.g4 by ANTLR 4.5
package mut.lexparse;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MutatorParser}.
 */
public interface MutatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MutatorParser#mutFile}.
	 * @param ctx the parse tree
	 */
	void enterMutFile(MutatorParser.MutFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#mutFile}.
	 * @param ctx the parse tree
	 */
	void exitMutFile(MutatorParser.MutFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(MutatorParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(MutatorParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(MutatorParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(MutatorParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(MutatorParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(MutatorParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#use}.
	 * @param ctx the parse tree
	 */
	void enterUse(MutatorParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#use}.
	 * @param ctx the parse tree
	 */
	void exitUse(MutatorParser.UseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#addSource}.
	 * @param ctx the parse tree
	 */
	void enterAddSource(MutatorParser.AddSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#addSource}.
	 * @param ctx the parse tree
	 */
	void exitAddSource(MutatorParser.AddSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#removeSource}.
	 * @param ctx the parse tree
	 */
	void enterRemoveSource(MutatorParser.RemoveSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#removeSource}.
	 * @param ctx the parse tree
	 */
	void exitRemoveSource(MutatorParser.RemoveSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#addTest}.
	 * @param ctx the parse tree
	 */
	void enterAddTest(MutatorParser.AddTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#addTest}.
	 * @param ctx the parse tree
	 */
	void exitAddTest(MutatorParser.AddTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#removeTest}.
	 * @param ctx the parse tree
	 */
	void enterRemoveTest(MutatorParser.RemoveTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#removeTest}.
	 * @param ctx the parse tree
	 */
	void exitRemoveTest(MutatorParser.RemoveTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#listSource}.
	 * @param ctx the parse tree
	 */
	void enterListSource(MutatorParser.ListSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#listSource}.
	 * @param ctx the parse tree
	 */
	void exitListSource(MutatorParser.ListSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#listTest}.
	 * @param ctx the parse tree
	 */
	void enterListTest(MutatorParser.ListTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#listTest}.
	 * @param ctx the parse tree
	 */
	void exitListTest(MutatorParser.ListTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#strain}.
	 * @param ctx the parse tree
	 */
	void enterStrain(MutatorParser.StrainContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#strain}.
	 * @param ctx the parse tree
	 */
	void exitStrain(MutatorParser.StrainContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#mutate}.
	 * @param ctx the parse tree
	 */
	void enterMutate(MutatorParser.MutateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#mutate}.
	 * @param ctx the parse tree
	 */
	void exitMutate(MutatorParser.MutateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(MutatorParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(MutatorParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#symbolList}.
	 * @param ctx the parse tree
	 */
	void enterSymbolList(MutatorParser.SymbolListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#symbolList}.
	 * @param ctx the parse tree
	 */
	void exitSymbolList(MutatorParser.SymbolListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorParser#fileList}.
	 * @param ctx the parse tree
	 */
	void enterFileList(MutatorParser.FileListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorParser#fileList}.
	 * @param ctx the parse tree
	 */
	void exitFileList(MutatorParser.FileListContext ctx);
}