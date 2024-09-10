/* CMSC 430 Compiler Theory and Design
   Project 2
   Andrew France
   7/8/2024

   Project 2 Parser */

%{

#include <string>

using namespace std;

#include "listing.h"

int yylex();
void yyerror(const char* message);

%}

%define parse.error verbose

%token IDENTIFIER INT_LITERAL CHAR_LITERAL REAL_LITERAL HEX_LITERAL

%token ADDOP MULOP ANDOP RELOP OROP NOTOP REMOP EXPOP NEGOP ARROW

%token BEGIN_ CASE CHARACTER ELSE END ENDSWITCH FUNCTION INTEGER IS LIST OF OTHERS ELSIF ENDFOLD ENDIF FOLD IF LEFT REAL RIGHT THEN RETURNS SWITCH WHEN

%%

function:	
	function_header optional_variable body ;

function_header:	
	FUNCTION IDENTIFIER optional_parameters RETURNS type ';'
	| error ';' ;

type:
	INTEGER |
	REAL |
	CHARACTER ;
	
/* Implementation for parameters */

optional_parameters:
	parameters | %empty ;
	
parameters:
	parameters ',' parameter | parameter ;
	
parameter:
	IDENTIFIER ':' type ;
	
optional_variable:
	optional_variable variable |
	error ';' |
	%empty ;
    
variable:	
	IDENTIFIER ':' type IS statement ';' |
	IDENTIFIER ':' LIST OF type IS list ';' |
	IDENTIFIER error IS statement ';' ;

list:
	'(' expressions ')' ;

expressions:
	expressions ',' expression| 
	expression ;

body:
	BEGIN_ statement_ END ';' ;

statement_:
	statement ';' | error ';' ;
    
statement:
	expression |
	WHEN condition ',' expression ':' expression 
| SWITCH expression IS cases OTHERS ARROW statement ';' ENDSWITCH 
| IF condition THEN statement ';' elsif_opt ELSE statement ';' ENDIF 
| FOLD direction operator list_choice ENDFOLD | error statement ';';

/* direction, operator, and list_choice to support fold */

direction: 
	LEFT | RIGHT ;

operator:
	ADDOP | MULOP ;

list_choice:
	list | IDENTIFIER ; 

/* elsif_opt to have recursive functionality of ELSIF */

elsif_opt:
	elsif_opt elsif |
	%empty ;

elsif:
	ELSIF condition THEN statement ';' |
	error ';' ;

cases:
	cases case |
	cases error ';' |
	%empty ;
	
case:
	CASE INT_LITERAL ARROW statement ';' ; 
	
condition:
	condition OROP condition1 |
	condition1 ;

condition1:
	condition1 ANDOP condition2 |
	condition2 ;
	
condition2:
	NOTOP condition2 |
	relation ;

relation:
	'(' condition ')' |
	expression RELOP expression | 
	error ';' ;

expression:
	expression ADDOP term |
	term ;
      
term:
	term MULOP factor |
	term REMOP factor |
	factor ;

factor:
	primary EXPOP factor |
	primary ;

primary:
	NEGOP primary |
	'(' expression ')' |
	INT_LITERAL |
	REAL_LITERAL |
	HEX_LITERAL |
	CHAR_LITERAL |
	IDENTIFIER '(' expression ')' |
	IDENTIFIER ;

%%

void yyerror(const char* message) {
	appendError(SYNTAX, message);
}

int main(int argc, char *argv[]) {
	firstLine();
	yyparse();
	lastLine();
	return 0;
} 
