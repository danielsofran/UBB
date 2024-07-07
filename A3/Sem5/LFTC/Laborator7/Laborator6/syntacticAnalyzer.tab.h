/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2021 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

#ifndef YY_YY_SYNTACTICANALYZER_TAB_H_INCLUDED
# define YY_YY_SYNTACTICANALYZER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token kinds.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    YYEMPTY = -2,
    YYEOF = 0,                     /* "end of file"  */
    YYerror = 256,                 /* error  */
    YYUNDEF = 257,                 /* "invalid token"  */
    INT = 258,                     /* INT  */
    FLOAT = 259,                   /* FLOAT  */
    STRING = 260,                  /* STRING  */
    ID = 261,                      /* ID  */
    INCLUDE = 262,                 /* INCLUDE  */
    VECTOR_INT = 263,              /* VECTOR_INT  */
    IF = 264,                      /* IF  */
    ELSE = 265,                    /* ELSE  */
    FOR = 266,                     /* FOR  */
    WHILE = 267,                   /* WHILE  */
    CIN = 268,                     /* CIN  */
    COUT = 269,                    /* COUT  */
    MAIN_BEGIN = 270,              /* MAIN_BEGIN  */
    USING = 271,                   /* USING  */
    NAMESPACE = 272,               /* NAMESPACE  */
    STD = 273,                     /* STD  */
    RETURN = 274,                  /* RETURN  */
    SEMICOLON = 275,               /* SEMICOLON  */
    DELIMITATORS = 276,            /* DELIMITATORS  */
    ACOLADA_OPEN = 277,            /* ACOLADA_OPEN  */
    ACOLADA_CLOSE = 278,           /* ACOLADA_CLOSE  */
    PARENTHESIS_O = 279,           /* PARENTHESIS_O  */
    PARENTHESIS_C = 280,           /* PARENTHESIS_C  */
    HEADER_FILE_NAME = 281,        /* HEADER_FILE_NAME  */
    SHIFT_LEFT = 282,              /* SHIFT_LEFT  */
    SHIFT_RIGHT = 283,             /* SHIFT_RIGHT  */
    ASSIGMENT_OPERATOR = 284,      /* ASSIGMENT_OPERATOR  */
    MULTIPLY_OPERATOR = 285,       /* MULTIPLY_OPERATOR  */
    ARITHMETIC_OPERATORS = 286,    /* ARITHMETIC_OPERATORS  */
    RELATION_OPERATORS = 287,      /* RELATION_OPERATORS  */
    COMMA = 288,                   /* COMMA  */
    REPETA = 289,                  /* REPETA  */
    PANACAND = 290,                /* PANACAND  */
    SFREPETA = 291                 /* SFREPETA  */
  };
  typedef enum yytokentype yytoken_kind_t;
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 15 "syntacticAnalyzer.y"

	int ival;
	float fval; 
	char* sval;

#line 106 "syntacticAnalyzer.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;


int yyparse (void);


#endif /* !YY_YY_SYNTACTICANALYZER_TAB_H_INCLUDED  */
