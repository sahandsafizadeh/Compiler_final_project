package ast.expr.binary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class BinaryOr extends BinaryExpression {

    public BinaryOr(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("binary or");
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL || type == StructureType.FLOAT)
            Logger.error("type mismatch");
        else if (type == StructureType.LONG)
            return Opcodes.LOR;
        else
            return Opcodes.IOR;
        return 0;
    }

}
