package cg;

import ast.type.StructureType;
import ast.type.Type;

import static ast.type.FunctionType.*;

public class TypeChecker {

    public static Type binaryExprTypeCheck(Type t1, Type t2) {
        if (!(isValidExprType(t1) && isValidExprType(t2)))
            Logger.error("type mismatch");

        Type type;
        if (t1 == DOUBL || t2 == DOUBL)
            type = StructureType.DOUBL;
        else if (t1 == FLOAT || t2 == FLOAT)
            type = StructureType.FLOAT;
        else if (t1 == LONG || t2 == LONG)
            type = StructureType.LONG;
        else
            type = StructureType.INT;
        return type;
    }

    private static boolean isValidExprType(Type type) {
        return type == BOOL || type == CHAR || type == INT || type == LONG || type == FLOAT || type == DOUBL;
    }

}