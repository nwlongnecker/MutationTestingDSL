// Generated from MutatorOld.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MutatorOldParser}.
 */
public interface MutatorOldListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#mutFile}.
	 * @param ctx the parse tree
	 */
	void enterMutFile(MutatorOldParser.MutFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#mutFile}.
	 * @param ctx the parse tree
	 */
	void exitMutFile(MutatorOldParser.MutFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(MutatorOldParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(MutatorOldParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#strainsFile}.
	 * @param ctx the parse tree
	 */
	void enterStrainsFile(MutatorOldParser.StrainsFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#strainsFile}.
	 * @param ctx the parse tree
	 */
	void exitStrainsFile(MutatorOldParser.StrainsFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#mutantZero}.
	 * @param ctx the parse tree
	 */
	void enterMutantZero(MutatorOldParser.MutantZeroContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#mutantZero}.
	 * @param ctx the parse tree
	 */
	void exitMutantZero(MutatorOldParser.MutantZeroContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#killWith}.
	 * @param ctx the parse tree
	 */
	void enterKillWith(MutatorOldParser.KillWithContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#killWith}.
	 * @param ctx the parse tree
	 */
	void exitKillWith(MutatorOldParser.KillWithContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#alienStrains}.
	 * @param ctx the parse tree
	 */
	void enterAlienStrains(MutatorOldParser.AlienStrainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#alienStrains}.
	 * @param ctx the parse tree
	 */
	void exitAlienStrains(MutatorOldParser.AlienStrainsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#strain}.
	 * @param ctx the parse tree
	 */
	void enterStrain(MutatorOldParser.StrainContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#strain}.
	 * @param ctx the parse tree
	 */
	void exitStrain(MutatorOldParser.StrainContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#mutation}.
	 * @param ctx the parse tree
	 */
	void enterMutation(MutatorOldParser.MutationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#mutation}.
	 * @param ctx the parse tree
	 */
	void exitMutation(MutatorOldParser.MutationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(MutatorOldParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(MutatorOldParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#symbolList}.
	 * @param ctx the parse tree
	 */
	void enterSymbolList(MutatorOldParser.SymbolListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#symbolList}.
	 * @param ctx the parse tree
	 */
	void exitSymbolList(MutatorOldParser.SymbolListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MutatorOldParser#fileList}.
	 * @param ctx the parse tree
	 */
	void enterFileList(MutatorOldParser.FileListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MutatorOldParser#fileList}.
	 * @param ctx the parse tree
	 */
	void exitFileList(MutatorOldParser.FileListContext ctx);
}