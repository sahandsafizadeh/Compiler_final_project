package ast.expr.binary.arithmatic;

import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;

public class ArithmeticBinaryExpr extends BinaryExpression {

    private ArithmeticBinaryExpr(Type type) {
        super(type);
    }

    ArithmeticBinaryExpr(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Type getResultType() {
        return TypeChecker.binaryExprTypeCheck(expr1.getResultType(), expr2.getResultType());
    }

    @Override
    public void compile() {
        Type resultType = getResultType();
        expr1.compile();
        expr1.doCastCompile(resultType);
        expr2.compile();
        expr2.doCastCompile(resultType);
        CodeGenerator.mVisit.visitInsn(determineOp(resultType));
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
