package ast.expr.binary.arithmatic;


import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Add extends BinaryExpression {

    public Add(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("add");
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL)
            return Opcodes.DADD;
        else if (type == StructureType.FLOAT)
            return Opcodes.FADD;
        else if (type == StructureType.LONG)
            return Opcodes.LADD;
        else
            return Opcodes.IADD;
    }

}
