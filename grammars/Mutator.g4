grammar Mutator;

// Parser rules
mutFile : 		command* | EOF ;

command :		source | addSource | removeSource | listSource
				| test | addTest   | removeTest   | listTest
				| use
				| strain
				| mutate
				| report ;

source :		SOURCE COLON fileList ;
test :			TEST COLON fileList ;
use :			USE fileList ;

addSource :		ADD SOURCE fileList ;
removeSource :	REMOVE SOURCE fileList ;
addTest :		ADD TEST fileList ;
removeTest :	REMOVE TEST fileList ;
listSource :	LIST SOURCE ;
listTest :		LIST TEST ;

strain :		STRAIN ID mutate+ END ;

mutate :		MUTATE (idList | symbolList TO symbolList) ;

report :		REPORT (LAST | ALL) fileList?;

idList :		ID (COMMA ID)* ;
symbolList :	SYMBOL (COMMA SYMBOL)* ;
fileList :		(FILEPATH | SYMBOL) (COMMA (FILEPATH | SYMBOL))* ;

/* Lexical rules */

// Separators and operators
COMMA :			',' ;
COLON :			':' ;

// Reserved words
SOURCE :		'source' ;
TEST :			'test' ;
USE :			'use' ;
ADD :			'add' ;
REMOVE :		'remove' ;
LIST :			'list' ;
STRAIN :		'strain' ;
END :			'end' ;
MUTATE :		'mutate' ;
TO :			'to' ;
REPORT :		'report' ;
LAST :			'last' ;
ALL :			'all' ;

// The rest
ID : 			LETTER (LETTER|DIGIT|'_')* ;
// Supposed to match any file or directory name
FILEPATH :		(DIRNAME SLASH)* DIRNAME | (DIRNAME)* FILENAME ;
DIRNAME :		((LETTER|DIGIT|UNDERSCORE)+ | (DOT | DOT DOT)) SLASH? ;
FILENAME : 		(LETTER|DIGIT|UNDERSCORE|DOT)+ ;

WS :			([ \t\r\n] | COMMENT)+ -> skip ;
COMMENT :		'#' .*? ('\n'|EOF) ;

// Will probably change, should at least be able to match +, -, &&, ||, etc.
// Language could/should be expanded to allow other types of mutations
SYMBOL :		(~([ \t\r\n#,:]))+ ;

fragment
UNDERSCORE :	'_' ;
fragment
DOT :			'.' ;
fragment
SLASH :			'/' ;
fragment
STAR :			'*' ;

fragment
LETTER :		[A-Za-z] ;

fragment
DIGIT :			[0-9] ;
