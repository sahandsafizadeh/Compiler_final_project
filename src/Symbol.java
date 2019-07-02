
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

/**
 * CUP generated class containing symbol constants.
 */
public class Symbol extends java_cup.runtime.Symbol {
    /* terminals */
    public static final int BOOL = 4;
    public static final int GT = 53;
    public static final int DIVASSIGN = 67;
    public static final int CONST = 2;
    public static final int INT_LIT = 74;
    public static final int NOTEQ = 68;
    public static final int SEMI = 34;
    public static final int UNTIL = 29;
    public static final int POSTPLUS2 = 62;
    public static final int SIZEOF = 33;
    public static final int BIOR = 47;
    public static final int LT = 52;
    public static final int DOUBLE = 9;
    public static final int FALSE = 13;
    public static final int NOT = 49;
    public static final int FLOAT_LIT = 75;
    public static final int PLUSASSIGN = 64;
    public static final int FLOAT = 8;
    public static final int RECORD = 3;
    public static final int PRANTCL = 57;
    public static final int CASTPRANTOP = 58;
    public static final int PREFMINUS2 = 61;
    public static final int BIEXOR = 48;
    public static final int MOD = 44;
    public static final int PRANTOP = 56;
    public static final int BIAND = 46;
    public static final int COMA = 36;
    public static final int TRUE = 12;
    public static final int LONG = 7;
    public static final int PLUS = 39;
    public static final int DIVIDE = 43;
    public static final int ASSIGN = 45;
    public static final int CHAR = 5;
    public static final int MULTASSIGN = 66;
    public static final int SWITCH = 21;
    public static final int FOR = 25;
    public static final int VOID = 11;
    public static final int EXTERN = 14;
    public static final int RETURN = 32;
    public static final int MULT = 42;
    public static final int ELSE = 20;
    public static final int GTEQ = 71;
    public static final int BREAK = 30;
    public static final int DOT = 37;
    public static final int INT = 6;
    public static final int EQEQ = 69;
    public static final int EOF = 0;
    public static final int STR_LIT = 73;
    public static final int DEFAULT = 24;
    public static final int COL = 35;
    public static final int FUNCTION = 16;
    public static final int REPEAT = 28;
    public static final int MINUS = 40;
    public static final int LTEQ = 70;
    public static final int IN = 27;
    public static final int OR = 51;
    public static final int error = 1;
    public static final int BINOT = 38;
    public static final int CONTINUE = 31;
    public static final int IF = 19;
    public static final int ID = 76;
    public static final int OF = 22;
    public static final int END = 18;
    public static final int FOREACH = 26;
    public static final int FUNCPRANTOP = 59;
    public static final int BEGIN = 17;
    public static final int MINUSASSIGN = 65;
    public static final int CASE = 23;
    public static final int CHAR_LIT = 72;
    public static final int BRACKCL = 55;
    public static final int STRING = 10;
    public static final int BRACKOP = 54;
    public static final int PREFPLUS2 = 60;
    public static final int AND = 50;
    public static final int UMINUS = 41;
    public static final int POSTMINUS2 = 63;
    public static final int AUTO = 15;
    public static final String[] terminalNames = new String[]{
            "EOF",
            "error",
            "CONST",
            "RECORD",
            "BOOL",
            "CHAR",
            "INT",
            "LONG",
            "FLOAT",
            "DOUBLE",
            "STRING",
            "VOID",
            "TRUE",
            "FALSE",
            "EXTERN",
            "AUTO",
            "FUNCTION",
            "BEGIN",
            "END",
            "IF",
            "ELSE",
            "SWITCH",
            "OF",
            "CASE",
            "DEFAULT",
            "FOR",
            "FOREACH",
            "IN",
            "REPEAT",
            "UNTIL",
            "BREAK",
            "CONTINUE",
            "RETURN",
            "SIZEOF",
            "SEMI",
            "COL",
            "COMA",
            "DOT",
            "BINOT",
            "PLUS",
            "MINUS",
            "UMINUS",
            "MULT",
            "DIVIDE",
            "MOD",
            "ASSIGN",
            "BIAND",
            "BIOR",
            "BIEXOR",
            "NOT",
            "AND",
            "OR",
            "LT",
            "GT",
            "BRACKOP",
            "BRACKCL",
            "PRANTOP",
            "PRANTCL",
            "CASTPRANTOP",
            "FUNCPRANTOP",
            "PREFPLUS2",
            "PREFMINUS2",
            "POSTPLUS2",
            "POSTMINUS2",
            "PLUSASSIGN",
            "MINUSASSIGN",
            "MULTASSIGN",
            "DIVASSIGN",
            "NOTEQ",
            "EQ",
            "LE",
            "GE",
            "CHAR_LIT",
            "STR_LIT",
            "INT_LIT",
            "FLOAT_LIT",
            "ID"
    };

    private int code;
    private String value;

    public Symbol(int code, String value) {
        super(code);
        this.code = code;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }

}

