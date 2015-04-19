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
import mut.lexparse.*;

/**
 * The DijkstraFactory is responsible for constructing all, or parts of a Dijkstra
 * compiler. It is a standard Factory class.
 * 
 * @version Jan 26, 2015
 */
public class LexerParserFactory
{
	/**
	 * Create a Dijkstra lexer using the specified input stream containing the text
	 * @param inputText the ANTLRInputStream that contains the program text
	 * @return the Dijkstra lexer
	 */
	static public MutatorLexer makeLexer(ANTLRInputStream inputText) {
		final MutatorLexer lexer = new MutatorLexer(inputText);
		lexer.addErrorListener(
				new BaseErrorListener() {
					@Override
					public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
							int line, int charPositionInLine, String msg,
							RecognitionException e)
					{
						throw new MutatorLexerException(msg, e);
					}
				}
		);
		return lexer;
	}
	
	/**
	 * @param inputText
	 * @return
	 */
	static public MutatorParser makeParser(ANTLRInputStream inputText) {
		final MutatorLexer lexer = makeLexer(inputText);
		final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		final MutatorParser parser = new MutatorParser(tokenStream);
		parser.addErrorListener(
				new BaseErrorListener() {
					@Override
					public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
							int line, int charPositionInLine, String msg,
							RecognitionException e)
					{
						throw new MutatorParserException(msg, e);
					}
				}
		);
		return parser;
	}
}
