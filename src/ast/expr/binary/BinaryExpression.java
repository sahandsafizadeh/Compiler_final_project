package ast.expr.binary;

import ast.Node;
import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import ast.type.TypeChecker;

public class BinaryExpression extends Expression {

    private Expression expr1;
    private Expression expr2;

    public BinaryExpression(Type type) {
        super(type);
    }

    public BinaryExpression(Expression expr1, Expression expr2) {
        super();
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Node compile() {
        Type t1 = expr1.getType();
        Type t2 = expr2.getType();
        Type resultType = TypeChecker.binaryExprTypeCheck(t1, t2);
        CodeGenerator.mVisit.visitInsn(determineOp(resultType));
        return new BinaryExpression(resultType);
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
