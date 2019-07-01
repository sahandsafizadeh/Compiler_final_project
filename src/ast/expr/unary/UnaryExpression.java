package ast.expr.unary;

import ast.expr.Expression;

public abstract class UnaryExpression extends Expression {

    public Expression expr;

    public UnaryExpression(Expression expr) {
        this.expr = expr;
    }

    
}
