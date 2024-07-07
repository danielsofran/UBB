%{
	#include "syntacticAnalyzer.tab.h"
	//#include "bAtrrib.h"
	#include <stdio.h> 
	#include <string.h>
%}

%option noyywrap

Arithmetic_operators >>|<<|\+|-|%|\*|\/
Delimitators [ ,;{}\(\)\"]
SUFIX	((u|U)?(l|L)?(l|L)?)|((l|L)?(l|L)?(u|U)?)
INTEGER 0|[1-9][0-9]*((e|E)(\+|-)?[1-9][0-9]*)?{SUFIX}
SIGN_INTEGER (\+|-){INTEGER}
ID [a-zA-z][a-zA-Z0-9]*

%%
"#include" {
	printf("INCLUDE: %s\n", yytext);
	return INCLUDE; 
}

"int" {
	printf("TYPE INT: %s\n", yytext);
	return TYPE_INT; 
}

"cin" {
	printf("CIN: %s\n", yytext);
	return CIN; 
}

"cout" {
	printf("COUT: %s\n", yytext);
	return COUT; 
}

"main()" {
	printf("MAIN: %s\n", yytext);
	return MAIN_BEGIN; 
}

"using" {
	printf("USING: %s\n", yytext);
	return USING; 
}

"namespace" {
	printf("NAMESPACE: %s\n", yytext);
	return NAMESPACE; 
}

"std" {
	printf("STD: %s\n", yytext);
	return STD; 
}

"return" {
	printf("RETURN: %s\n", yytext);
	return RETURN; 
}

{ID} {
	printf("An idenfitier: %s\n", yytext);
	strcpy(yylval.val, yytext);
	return ID;
}

(\<[a-z]+\>)|(\<[a-z]+\.h\>) {
	printf("A header file: %s\n", yytext);
	return HEADER_FILE_NAME;
}

{INTEGER} {
	printf("NUMERICAL integer value: %s\n", yytext);
	strcpy(yylval.val, yytext);
	return INT;
}

"+" {
	printf("PLUS: %s\n", yytext);
	return PLUS;
}

"-" {
	printf("MINUS: %s\n", yytext);
	return MINUS;
}

"*" {
	printf("MULT: %s\n", yytext);
	return MULT;
}

"/" {
	printf("DIV: %s\n", yytext);
	return DIV;
}

"=" {
	printf("ASSIGMENT: %s\n", yytext);
	return ASSIGMENT_OPERATOR;
}

{Arithmetic_operators} {
	if (strcmp(yytext, "<<") == 0)
		return SHIFT_LEFT;
	if (strcmp(yytext, ">>") == 0)
		return SHIFT_RIGHT;
	return ARITHMETIC_OPERATORS;
}

{Delimitators} {

	if (strcmp(yytext, " ") != 0) {
		printf("Delimitators: %s %d\n", yytext, yyleng);

		if (strcmp(yytext, ";") == 0) {
			return SEMICOLON;
		}

		if (strcmp(yytext, "(") == 0) {
			return PARENTHESIS_O;
		}

		if (strcmp(yytext, ")") == 0) {
			return PARENTHESIS_C;
		}

		if (strcmp(yytext, "{") == 0) {
			return ACOLADA_OPEN;
		}

		if (strcmp(yytext, "}") == 0) {
			return ACOLADA_CLOSE;
		}

		if (strcmp(yytext, ",") == 0) {
			return COMMA;
		}

		return DELIMITATORS;
	}
}

. {
	printf("ERROR lexical analyzer: %s\n", yytext);
}

%%
