%{
#include "math.h"
#include "y.tab.h"
extern int yylval;
%}

id [0-9]+
%%
{id} {yylval=atoi(yytext);return ID;}
[a-zA-Z]+ {return NAME;}
"+" {return ADD;}
"=" {return EQU;}
[ \t] ;
\n {return 0;}
[.] { }
%%
