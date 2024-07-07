%{
	#include <cstdio> 
	#include <iostream>
	using namespace std;

	// Declare stuff from Flex that Bison needs to know about:
	extern int yylex();
	extern int yyparse();
	extern FILE* yyin;

	void yyerror(const char* s);
	extern void printfInfo();
%}

%union {
	int ival;
	float fval; 
	char* sval;
};

%token <ival> INT
%token <fval> FLOAT 
%token <sval> STRING 
%token ID INCLUDE VECTOR_INT IF ELSE FOR WHILE CIN COUT MAIN_BEGIN USING NAMESPACE STD RETURN
%token SEMICOLON DELIMITATORS ACOLADA_OPEN ACOLADA_CLOSE PARENTHESIS_O PARENTHESIS_C
%token HEADER_FILE_NAME SHIFT_LEFT SHIFT_RIGHT
%token ASSIGMENT_OPERATOR
%token ARITHMETIC_OPERATORS
%token RELATION_OPERATORS COMMA REPETA PANACAND SFREPETA

%%
program:
		headers USING NAMESPACE STD SEMICOLON INT MAIN_BEGIN ACOLADA_OPEN block_decl_var instr_comp ACOLADA_CLOSE

headers:
		header | 
		headers header

header: INCLUDE HEADER_FILE_NAME

block_decl_var: TYPE list_decl_var SEMICOLON
list_decl_var: decl_var | decl_var COMMA list_decl_var /*right reccursion for ID ,*/
decl_var: ID | ID ASSIGMENT_OPERATOR expr
TYPE: INT | FLOAT | STRING

instr_comp: base_instr | instr_comp base_instr
base_instr: | instr | block_decl_var
/*decl_var_expr: TYPE decl_var SEMICOLON*/

instr: assigment SEMICOLON | if_stmt | cyclic_stmt | io_stmt SEMICOLON | RETURN INT SEMICOLON 

assigment: ID ASSIGMENT_OPERATOR expr

if_stmt: IF PARENTHESIS_O expr_inequality PARENTHESIS_C ACOLADA_OPEN instr_comp ACOLADA_CLOSE ELSE ACOLADA_OPEN instr_comp ACOLADA_CLOSE
         | IF PARENTHESIS_O expr_inequality PARENTHESIS_C ACOLADA_OPEN instr_comp ACOLADA_CLOSE

cyclic_stmt: FOR PARENTHESIS_O block_decl_var expr_inequality SEMICOLON assigment PARENTHESIS_C ACOLADA_OPEN instr_comp ACOLADA_CLOSE
		     | WHILE PARENTHESIS_O expr_inequality PARENTHESIS_C ACOLADA_OPEN instr_comp ACOLADA_CLOSE
			 | REPETA instr_comp PANACAND PARENTHESIS_O ID PARENTHESIS_C SFREPETA

io_stmt: read_input | write_output 
read_input: CIN lista_input 
lista_input: | read_instr | lista_input read_instr 
read_instr: SHIFT_RIGHT expr 

write_output: COUT lista_output 
lista_output: | write_instr | lista_output write_instr
write_instr: SHIFT_LEFT expr | SHIFT_LEFT STRING 

expr_inequality: expr_inequality operator_inequality expr_inequality | Const | ID
expr: expr operator_arith expr | Const | ID

Const: INT | FLOAT 
operator_arith: ARITHMETIC_OPERATORS
operator_inequality: RELATION_OPERATORS 
%%

int main(int argc, char** argv) {
	++argv, --argc; /* skip over program name */
	if (argc > 0)
		yyin = fopen(argv[0], "r");
	else
		yyin = stdin;

	// Parse through the input:
	yyparse();
	printfInfo();
}

void yyerror(const char* s) {
	cout << "EEK, parse error!  Message: " << s << endl;
	// might as well halt now:
	exit(-1);
}
