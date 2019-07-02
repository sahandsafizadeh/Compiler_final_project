package ast.expr.binary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.type.StructureType;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Mod extends ArithmeticBinaryExpr {

    public Mod(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("remainder");
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        if (type == StructureType.DOUBL)
            return Opcodes.DREM;
        else if (type == StructureType.FLOAT)
            return Opcodes.FREM;
        else if (type == StructureType.LONG)
            return Opcodes.LREM;
        else
            return Opcodes.IREM;
    }

}
