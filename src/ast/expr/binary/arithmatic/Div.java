package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Div extends ArithmeticBinaryExpr {

    public Div(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("division");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.DOUBL)
            return Opcodes.DDIV;
        else if (type == VariableType.FLOAT)
            return Opcodes.FDIV;
        else if (type == VariableType.LONG)
            return Opcodes.LDIV;
        else
            return Opcodes.IDIV;
    }

}
