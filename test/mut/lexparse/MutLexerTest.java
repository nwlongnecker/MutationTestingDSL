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
	
	@Test
	public void recognizeReservedWords()
	{
		makeLexer("source test add remove list use strain end mutate to");
		checkNextToken(SOURCE, "source");
		checkNextToken(TEST, "test");
		checkNextToken(ADD, "add");
		checkNextToken(REMOVE, "remove");
		checkNextToken(LIST, "list");
		checkNextToken(USE, "use");
		checkNextToken(STRAIN, "strain");
		checkNextToken(END, "end");
		checkNextToken(MUTATE, "mutate");
		checkNextToken(TO, "to");
	}
	
	@Test
	public void recognizeSeparators()
	{
		makeLexer(",:");
		checkNextToken(COMMA, ",");
		checkNextToken(COLON, ":");
		nextToken();
		assertEquals(EOF, t.getType());
	}
	
	@Test
	public void recognizeSimpleIDs()
	{
		makeLexer("hello Test1 var_1 isOpen");
		checkNextToken(ID, "hello");
		checkNextToken(ID, "Test1");
		checkNextToken(ID, "var_1");
		checkNextToken(ID, "isOpen");
	}
	
	@Test
	public void recognizeFilenames()
	{
		makeLexer("hello.txt Test1/ var_1/go.cmd ../../isOpen . ./ ../ ..");
		checkNextToken(FILEPATH, "hello.txt");
		checkNextToken(DIRNAME, "Test1/");
		checkNextToken(FILEPATH, "var_1/go.cmd");
		checkNextToken(FILEPATH, "../../isOpen");
		checkNextToken(DIRNAME, ".");
		checkNextToken(DIRNAME, "./");
		checkNextToken(DIRNAME, "../");
		checkNextToken(DIRNAME, "..");
	}
	
	@Test
	public void recognizeSymbols()
	{
		makeLexer("~!@$%^&*()_-`= +\\|[]{};'./<>?\"");
		checkNextToken(SYMBOL, "~!@$%^&*()_-`=");
		checkNextToken(SYMBOL, "+\\|[]{};'./<>?\"");
	}
	
	@Test
	public void emptyStringReturnsEOF()
	{
		makeLexer("");
		nextToken();
		assertEquals(EOF, t.getType());
	}
	
	@Test
	public void testIgnoreWhitespace() {
		makeLexer("\n ,\t\r");
		checkNextToken(COMMA, ",");
		nextToken();
		assertEquals(EOF, t.getType());
	}

	@Test
	public void testIgnoreComment() {
		makeLexer("# comment");
		nextToken();
		assertEquals(EOF, t.getType());
	}
	
	@Test
	public void testCommentThenToken() {
		makeLexer("mutate#myComment\n,\t");
		checkNextToken(MUTATE, "mutate");
		checkNextToken(COMMA, ",");
		nextToken();
		assertEquals(EOF, t.getType());
	}
	
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
