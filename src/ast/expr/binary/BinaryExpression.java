package ast.expr.binary;

import ast.Node;
import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import ast.type.TypeChecker;

public class BinaryExpression extends Expression {

    protected Expression expr1;
    protected Expression expr2;

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
        return null;
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
