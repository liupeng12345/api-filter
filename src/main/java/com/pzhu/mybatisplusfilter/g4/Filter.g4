grammar Filter;

AND : A N D;
OR : O R;
NOT : N O T;
LIKE: L I K E;
ISNULL: 'is NULL';
NOTNULL:'is NOT NULL';
CHINESE: ('\u4E00'..'\u9FA5' | '\uF900'..'\uFA2D')+;

filter
    : expression?
    ;


expression
    : factor ( connection  factor)*
    ;


factor
    : term ( connection term)*
    ;


term
    : (NOT  | MINUS)? simple
    ;


simple
    : restriction
    | composite
    ;

restriction
    :  field (comparator value | isComparator)?
    ;


comparator
    : LESS_EQUALS
    | LESS_THAN
    | GREATER_EQUALS
    | GREATER_THAN
    | NOT_EQUALS
    | EQUALS
    | HAS
    | LIKE
    ;
isComparator
    : ISNULL
    | NOTNULL;

composite
    : LPAREN expression RPAREN
    ;

value
    : number
    | string
    ;

string:
    |SQ_ stringinfo SQ_
    |DQ_ stringinfo DQ_
    ;

stringinfo:
    .*?
    ;

field
    :ID;

number:
    NUMBER;

connection:
      OR
    | AND
    ;

MINUS : '-' ;
ID : [a-zA-Z%]+ ;
NUMBER: (MINUS? [1-9]+ [0-9]* DOT? [0-9]*)|( [0] (DOT [0-9]+)? ) ;
WS : [ \t]+ -> skip;
DOT : '.';
COMMA: ',';
fragment A:   [Aa];
fragment B:   [Bb];
fragment C:   [Cc];
fragment D:   [Dd];
fragment E:   [Ee];
fragment F:   [Ff];
fragment G:   [Gg];
fragment H:   [Hh];
fragment I:   [Ii];
fragment J:   [Jj];
fragment K:   [Kk];
fragment L:   [Ll];
fragment M:   [Mm];
fragment N:   [Nn];
fragment O:   [Oo];
fragment P:   [Pp];
fragment Q:   [Qq];
fragment R:   [Rr];
fragment S:   [Ss];
fragment T:   [Tt];
fragment U:   [Uu];
fragment V:   [Vv];
fragment W:   [Ww];
fragment X:   [Xx];
fragment Y:   [Yy];
fragment Z:   [Zz];
fragment UL_: '_';
fragment ESC : '\\"' | '\\\\' ;
LPAREN : '(';
RPAREN : ')';
LESS_EQUALS: '<=';
LESS_THAN :'<';
GREATER_EQUALS:'>=';
GREATER_THAN:'>';
NOT_EQUALS:'!=';
EQUALS:'=';
HAS:':';
DQ_: '"';
SQ_ : '\'';
AND_:                '&&';
OR_:                 '||';
NOT_:                '!';
TILDE_:              '~';
VERTICAL_BAR_:       '|';
AMPERSAND_:          '&';
DOLLAR_:             '$';
YUAN_:               '¥';
SIGNED_LEFT_SHIFT_:  '<<';
SIGNED_RIGHT_SHIFT_: '>>';
CARET_:              '^';
MOD_:                '%';
PLUS_:               '+';
ASTERISK_:           '*';
SLASH_:              '/';
BACKSLASH_:          '\\';
DOT_ASTERISK_:       '.*';
SAFE_EQ_:            '<=>';
DEQ_:                '==';
POUND_:              '#';
LBE_:                '{';
RBE_:                '}';
LBT_:                '[';
RBT_:                ']';
BQ_:                 '`';
QUESTION_:           '?';
AT_:                 '@';
SEMI_:               ';';