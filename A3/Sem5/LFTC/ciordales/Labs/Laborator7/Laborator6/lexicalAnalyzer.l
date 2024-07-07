%{
	#include "lexicalAtomTable.h"
	#include "symbolTable.h"
	#include "transition.h"
	#include "fip.h"
	#include "syntacticAnalyzer.tab.h"
	#include <cstdio> 
	#include <string>
	#include <fstream>
	using namespace std;

	int LENGTH_WORD = 8;
	int lineNr = 1;
	
	LexicalAtomTable lexicalAtomTable;
	FIP fip;
	SymbolTable identifiersSymbolTable;
	SymbolTable constantsSymbolTable;

	void keyWordFunction();
	void literalFunction();
%}

%option noyywrap 


Arithmetic_operators >>|<<|\+|-|%|\*|\/
Relation_operators <|>|=|!=|==|<=|>=
Delimitators [ ,;{}\(\)\"]
SUFIX	((u|U)?(l|L)?(l|L)?)|((l|L)?(l|L)?(u|U)?)
REAL_NUMBER (0|[1-9][0-9]*)\.[0-9]+((e|E)(\+|-)?[1-9][0-9]*)?{SUFIX}
SIGN_REAL_NUMBER (\+|-){REAL_NUMBER}
INTEGER [1-9][0-9]*((e|E)(\+|-)?[1-9][0-9]*)?{SUFIX}
SIGN_INTEGER (\+|-){INTEGER}
OCTAL 0[0-7]*{SUFIX}
BINARY (0b)|(0B)(0|1)+{SUFIX}
HEXAZECIMAL 0(x|X)[0-9A-Fa-f]+{SUFIX}
ID [a-zA-z][a-zA-Z0-9]*
STRING \"[a-zA-Z0-9:;\. ]*\"

%%


"#include" {keyWordFunction(); return INCLUDE; }
"int" {keyWordFunction(); return INT; }
"double" {keyWordFunction(); return FLOAT; }
"float" {keyWordFunction(); return FLOAT; }
"vector<int>" {keyWordFunction(); return VECTOR_INT; }
"if" {keyWordFunction(); return IF; }
"else" {keyWordFunction(); return ELSE; }
"for" {keyWordFunction(); return FOR; }
"while" {keyWordFunction(); return WHILE; }
"cin" {keyWordFunction(); return CIN; } 
"cout" {keyWordFunction(); return COUT; }
"main()" {keyWordFunction(); return MAIN_BEGIN; }
"using" {keyWordFunction(); return USING; }
"namespace" {keyWordFunction(); return NAMESPACE; }
"std" {keyWordFunction(); return STD; }
"return" {keyWordFunction(); return RETURN; }
"repeta" {keyWordFunction(); return REPETA; }
"panacand" {keyWordFunction(); return PANACAND; }
"sfrepeta" {keyWordFunction(); return SFREPETA; }

{ID} {
	printf("An idenfitier: %s\n", yytext);
	if (yyleng > LENGTH_WORD)
		throw  "The length of word is greater than " + to_string(LENGTH_WORD) + " characters";
	if (identifiersSymbolTable.getCodeFromSymbolTable(yytext) == 0)
		identifiersSymbolTable.addIdentifierToSymbolTable(yytext);
	fip.addEntry(lexicalAtomTable.getIdentifierAtomCode(), identifiersSymbolTable.getCodeFromSymbolTable(yytext));
	return ID;
}

(\<[a-z]+\>)|(\<[a-z]+\.h\>) {
	printf("A header file: %s\n", yytext);
	return HEADER_FILE_NAME;
}

{REAL_NUMBER}|{SIGN_REAL_NUMBER} {
	literalFunction();
	return FLOAT;
}

{INTEGER}|{SIGN_INTEGER}|{BINARY}|{OCTAL}|{HEXAZECIMAL} {
	literalFunction();
	return INT;
}

{STRING} {
	literalFunction();
	return STRING;
}

{Arithmetic_operators} {
	printf("Arithmetic operaotors: %s\n", yytext);
	if (lexicalAtomTable.getCodeAtom(yytext) == -1)
		lexicalAtomTable.addLexicalAtom(yytext);
	fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
	if (strcmp(yytext, "<<") == 0)
		return SHIFT_LEFT;
	if (strcmp(yytext, ">>") == 0)
		return SHIFT_RIGHT;
	return ARITHMETIC_OPERATORS;
}

{Relation_operators} {
	printf("Relation operators: %s\n", yytext);
	if (lexicalAtomTable.getCodeAtom(yytext) == -1)
		lexicalAtomTable.addLexicalAtom(yytext);
	fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
	if (strcmp(yytext, "=") == 0)
		return ASSIGMENT_OPERATOR;
	return RELATION_OPERATORS;
}

{Delimitators} {
	
	if (strcmp(yytext, " ") != 0) {
		printf("Delimitators: %s %d\n", yytext, yyleng);
		if (lexicalAtomTable.getCodeAtom(yytext) == -1)
			lexicalAtomTable.addLexicalAtom(yytext);
		fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);

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

\n {
	lineNr++;
	printf("\n");
}

. {
	printf("EROARE: %s\nLinia: %d\n", yytext, lineNr);
}

%% 

void keyWordFunction() {
	printf("A keyword: %s\n", yytext);
	lexicalAtomTable.addLexicalAtom(yytext);
	fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
}

void literalFunction() {
	printf("A literal: %s\n", yytext);
	if (constantsSymbolTable.getCodeFromSymbolTable(yytext) == 0)
		constantsSymbolTable.addIdentifierToSymbolTable(yytext);
	fip.addEntry(lexicalAtomTable.getLiteralAtomCode(), constantsSymbolTable.getCodeFromSymbolTable(yytext));
}

void printfInfo() {
	ofstream output1("resources/atomLexicalTable.txt");
	output1 << lexicalAtomTable.toString();
	output1.close();

	ofstream output2("resources/fip.txt");
	output2 << fip.toString();
	output2.close();

	ofstream output3("resources/identifiersSymbolTable.txt");
	output3 << identifiersSymbolTable.toString();
	output3.close();

	ofstream output4("resources/constantsSymbolTable.txt");
	output4 << constantsSymbolTable.toString();
	output4.close();
}

//int main(int argc,char** argv)
//{
//	//++argv, --argc; /* skip over program name */
//	//if (argc > 0)
//	//	yyin = fopen(argv[0], "r");
//	//else
//	//	yyin = stdin;
//	//yylex();
//
	//ofstream output1("resources/atomLexicalTable.txt");
	//output1 << lexicalAtomTable.toString();
	//output1.close();

	//ofstream output2("resources/fip.txt");
	//output2 << fip.toString();
	//output2.close();

	//ofstream output3("resources/identifiersSymbolTable.txt");
	//output3 << identifiersSymbolTable.toString();
	//output3.close();

	//ofstream output4("resources/constantsSymbolTable.txt");
	//output4 << constantsSymbolTable.toString();
	//output4.close();

//
//	return 0;
//}

