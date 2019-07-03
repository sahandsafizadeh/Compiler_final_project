package ast.expr.unary.logical;

import ast.Node;
import ast.expr.Expression;
import ast.expr.unary.UnaryExpression;
import ast.type.Type;

public class Not extends UnaryExpression {

    public Not(Expression expr) {
        super(expr);
    }

    @Override
    public Node compile() {
        //todo
        return super.compile();
    }

    @Override
    public int determineOp(Type type) {
        return super.determineOp(type);
    }

}
