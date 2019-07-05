package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Mod extends ArithmeticBinaryExpr {

    public Mod(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("remainder");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.DOUBL)
            return Opcodes.DREM;
        else if (type == VariableType.FLOAT)
            return Opcodes.FREM;
        else if (type == VariableType.LONG)
            return Opcodes.LREM;
        else
            return Opcodes.IREM;
    }

}
