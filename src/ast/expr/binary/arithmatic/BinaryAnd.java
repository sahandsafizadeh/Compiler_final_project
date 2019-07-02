package ast.expr.binary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class BinaryAnd extends BinaryExpression {

    public BinaryAnd(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("binary and");
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL || type == StructureType.FLOAT)
            Logger.error("type mismatch");
        else if (type == StructureType.LONG)
            return Opcodes.LAND;
        else
            return Opcodes.IAND;
        return 0;
    }

}