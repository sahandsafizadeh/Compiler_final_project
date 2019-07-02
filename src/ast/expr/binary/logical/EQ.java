package ast.expr.binary.logical;

import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class EQ extends BinaryExpression {

    public EQ(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("add");
        super.compile();
        return null;
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
