import symbol.*;

%%

//Scanner class attributes
%class Scanner
%unicode
%line
%column
%public

//Scanner function definitions
%function scan
%type Symbol


//Scanner class code includings
%{

    int ICV;
    double RCV;
    StringBuilder string = new StringBuilder();

    private Symbol simpleColorfulTagCG(int code, String color){
        string.setLength(0);
        string.append("<span style=\"color: ").append(color).append("\">").append(yytext()).append("</span>");
        return new Symbol(code);
    }

    private Symbol italicColorfulTagCG(int code, String color){
        string.setLength(0);
        string.append("<span style=\"color: ").append(color).append("\"><i>").append(yytext()).append("</i></span>");
        return new Symbol(code);
    }

    private Symbol keywordCG(int code){
        string.setLength(0);
        string.append("<span style=\"color: ").append(SymbolType.KEYWORD.getColor()).append("\"><b>").append(yytext()).append("</b></span>");
        return new Symbol(code);
    }


%}


//Micros
LineTerminator = \r|\n|\r\n
EndOfLineCommentContent = [^\n\r\t ]+
MultipleLineCommentContent = "*"+[^\/] | [^\*\n\r\t ]+

CharacterLiteral = \'[^\\]\'
SpecialCharacterLiteral = \'\\[abfnrtv\\\'\"]\'

StringConent = [^\n\r\"\\ \t]+
StringSpecialCharacters = \\[abfnrtv\\\'\"]

UnsignedLongIntegerLiteral = u?l?l? | U?L?L?
DecimalIntegerLiteral = [1-9]\d*
OctalIntegerLiteral = 0[0-7]*
HexaDecimalIntegerLiteral = 0x[\da-fA-F]+
IntegerLiteral = ({DecimalIntegerLiteral} | {OctalIntegerLiteral} | {HexaDecimalIntegerLiteral}){UnsignedLongIntegerLiteral}?

DecimalFloatingPointSicentific = ((E | e\+)\d+)?([fF] | {UnsignedLongIntegerLiteral})?
HexaDecimalFloatingPointScientific = p[\+-]?\d+[fF]?
DecimalFloatingPointLiteral = \d+\.\d+{DecimalFloatingPointSicentific}? | \d+[fF]
HexaDecimalFloatingPointLiteral = {HexaDecimalIntegerLiteral}(\.[\da-fA-F]+)?{HexaDecimalFloatingPointScientific}
FloatingPointLiteral = {DecimalFloatingPointLiteral} | {HexaDecimalFloatingPointLiteral}

Identifier = [_\w][_\w\d]*


//State declarations
%state ENDOFLINECOMMENT
%state MULTIPLELINECOMMENT
%state STRING

%%


<YYINITIAL> {

/*white space*/
" " { string.setLength(0); string.append("&nbsp;"); return new Symbol(Symbol.WHITE_SPACE); }
"\t" { string.setLength(0); string.append("&emsp;"); return new Symbol(Symbol.WHITE_SPACE); }
{LineTerminator} { string.setLength(0); string.append("<br/>"); return new Symbol(Symbol.WHITE_SPACE); }

/*comments*/
"//" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.COMMENT.getColor()).append("\">//"); yybegin(ENDOFLINECOMMENT); }
"/*" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.COMMENT.getColor()).append("\">/*"); yybegin(MULTIPLELINECOMMENT); }

/*type and variable keywords*/
"const" { return keywordCG(Symbol.CONST); }
"volatile" { return keywordCG(Symbol.VOLATILE); }
"register" { return keywordCG(Symbol.REGISTER); }
"extern" { return keywordCG(Symbol.EXTERN); }
"signed" { return keywordCG(Symbol.SIGNED); }
"unsigned" { return keywordCG(Symbol.UNSIGNED); }
"long" { return keywordCG(Symbol.LONG); }
"short" { return keywordCG(Symbol.SHORT); }
"char" { return keywordCG(Symbol.CHAR); }
"int" { return keywordCG(Symbol.INT); }
"float" { return keywordCG(Symbol.FLOAT); }
"double" { return keywordCG(Symbol.DOUBLE); }
"struct" { return keywordCG(Symbol.STRUCT); }
"union" { return keywordCG(Symbol.UNION); }
"enum" { return keywordCG(Symbol.ENUM); }

/*function Keywords*/
"void" { return keywordCG(Symbol.VOID); }
"static" { return keywordCG(Symbol.STATIC); }
"return" { return keywordCG(Symbol.RETURN); }

/*flow control keywords*/
"if" { return keywordCG(Symbol.IF); }
"else" { return keywordCG(Symbol.ELSE); }
"switch" { return keywordCG(Symbol.SWITCH); }
"case" { return keywordCG(Symbol.CASE); }
"default" { return keywordCG(Symbol.DEFAULT); }
"while" { return keywordCG(Symbol.WHILE); }
"do" { return keywordCG(Symbol.DO); }
"for" { return keywordCG(Symbol.FOR); }
"break" { return keywordCG(Symbol.BREAK); }
"continue" { return keywordCG(Symbol.CONTINUE); }
"goto" { return keywordCG(Symbol.GOTO); }

/*other keywords*/
"auto" { return keywordCG(Symbol.AUTO); }
"typedef" { return keywordCG(Symbol.TYPEDEF); }
"sizeof" { return keywordCG(Symbol.SIZEOF); }

/*operators*/
";" { return simpleColorfulTagCG(Symbol.SEMI, SymbolType.OPERATOR.getColor()); }
":" { return simpleColorfulTagCG(Symbol.COL, SymbolType.OPERATOR.getColor()); }
"," { return simpleColorfulTagCG(Symbol.COMA, SymbolType.OPERATOR.getColor()); }
"." { return simpleColorfulTagCG(Symbol.DOT, SymbolType.OPERATOR.getColor()); }
"#" { return simpleColorfulTagCG(Symbol.SHARP, SymbolType.OPERATOR.getColor()); }
"+" { return simpleColorfulTagCG(Symbol.PLUS, SymbolType.OPERATOR.getColor()); }
"-" { return simpleColorfulTagCG(Symbol.MINUS, SymbolType.OPERATOR.getColor()); }
"*" { return simpleColorfulTagCG(Symbol.STAR, SymbolType.OPERATOR.getColor()); }
"/" { return simpleColorfulTagCG(Symbol.SLASH, SymbolType.OPERATOR.getColor()); }
"%" { return simpleColorfulTagCG(Symbol.PREC, SymbolType.OPERATOR.getColor()); }
"=" { return simpleColorfulTagCG(Symbol.EQ, SymbolType.OPERATOR.getColor()); }
"?" { return simpleColorfulTagCG(Symbol.QUEST, SymbolType.OPERATOR.getColor()); }
"!" { return simpleColorfulTagCG(Symbol.NOT, SymbolType.OPERATOR.getColor()); }
"&" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.OPERATOR.getColor()).append("\">&amp;</span>"); return new Symbol(Symbol.AND); }
"|" { return simpleColorfulTagCG(Symbol.OR, SymbolType.OPERATOR.getColor()); }
"^" { return simpleColorfulTagCG(Symbol.HAT, SymbolType.OPERATOR.getColor()); }
"<" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.OPERATOR.getColor()).append("\">&lt;</span>"); return new Symbol(Symbol.LT); }
">" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.OPERATOR.getColor()).append("\">&gt;</span>"); return new Symbol(Symbol.GT); }
"{" { return simpleColorfulTagCG(Symbol.CURBRACOP, SymbolType.OPERATOR.getColor()); }
"}" { return simpleColorfulTagCG(Symbol.CURBRACCL, SymbolType.OPERATOR.getColor()); }
"[" { return simpleColorfulTagCG(Symbol.BRACKOP, SymbolType.OPERATOR.getColor()); }
"]" { return simpleColorfulTagCG(Symbol.BRACKCL, SymbolType.OPERATOR.getColor()); }
"(" { return simpleColorfulTagCG(Symbol.PRANTOP, SymbolType.OPERATOR.getColor()); }
")" { return simpleColorfulTagCG(Symbol.PRANTCL, SymbolType.OPERATOR.getColor()); }
"++" { return simpleColorfulTagCG(Symbol.PLUS2, SymbolType.OPERATOR.getColor()); }
"--" { return simpleColorfulTagCG(Symbol.MINUS2, SymbolType.OPERATOR.getColor()); }
"&&" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.OPERATOR.getColor()).append("\">&amp;&amp;</span>"); return new Symbol(Symbol.AND2); }
"||" { return simpleColorfulTagCG(Symbol.OR2, SymbolType.OPERATOR.getColor()); }
"<<" { return simpleColorfulTagCG(Symbol.SHIFTL, SymbolType.OPERATOR.getColor()); }
">>" { return simpleColorfulTagCG(Symbol.SHIFTR, SymbolType.OPERATOR.getColor()); }
"->" { return simpleColorfulTagCG(Symbol.POINT, SymbolType.OPERATOR.getColor()); }
"+=" { return simpleColorfulTagCG(Symbol.PLUSEQ, SymbolType.OPERATOR.getColor()); }
"-=" { return simpleColorfulTagCG(Symbol.MINUSEQ, SymbolType.OPERATOR.getColor()); }
"*=" { return simpleColorfulTagCG(Symbol.STAREQ, SymbolType.OPERATOR.getColor()); }
"/=" { return simpleColorfulTagCG(Symbol.SLASHEQ, SymbolType.OPERATOR.getColor()); }
"%=" { return simpleColorfulTagCG(Symbol.PRECEQ, SymbolType.OPERATOR.getColor()); }
"&=" { return simpleColorfulTagCG(Symbol.ANDEQ, SymbolType.OPERATOR.getColor()); }
"|=" { return simpleColorfulTagCG(Symbol.OREQ, SymbolType.OPERATOR.getColor()); }
"^=" { return simpleColorfulTagCG(Symbol.HATEQ, SymbolType.OPERATOR.getColor()); }
"!=" { return simpleColorfulTagCG(Symbol.NOTEQ, SymbolType.OPERATOR.getColor()); }
"==" { return simpleColorfulTagCG(Symbol.EQEQ, SymbolType.OPERATOR.getColor()); }
"<=" { return simpleColorfulTagCG(Symbol.LTEQ, SymbolType.OPERATOR.getColor()); }
">=" { return simpleColorfulTagCG(Symbol.GTEQ, SymbolType.OPERATOR.getColor()); }
"<<=" { return simpleColorfulTagCG(Symbol.SHIFTLEQ, SymbolType.OPERATOR.getColor()); }
">>=" { return simpleColorfulTagCG(Symbol.SHIFTREQ, SymbolType.OPERATOR.getColor()); }

/*character literals*/
{CharacterLiteral} { return simpleColorfulTagCG(Symbol.CHAR_LIT, SymbolType.CHARACTER_LITERAL.getColor()); }
{SpecialCharacterLiteral} { return italicColorfulTagCG(Symbol.CHAR_LIT, SymbolType.CHARACTER_LITERAL.getColor()); }

/*string literals*/
\" { string.setLength(0); string.append("<span style=\"color: ").append(SymbolType.STRING_LITERAL.getColor()).append("\">&quot;"); yybegin(STRING); }

/*integer literals*/
{IntegerLiteral} { return simpleColorfulTagCG(Symbol.INT_LIT, SymbolType.INTEGER_LITERAL.getColor()); }

/*floating point literals*/
{FloatingPointLiteral} { return italicColorfulTagCG(Symbol.FLOAT_LIT, SymbolType.FLOATING_POINT_LITERAL.getColor()); }

/*identifiers*/
{Identifier} { return simpleColorfulTagCG(Symbol.ID, SymbolType.ID.getColor()); }

}//end YYINITIAL

<ENDOFLINECOMMENT> {

{LineTerminator} { string.append("</span><br/>"); yybegin(YYINITIAL); return new Symbol(Symbol.COMMENT); }
{EndOfLineCommentContent} { string.append(yytext()); }
" " { string.append("&nbsp;"); }
"\t" { string.append("&emsp;"); }

}//end ENDOFLINECOMMENT

<MULTIPLELINECOMMENT> {

"*/" { string.append("*/</span>"); yybegin(YYINITIAL); return new Symbol(Symbol.COMMENT); }
{MultipleLineCommentContent} { string.append(yytext()); }
{LineTerminator} { string.append("<br/>"); }
" " { string.append("&nbsp;"); }
"\t" { string.append("&emsp;"); }

}//end MULTIPLELINECOMMENT

<STRING> {

\" { string.append("&quot;</span>"); yybegin(YYINITIAL); return new Symbol(Symbol.STR_LIT); }
{StringConent} { string.append(yytext()); }
" " { string.append("&nbsp;"); }
"\t" { string.append("&emsp;"); }
{StringSpecialCharacters} { string.append("<span style=\"color: ").append(SymbolType.CHARACTER_LITERAL.getColor()).append("\"><i>").append(yytext()).append("</i></span>"); }

}//end STRING