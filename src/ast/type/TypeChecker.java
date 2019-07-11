package ast.type;

import cg.Logger;
import symtab.dscp.AbstractDescriptor;

import static ast.type.Type.*;

public class TypeChecker {

    private TypeChecker() {
    }

    public static Type unaryExprTypeCheck(Type t) {
        if (!isValidExprType(t))
            Logger.error("type mismatch");
        if (t == DOUBLE)
            return DOUBLE;
        else if (t == FLOAT)
            return FLOAT;
        else if (t == LONG)
            return LONG;
        else
            return INT;
    }

    public static Type binaryExprTypeCheck(Type t1, Type t2) {
        if (!(isValidExprType(t1) && isValidExprType(t2)))
            Logger.error("type mismatch");
        if (t1 == DOUBLE || t2 == DOUBLE)
            return DOUBLE;
        else if (t1 == FLOAT || t2 == FLOAT)
            return FLOAT;
        else if (t1 == LONG || t2 == LONG)
            return LONG;
        else
            return INT;
    }

    public static boolean isValidExprType(Type type) {
        return type == BOOL || type == CHAR || type == INT || type == LONG || type == FLOAT || type == DOUBLE;
    }

    public static boolean isValidVariableType(Type type) {
        return isValidExprType(type) || type == STRING;
    }

    public static boolean isValidPrimitiveArrayType(Type type) {
        return isValidSwitchType(type) || type == LONG || type == FLOAT || type == DOUBLE;
    }

    public static boolean isValidSwitchType(Type type) {
        return type == BOOL || type == CHAR || type == INT;
    }

    public static void inferType(AbstractDescriptor descriptor, Type type) {
        if (descriptor.getType() == AUTO)
            descriptor.setType(type);
    }

}