%{

%}
%s comment
%%
^[ \t]*\/\*.*\*\/\n {printf("one line comment %s",yytext);}
^[ \t]*\/\*.*\n {BEGIN comment ;printf("comment %s",yytext);}
<comment>.*\*\/\n {BEGIN 0 ;printf(" multi comment");}
<comment>.\n {printf(" multi comment");}
%%