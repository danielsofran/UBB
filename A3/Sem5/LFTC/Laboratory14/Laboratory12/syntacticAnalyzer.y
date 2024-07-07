%{

	#include "asmPreprocessing.h"
	//#include "bAtrrib.h"
	#include <string.h>
	#include <stdio.h> 
	#include <stdlib.h>

	// Declare stuff from Flex that Bison needs to know about:
	extern int yylex();
	extern int yyparse();
	extern FILE* yyin;

	void yyerror(const char* s);

	char data_segment[10000000];
	char code_segment[10000000];
	char declar[1000];

	int tempnr = 1;
	void newTempName(char* s) {
		sprintf(s, "temp%d", tempnr);
		sprintf(declar, DECLARATION_INT_VARIABLE, s, "0");
		strcat(data_segment, declar);
		tempnr++;
	}

%}

%code requires {
	struct attrib {
		char varn[10];
		char cod[2500];
		int type;
	};
	typedef struct attrib attributes;
}

%union {
	char val[100];
	attributes attr;
};

%token INCLUDE CIN COUT MAIN_BEGIN USING NAMESPACE STD RETURN
%token SEMICOLON DELIMITATORS ACOLADA_OPEN ACOLADA_CLOSE PARENTHESIS_O PARENTHESIS_C
%token HEADER_FILE_NAME SHIFT_LEFT SHIFT_RIGHT
%token ASSIGMENT_OPERATOR
%token ARITHMETIC_OPERATORS MULT DIV PLUS MINUS
%token COMMA TYPE_INT
%token <val> INT
%token <val> ID
%type <attr> write_instr
%type <attr> read_instr
%type <attr> term
%type <attr> expr

%left PLUS MINUS
%left MULT DIV

%%
program:
headers USING NAMESPACE STD SEMICOLON TYPE_INT MAIN_BEGIN ACOLADA_OPEN block_decl_var instr_comp ACOLADA_CLOSE{
			printf("----------------------------------------------------------------------------------------------\n");
			printf("%s\n", BEGIN_PROGRAM);
			printf("%s\n", INITIAL_DATA_SEGMENT);
			printf("%s\n", data_segment); 
			printf("%s\n", INITIAL_CODE_SEGMENT);
			printf("%s\n", code_segment);
			printf("%s\n", EXIT_CODE);
		}

headers:
header | headers header

header : INCLUDE HEADER_FILE_NAME

block_decl_var : TYPE list_decl_var SEMICOLON
list_decl_var : decl_var | decl_var COMMA list_decl_var /*right reccursion for ID ,*/
decl_var : ID {
			sprintf(declar, DECLARATION_INT_VARIABLE, $1, "0");
			strcat(data_segment, declar);
		}
		| ID ASSIGMENT_OPERATOR expr {
			if($3.type == 0)
				sprintf(declar, DECLARATION_INT_VARIABLE, $1, $3.varn);
			else
				sprintf(declar, DECLARATION_INT_VARIABLE, $1, "0");
			strcat(data_segment, declar);

			char tempBuffer[50000];
			if ($3.type == 1)
				sprintf(tempBuffer, ASSIGMENT, $3.varn, $1);
			else
				sprintf(tempBuffer, ASSIGMENT_CONSTANT, $3.varn, $1);
			strcat(code_segment, $3.cod);
			strcat(code_segment, tempBuffer);
		}
TYPE : TYPE_INT

instr_comp : base_instr | instr_comp base_instr
base_instr : | instr | block_decl_var
/*decl_var_expr: TYPE decl_var SEMICOLON*/

instr : assigment SEMICOLON | io_stmt SEMICOLON | RETURN INT SEMICOLON

assigment : ID ASSIGMENT_OPERATOR expr{
			char tempBuffer[50000];
		    if($3.type == 1)
				sprintf(tempBuffer, ASSIGMENT, $3.varn, $1);
			else
				sprintf(tempBuffer, ASSIGMENT_CONSTANT, $3.varn, $1);
			strcat(code_segment, $3.cod);
			strcat(code_segment, tempBuffer);
		}

io_stmt : read_input | write_output
read_input : CIN lista_input
lista_input : | read_instr | lista_input read_instr
read_instr : SHIFT_RIGHT term {
	sprintf($$.cod, READ_STMT, $2.varn);
	strcat(code_segment, $$.cod);
}

write_output : COUT lista_output
lista_output : | write_instr | lista_output write_instr
write_instr : SHIFT_LEFT term{

	sprintf($$.cod, WRITE_STMT, $2.varn);
	strcat(code_segment, $$.cod);
}

expr: term{
		strcpy($$.varn, $1.varn);
		strcpy($$.cod, $1.cod);
		$$.type = $1.type;
	  }
	  | expr DIV term{
	      newTempName($$.varn);
		  char addition_buffer[50000];
		  if($3.type == 1 && $1.type == 1) // addition with a variable
			sprintf(addition_buffer, DIVISION, $1.varn, $3.varn, $$.varn);
		  else 
	      if ($3.type == 0 && $1.type == 1)
			sprintf(addition_buffer, DIVISION_CONSTANT, $1.varn, $3.varn, $$.varn);
		  else
		  if ($3.type == 1 && $1.type == 0)
			sprintf(addition_buffer, DIVISION_C_V, $1.varn, $3.varn, $$.varn);
		  else 
          if ($3.type == 0 && $1.type == 0)
			sprintf(addition_buffer, DIVISION_C_C, $1.varn, $3.varn, $$.varn);
		  strcat($$.cod, addition_buffer);
		  $$.type = 1;
	   }
	  | expr MULT term{
	      newTempName($$.varn);
		  char addition_buffer[50000];
		  if($3.type == 1 && $1.type==1) // addition with a variable
			sprintf(addition_buffer, MULTIPLICATION, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type==1)
			sprintf(addition_buffer, MULTIPLICATION_CONSTANT, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 1 && $1.type==0)
			sprintf(addition_buffer, MULTIPLICATION_C_V, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type==0)
			sprintf(addition_buffer, MULTIPLICATION_C_C, $1.varn, $3.varn, $$.varn);
		  strcat($$.cod, addition_buffer);
		  $$.type = 1;
	  }
	  | expr PLUS term{
	      newTempName($$.varn);
		  char addition_buffer[50000];
		  if($3.type == 1 && $1.type == 1) // addition with a variable
			sprintf(addition_buffer, ADDITION, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type == 1)
			sprintf(addition_buffer, ADDITION_CONSTANT, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 1 && $1.type == 0)
			sprintf(addition_buffer, ADDITION_C_V, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type == 0)
			sprintf(addition_buffer, ADDITION_C_C, $1.varn, $3.varn, $$.varn);
		  strcat($$.cod, addition_buffer);
		  $$.type = 1;
	  }
	  | expr MINUS term{
	      newTempName($$.varn);
		  char addition_buffer[50000];
		  if($3.type == 1 && $1.type==1) // addition with a variable
			sprintf(addition_buffer, MINUS_V, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type==1)
			sprintf(addition_buffer, MINUS_CONSTANT, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 1 && $1.type==0)
			sprintf(addition_buffer, MINUS_C_V, $1.varn, $3.varn, $$.varn);
		  else 
		  if($3.type == 0 && $1.type==0)
			sprintf(addition_buffer, MINUS_C_C, $1.varn, $3.varn, $$.varn);
		  strcat($$.cod, addition_buffer);
		  $$.type = 1;
	  }

term: INT {
		strcpy($$.varn, $1);
		strcpy($$.cod, "");
		$$.type = 0;
	}
	| ID {
		strcpy($$.varn, $1);
		strcpy($$.cod, "");
		$$.type = 1;
	}

%%

int main(int argc, char** argv) {
	++argv, --argc; /* skip over program name */
	if (argc > 0)
		yyin = fopen(argv[0], "r");
	else
		yyin = stdin;

	// Parse through the input:
	yyparse();
}

void yyerror(const char* s) {
	printf("EEK, parse error!  Message: %s\n", s);
	// might as well halt now:
	exit(-1);
}
