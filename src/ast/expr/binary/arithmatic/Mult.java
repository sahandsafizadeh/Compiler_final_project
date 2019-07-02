package ast.expr.binary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Mult extends ArithmeticBinaryExpr {

    public Mult(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("multiply");
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL)
            return Opcodes.DMUL;
        else if (type == StructureType.FLOAT)
            return Opcodes.FMUL;
        else if (type == StructureType.LONG)
            return Opcodes.LMUL;
        else
            return Opcodes.IMUL;
    }
}
