// Generated from Mutator.g4 by ANTLR 4.5
package mut.lexparse;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MutatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MutatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MutatorParser#mutFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMutFile(MutatorParser.MutFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(MutatorParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(MutatorParser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(MutatorParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#use}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse(MutatorParser.UseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#addSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSource(MutatorParser.AddSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#removeSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveSource(MutatorParser.RemoveSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#addTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddTest(MutatorParser.AddTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#removeTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveTest(MutatorParser.RemoveTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#listSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListSource(MutatorParser.ListSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#listTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListTest(MutatorParser.ListTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#strain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrain(MutatorParser.StrainContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#mutate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMutate(MutatorParser.MutateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(MutatorParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#symbolList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolList(MutatorParser.SymbolListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MutatorParser#fileList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileList(MutatorParser.FileListContext ctx);
}