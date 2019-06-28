package symbol;

public class Symbol {

    public static final int WHITE_SPACE = 1;
    public static final int COMMENT = 2;
    public static final int CONST = 3;
    public static final int VOLATILE = 4;
    public static final int REGISTER = 5;
    public static final int EXTERN = 6;
    public static final int SIGNED = 7;
    public static final int UNSIGNED = 8;
    public static final int LONG = 9;
    public static final int SHORT = 10;
    public static final int CHAR = 11;
    public static final int INT = 12;
    public static final int FLOAT = 13;
    public static final int DOUBLE = 14;
    public static final int STRUCT = 15;
    public static final int UNION = 16;
    public static final int ENUM = 17;
    public static final int VOID = 18;
    public static final int STATIC = 19;
    public static final int RETURN = 20;
    public static final int IF = 21;
    public static final int ELSE = 22;
    public static final int SWITCH = 23;
    public static final int CASE = 24;
    public static final int DEFAULT = 25;
    public static final int WHILE = 26;
    public static final int DO = 27;
    public static final int FOR = 28;
    public static final int BREAK = 29;
    public static final int CONTINUE = 30;
    public static final int GOTO = 31;
    public static final int AUTO = 32;
    public static final int TYPEDEF = 33;
    public static final int SIZEOF = 34;
    public static final int SEMI = 35;
    public static final int COL = 36;
    public static final int COMA = 37;
    public static final int DOT = 38;
    public static final int SHARP = 39;
    public static final int PLUS = 40;
    public static final int MINUS = 41;
    public static final int STAR = 42;
    public static final int SLASH = 43;
    public static final int PREC = 44;
    public static final int EQ = 45;
    public static final int QUEST = 46;
    public static final int NOT = 47;
    public static final int AND = 48;
    public static final int OR = 49;
    public static final int HAT = 50;
    public static final int LT = 51;
    public static final int GT = 52;
    public static final int CURBRACOP = 53;
    public static final int CURBRACCL = 54;
    public static final int BRACKOP = 55;
    public static final int BRACKCL = 56;
    public static final int PRANTOP = 57;
    public static final int PRANTCL = 58;
    public static final int PLUS2 = 59;
    public static final int MINUS2 = 60;
    public static final int AND2 = 61;
    public static final int OR2 = 62;
    public static final int SHIFTL = 63;
    public static final int SHIFTR = 64;
    public static final int POINT = 65;
    public static final int PLUSEQ = 66;
    public static final int MINUSEQ = 67;
    public static final int STAREQ = 68;
    public static final int SLASHEQ = 69;
    public static final int PRECEQ = 70;
    public static final int ANDEQ = 71;
    public static final int OREQ = 72;
    public static final int HATEQ = 73;
    public static final int NOTEQ = 74;
    public static final int EQEQ = 75;
    public static final int LTEQ = 76;
    public static final int GTEQ = 77;
    public static final int SHIFTLEQ = 78;
    public static final int SHIFTREQ = 79;
    public static final int CHAR_LIT = 80;
    public static final int STR_LIT = 81;
    public static final int INT_LIT = 82;
    public static final int FLOAT_LIT = 83;
    public static final int ID = 84;

    private int code;
    private SymbolType type;

    public Symbol(int code) {
        type = SymbolType.getByCode(this.code = code);
    }

    public int getCode() {
        return code;
    }

    public SymbolType getType() {
        return type;
    }

}