%{
int words,charters,lines;
%}

%%

[^ \t\n]+ {words++;charters=charters+yyleng;}
\n  {lines++;}
.   {charters++;}
%%

int main(int argc,char *argv[])
{
    FILE *fd = fopen(argv[1],"r");
    yyin = fd;
    yylex();
    printf("%d %d %d \n",words,charters,lines);
    return 0;
}

int yywrap()
{
    return 1;
}