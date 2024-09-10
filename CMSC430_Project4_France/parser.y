/* CMSC 430 Compiler Theory and Design
   Project 4 
   Andrew France
   8/5/2024
   
   Project 4 Parser with semantic actions for static semantic errors */

%{
#include <string>
#include <vector>
#include <map>

using namespace std;

#include "types.h"
#include "listing.h"
#include "symbols.h"

int yylex();
Types find(Symbols<Types>& table, CharPtr identifier, string tableName);
bool checkDuplicate(Symbols<Types>& table, CharPtr identifier, string tableName);
void yyerror(const char* message);

Symbols<Types> scalars;
Symbols<Types> lists;

%}

%define parse.error verbose

%union {
	CharPtr iden;
	Types type;
}

%token <iden> IDENTIFIER

%token <type> INT_LITERAL CHAR_LITERAL REAL_LITERAL HEX_LITERAL

%token ADDOP MULOP RELOP ANDOP OROP NOTOP REMOP EXPOP NEGOP ARROW LEFT RIGHT

%token BEGIN_ CASE CHARACTER ELSE END ENDSWITCH FUNCTION INTEGER IS LIST OF OTHERS ELSIF ENDFOLD ENDIF FOLD IF REAL THEN RETURNS SWITCH WHEN

%type <type> list expressions body type statement_ statement cases case expression
	term primary factor relation elsif_opt elsif list_choice function_header condition condition1 condition2

%%

function:	
	function_header optional_variable body {checkAssignment($1, $3, "Function Return");} ;
	
		
function_header:	
	FUNCTION IDENTIFIER optional_parameters RETURNS type ';' {$$ = $5;}
	| error ';' {$$ = NONE;} ;

type:
	INTEGER {$$ = INT_TYPE;} |
	REAL {$$ = REAL_TYPE;} |
	CHARACTER {$$ = CHAR_TYPE; };

optional_parameters:
	parameters | %empty ;
	
parameters:
	parameters ',' parameter | parameter ;
	
parameter:
	IDENTIFIER ':' type ;
	
optional_variable:
	optional_variable variable |
	%empty ;
    
variable:	
	IDENTIFIER ':' type IS statement ';'
		{ if (!checkDuplicate(scalars, $1, "Scalar")){
		checkAssignment($3, $5, "Variable Initialization"); 
		scalars.insert($1, $3);} } |
	IDENTIFIER ':' LIST OF type IS list ';' 
		{if (!checkDuplicate(lists, $1, "List")){
		checkListAssignment($5, $7, "List Type"); 
		lists.insert($1, $5); } } ;

list:
	'(' expressions ')' {$$ = $2;} ;

expressions:
	expressions ',' expression {$$ = checkLists($1, $3);} |
	expression ; 

body:
	BEGIN_ statement_ END ';' {$$ = $2;} ;
    
statement_:
	statement ';'|
	error ';' {$$ = MISMATCH;} ;
	
statement:
	expression |
	WHEN condition ',' expression ':' expression 
		{$$ = checkWhen($4, $6);} |
	SWITCH expression IS cases OTHERS ARROW statement ';' ENDSWITCH 
		{$$ = checkSwitch($2, $4, $7);} |
	IF condition THEN statement ';' elsif_opt ELSE statement ';' ENDIF 
		{$$ = checkIf($4, $6, $8);} |
	FOLD direction operator list_choice ENDFOLD {$$ = checkFold($4);} ;

direction: 
	LEFT | RIGHT ;

operator:
	ADDOP | MULOP ;

list_choice:
	list {$$ = $1;}| IDENTIFIER {$$ = find(lists, $1, "List");} ; 

elsif_opt:
	elsif_opt elsif {$$ = checkIf($1, $2);}|
	%empty {$$ = NONE;};

elsif:
	ELSIF condition THEN statement ';' {$$ = $4;}|
	error ';' {$$ = MISMATCH;} ;

cases:
	cases case {$$ = checkCases($1, $2);} |
	%empty {$$ = NONE;} ;
	
case:
	CASE INT_LITERAL ARROW statement ';' {$$ = $4;} ; 

condition:
	condition OROP condition1 |
	condition1 ;

condition1:
	condition1 ANDOP condition2 |
	condition2 ;
	
condition2:
	NOTOP condition2 {$$ = $2;}|
	relation ;

relation:
	'(' condition ')' {$$ = $2;} |
	expression RELOP expression {$$ = checkRelation($1, $3);} ; 
	
expression:
	expression ADDOP term {$$ = checkArithmetic($1, $3);} |
	term ;
      
term:
	term MULOP factor {$$ = checkArithmetic($1, $3);} |
	term REMOP factor {$$ = checkRemainder($1, $3);} |
	factor ;

factor:
	primary EXPOP factor {$$ = checkArithmetic($1, $3);} |
	primary ;
	
primary:
	NEGOP primary {$$ = checkArithmetic($2);}|
	'(' expression ')' {$$ = $2;} |
	INT_LITERAL |
	REAL_LITERAL |
	HEX_LITERAL |
	CHAR_LITERAL |
	IDENTIFIER '(' expression ')' {checkSubscript($3); $$ = find(lists, $1, "List");} |
	IDENTIFIER  {$$ = find(scalars, $1, "Scalar");} ;

%%

Types find(Symbols<Types>& table, CharPtr identifier, string tableName) {
	Types type;
	if (!table.find(identifier, type)) {
		appendError(UNDECLARED, tableName + " " + identifier);
		return MISMATCH;
	}
	return type;
}

void yyerror(const char* message) {
	appendError(SYNTAX, message);
}

// Function to check for duplicates
bool checkDuplicate(Symbols<Types>& table, CharPtr identifier, string tableName) {
    Types type;
    if (table.find(identifier, type)) {
        appendError(GENERAL_SEMANTIC, "Duplicate " + tableName + " " + identifier);
        return true;
    }
    return false;
}

int main(int argc, char *argv[]) {
	firstLine();
	yyparse();
	lastLine();
	return 0;
} 
