%{
#include "stdio.h"
%}
%token RETURN ID IF THEN

%%

s: simple {printf("simple");} | 
compound {printf("compound");}
;
simple:RETURN ID ;
compound: IF '(' ')';
%%
int yyerror(char *s)
{
    printf("error : %s",s);
}
int main()
{
    yyparse();
}