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

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.junit.Test;

/**
 * Description
 * @version Jan 27, 2015
 */
public class MutParserTest
{
	private MutatorParser parser;
	private ParserRuleContext tree;
	
	@Test
	public void exampleprogram1()
	{
		doParse("source: source.java "
				+ "test: tests.java "
				+ "mutate && to || "
				+ "mutate && to || "
				+ "mutate || to && "
				+ "mutate >,<,<=,>= to >,<,<=,>=");
	}
	
	@Test
	public void exampleprogram2()
	{
		doParse("source: src/ "
				+ "test: test/ "
				+ "strain booleanLogic "
				+ "mutate && to || "
				+ "mutate || to && "
				+ "end "
				+ "mutate booleanLogic");
	}
	
	@Test
	public void exampleprogram3()
	{
		doParse("strain math "
				+ "mutate +,-,*,/ to +,-,*,/ "
				+ "end");
	}
	
	@Test
	public void exampleprogram4()
	{
		doParse("source: source.java, source2.java "
				+ "test: test1.java, test2.java "
				+ "use strains.mut "
				+ "mutate math ");
	}
	
	// Helper methods
	private void makeParser(String inputText)
	{
		parser = LexerParserFactory.makeParser(new ANTLRInputStream(inputText));
	}

	/**
	 * This method performs the parse. If you want to see what the tree looks like, use
	 * 		<br><code>System.out.println(tree.toStringTree());<code></br>
	 * after calling this method.
	 * @param inputText the text to parse
	 */
	private void doParse(String inputText)
	{
		makeParser(inputText);
		tree = parser.mutFile();
		assertTrue(true);
	}
	
	/**
	 * Call this routine to display the parse tree. Hit ENTER on the console to continue.
	 */
	@SuppressWarnings("unused")
	private void showTree()
	{
		System.out.println(tree.toStringTree());
		final List<String> ruleNames = Arrays.asList(parser.getRuleNames());
		final TreeViewer tv = new TreeViewer(ruleNames, tree);
		final JFrame frame = new JFrame("Parse Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tv);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        final BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
