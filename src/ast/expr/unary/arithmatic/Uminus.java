package ast.expr.unary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.expr.unary.UnaryExpression;
import ast.type.Type;
import ast.type.TypeChecker;
import ast.type.VariableType;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;

public class Uminus extends UnaryExpression {

    public Uminus(Expression expr) {
        super(expr);
    }

    @Override
    public Node compile() {
        Type resultType = TypeChecker.unaryExprTypeCheck(expr.getType());
        CodeGenerator.mVisit.visitInsn(determineOp(resultType));
        return new UnaryExpression(resultType);
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.DOUBL)
            return Opcodes.DNEG;
        else if (type == VariableType.FLOAT)
            return Opcodes.FNEG;
        else if (type == VariableType.LONG)
            return Opcodes.LNEG;
        else
            return Opcodes.INEG;
    }

}
