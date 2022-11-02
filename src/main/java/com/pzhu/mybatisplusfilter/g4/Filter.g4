grammar Filter;

AND : A N D | '&';
OR : O R | '|';
NOT : N O T;
LIKE: L I K E;
NULL: 'is NULL' | 'is null';
NOTNULL:'is NOT NULL' | 'is not null';
MINUS : '-' ;
ID : [a-zA-Z]+ ;
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
STRING: (SQ_ (ESC | ~["\\])*? SQ_ ) | (DQ_ (ESC | ~["\\])*?   DQ_);
NUMBER
    : '-'? INT '.' [0-9]+ EXP?
    | '-'? INT EXP
    | '-'? INT
    ;
fragment INT: '0' | [1-9] [0-9]*;
fragment EXP: [Ee] [+\-]? INT;
WS: [ \t\n\r]+ -> skip;
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
fragment ESC: '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];


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
    :  NULL | NOTNULL;

composite
    : LPAREN expression RPAREN
    ;

value
    : number
    | string
    ;

string:
    STRING
    ;

field
    :ID;

number:
    NUMBER;

connection:
      OR
    | AND
    ;