package ast.expr.binary.logical;

import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.CastingType;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class EQ extends BinaryExpression {

    public EQ(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Logger.log("equality");

        return null;
    }

    @Override
    public int determineOp(Type type) {
        return Opcodes.IF_ICMPNE;
    }

}
