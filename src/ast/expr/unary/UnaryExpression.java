package ast.expr.unary;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;

public abstract class UnaryExpression extends Expression {

    protected Expression expr;

    public UnaryExpression(Type type) {
        super(type);
    }

    public UnaryExpression(Expression expr) {
        this.expr = expr;
    }

    @Override
    public Type getResultType() {
        return TypeChecker.unaryExprTypeCheck(expr.getResultType());
    }

}
