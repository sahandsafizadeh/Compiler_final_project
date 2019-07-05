package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.Type;
import cg.CodeGenerator;

public abstract class ArithmeticBinaryExpr extends BinaryExpression {

    private ArithmeticBinaryExpr(Type type) {
        super(type);
    }

    ArithmeticBinaryExpr(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public void compile() {
        Type resultType = getResultType();
        compileExprs(resultType);
        CodeGenerator.mVisit.visitInsn(determineOp(resultType));
    }

}
