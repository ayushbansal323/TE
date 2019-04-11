%{
#include "y.tab.h"
%}
%%

return {return RETURN;}
[0-9]+  {return ID;}
if {return IF;}
[ \t] ;
"(" {return '(';}
")" {return ')';}
%%