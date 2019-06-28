package symbol;

public enum SymbolType {

    WHITE_SPACE("white"),
    COMMENT("gray"),
    KEYWORD("blue"),
    OPERATOR("black"),
    CHARACTER_LITERAL("green"),
    STRING_LITERAL("red"),
    INTEGER_LITERAL("purple"),
    FLOATING_POINT_LITERAL("purple"),
    ID("orange");

    private String color;

    SymbolType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static SymbolType getByCode(int code) {
        switch (code) {
            case 1:
                return WHITE_SPACE;
            case 2:
                return COMMENT;
            case 80:
                return CHARACTER_LITERAL;
            case 81:
                return STRING_LITERAL;
            case 82:
                return INTEGER_LITERAL;
            case 83:
                return FLOATING_POINT_LITERAL;
            case 84:
                return ID;
            default:
                return 3 <= code && code <= 34 ? KEYWORD : OPERATOR;
        }//end switch-case
    }

}
