%{
#include <stdio.h>
#include <ctype.h>
int yyerror(const char* s);
int yylex();

int cnt=0;
%}


%%
input :  'b' '\n'     {cnt++;printf(" %d am citit b\n", cnt);}
        |
          input       {cnt++;printf(" %d input\n", cnt);}
          'a' '\n' {cnt++;printf(" %d letter \n", cnt);}
        ;


%%
int yylex() {
      int c;
      c = getchar();
 
      return c;
}

int yyerror(const char* s)
{
       printf("syntax error\n");
}

int main()
{
  if(0==yyparse()) printf("\nResult yyparse OK\n");
  return 0;
}
