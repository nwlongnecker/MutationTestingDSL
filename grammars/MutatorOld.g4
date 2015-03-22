
grammar MutatorOld;
 
// Parser rules
mutFile : 			(strainsFile | script) EOF ;
script :			mutantZero killWith alienStrains? strain* mutation+ ;

strainsFile :	strain+ ;

mutantZero :	MUTANT_ZERO fileList ;
killWith :		KILL_WITH fileList ;
alienStrains :	ALIEN_STRAINS fileList ;

strain :		STRAIN ID mutation+ END ;

mutation :		MUTATE (idList | symbolList TO symbolList) ;

idList :		ID (COMMA ID)* ;
symbolList :	SYMBOL (COMMA SYMBOL)* ;
fileList :		FILENAME (COMMA FILENAME)* ;

/* Lexical rules */

// Separators and operators
COMMA :			',' ;

// Reserved words
MUTANT_ZERO :	'mutant zero:' ;
KILL_WITH :		'kill with:' ;
ALIEN_STRAINS :	'alien strains:' ;
STRAIN :		'strain' ;
END :			'end' ;
MUTATE :		'mutate' ;
TO :			'to' ;

// The rest
ID : 			LETTER (LETTER|DIGIT|'_'|'?')* ;
// Supposed to match any file or directory name
FILENAME : 		LETTER (LETTER|DIGIT|'_'|'?'|'.'|'/')* ;
// Will probably change, should at least be able to match +, -, &&, ||, etc.
// Language could/should be expanded to allow other types of mutations
SYMBOL :		[~!&*+/|<>=\-]+ ;

WS :			([ \t\r\n] | COMMENT)+ -> skip ;
COMMENT :		'#' .*? ('\n'|EOF);

fragment
LETTER :		[A-Za-z] ;

fragment
DIGIT :		[0-9] ;
