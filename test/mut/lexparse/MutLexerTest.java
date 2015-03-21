/*******************************************************************************
 * Copyright (c) 2015 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Used in CS4533/CS544 at Worcester Polytechnic Institute
 *******************************************************************************/

package mut.lexparse;

import org.antlr.v4.runtime.*;
import org.junit.Test;

import static org.antlr.v4.runtime.Token.EOF;
import static org.junit.Assert.*;
import static mut.lexparse.MutatorLexer.*;
import mut.utility.MutatorFactory;

/**
 * Description
 * @version Jan 26, 2015
 */
public class MutLexerTest
{
	private MutatorLexer lexer;
	private Token t;
	
//	@Test
//	public void recognizeReservedWords()
//	{
//		makeLexer("boolean false fi if input int print program true float do od mod div");
//		checkNextToken(BOOLEAN, "boolean");
//		checkNextToken(FALSE, "false");
//		checkNextToken(FI, "fi");
//		checkNextToken(IF, "if");
//		checkNextToken(INPUT, "input");
//		checkNextToken(INT, "int");
//		checkNextToken(PRINT, "print");
//		checkNextToken(PROGRAM, "program");
//		checkNextToken(TRUE, "true");
//		checkNextToken(FLOAT, "float");
//		checkNextToken(DO, "do");
//		checkNextToken(OD, "od");
//		checkNextToken(MOD, "mod");
//		checkNextToken(DIV, "div");
//	}
//	
//	@Test
//	public void recognizeSeparators()
//	{
//		makeLexer(";()::,|&");
//		checkNextToken(SEMICOLON, ";");
//		checkNextToken(LPAR, "(");
//		checkNextToken(RPAR, ")");
//		checkNextToken(GUARD, "::");
//		checkNextToken(COMMA, ",");
//		checkNextToken(PIPE, "|");
//		checkNextToken(AMP, "&");
//		nextToken();
//		assertEquals(EOF, t.getType());
//	}
	
//	@Test
//	public void recognizeAssignOp()
//	{
//		makeLexer("<-");
//		checkNextToken(ASSIGN, "<-");
//	}
	
	// Helper methods
	private void makeLexer(String text)
	{
		lexer = MutatorFactory.makeLexer(new ANTLRInputStream(text));
	}

	private void nextToken()
	{
		t = lexer.nextToken();
	}
	
	private void checkNextToken(int tokenType, String tokenText)
	{
		nextToken();
		assertEquals(tokenType, t.getType());
		assertEquals(tokenText, t.getText());
	}
}
