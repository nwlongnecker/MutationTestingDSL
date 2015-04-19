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

/**
 * Dijkstra parser exception to report errors.
 * @version Jan 27, 2015
 */
public class MutatorParserException extends RuntimeException
{
	/**
	 * Constructor
	 * @param msg exception description
	 * @param cause reason for the exception (an exception that occurred in the lexer)
	 */
	public MutatorParserException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
