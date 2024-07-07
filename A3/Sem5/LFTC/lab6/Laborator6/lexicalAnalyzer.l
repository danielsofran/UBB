%{
	#include "lexicalAtomTable.h"
	#include "symbolTable.h"
	#include "transition.h"
	#include "fip.h"
	#include <string>
	#include <fstream>
	using namespace std;

	int LENGTH_WORD = 8;
	int lineNr = 1;
	
	LexicalAtomTable lexicalAtomTable;
	FIP fip;
	SymbolTable symbolTable;
%}

%option noyywrap 


Arithmetic_operators >>|<<|\+|-|%|\*
Relation_operators <|>|=|!=|==
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
KEYWORD "sfcattimp"|"executa"|"cattimp"|"#include"|"int"|"double"|"vector<int>"|"if"|"else"|"for"|"while"|"cin"|"cout"|"main()"|"using"|"namespace"|"std"|"return"|"<iostream>"

%%

\n {
	lineNr++;
	printf("\n");
}

{KEYWORD} {
    printf("A keyword: %s at line %d\n", yytext, lineNr);
    lexicalAtomTable.addLexicalAtom(yytext);
    fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
}

{ID} {
    printf("An identifier: %s at line %d\n", yytext, lineNr);
    if (yyleng > LENGTH_WORD)
        throw std::string("The length of the word is greater than ") + std::to_string(LENGTH_WORD) + " characters";
    if (symbolTable.getCodeFromSymbolTable(yytext) == 0)
        symbolTable.addIdentifierToSymbolTable(yytext);
    fip.addEntry(lexicalAtomTable.getIdentifierAtomCode(), symbolTable.getCodeFromSymbolTable(yytext));
}

{REAL_NUMBER}|{SIGN_REAL_NUMBER}|{INTEGER}|{SIGN_INTEGER}|{BINARY}|{OCTAL}|{HEXAZECIMAL}|{STRING} {
    printf("A literal: %s at line %d\n", yytext, lineNr);
    if (symbolTable.getCodeFromSymbolTable(yytext) == 0)
        symbolTable.addIdentifierToSymbolTable(yytext);
    fip.addEntry(lexicalAtomTable.getLiteralAtomCode(), symbolTable.getCodeFromSymbolTable(yytext));
}

{Arithmetic_operators} {
    printf("Arithmetic operators: %s at line %d\n", yytext, lineNr);
    if (lexicalAtomTable.getCodeAtom(yytext) == -1)
        lexicalAtomTable.addLexicalAtom(yytext);
    fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
}

{Relation_operators} {
    printf("Relation operators: %s at line %d\n", yytext, lineNr);
    if (lexicalAtomTable.getCodeAtom(yytext) == -1)
        lexicalAtomTable.addLexicalAtom(yytext);
    fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
}

{Delimitators} {
    if (strcmp(yytext, " ") != 0) {
        printf("Delimiters: %s at line %d\n", yytext, lineNr);
        if (lexicalAtomTable.getCodeAtom(yytext) == -1)
            lexicalAtomTable.addLexicalAtom(yytext);
        fip.addEntry(lexicalAtomTable.getCodeAtom(yytext), 0);
    }
}


. {
    printf("EROARE pe linia %d: !!!\n", lineNr);
}

%% 
int main(int argc,char** argv)
{
	++argv, --argc; /* skip over program name */
	if (argc > 0)
		yyin = fopen(argv[0], "r");
	else
		yyin = stdin;
	yylex();

	ofstream output1("resources/atomLexicalTable.txt");
	output1 << lexicalAtomTable.toString();
	output1.close();

	ofstream output2("resources/fip.txt");
	output2 << fip.toString();
	output2.close();

	ofstream output3("resources/symbolTable.txt");
	output3 << symbolTable.toString();
	output3.close();

	return 0;
}

