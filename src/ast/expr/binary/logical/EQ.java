package ast.expr.binary.logical;

import ast.expr.Expression;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.VariableType.*;

public class EQ extends LogicalBinaryExpr {

    public EQ(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("equality");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE) {
            opCode = Opcodes.IFNE;
            compareCode = Opcodes.DCMPG;
        } else if (type == FLOAT) {
            opCode = Opcodes.IFNE;
            compareCode = Opcodes.FCMPG;
        } else if (type == LONG) {
            opCode = Opcodes.IFNE;
            compareCode = Opcodes.LCMP;
        } else
            opCode = Opcodes.IF_ICMPNE;
        return 0;
    }

}
