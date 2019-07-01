package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Div extends BinaryExpression {

    public Div(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Logger.log("add");
        super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL)
            return Opcodes.DDIV;
        else if (type == StructureType.FLOAT)
            return Opcodes.FDIV;
        else if (type == StructureType.LONG)
            return Opcodes.LDIV;
        else
            return Opcodes.IDIV;
    }
}
