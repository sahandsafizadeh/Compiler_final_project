package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.VariableType.*;

public class Sub extends ArithmeticBinaryExpr {

    public Sub(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("subtraction");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBL)
            return Opcodes.DSUB;
        else if (type == FLOAT)
            return Opcodes.FSUB;
        else if (type == LONG)
            return Opcodes.LSUB;
        else
            return Opcodes.ISUB;
    }

}
