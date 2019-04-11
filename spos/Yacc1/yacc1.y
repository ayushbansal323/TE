%{
#include<stdio.h>
%}

%token ID NAME EQU ADD

%%

s: NAME EQU ID {printf("EXPRESSION valueds %d",$3);} |
NAME EQU expression {printf("EXPRESSION valued %d",$3);}
;
expression : ID ADD ID {$$=$1+$3;}
;

%%
int main()
{
    yyparse();
}
int yyerror(char *s)
{
    printf("error : %s\n",s);
}