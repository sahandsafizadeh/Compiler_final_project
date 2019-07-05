package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class BinaryXor extends ArithmeticBinaryExpr {

    public BinaryXor(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("binary xor");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.LONG)
            return Opcodes.LXOR;
        else if (type == VariableType.INT)
            return Opcodes.IXOR;
        else
            Logger.error("type mismatch");
        return 0;
    }

}
