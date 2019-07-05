package ast.expr.constant;

import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;

public abstract class Constant extends Expression {

    protected Object value;

    public Constant(Type type) {
        super(type);
    }

    public Constant(Object value) {
        this.value = value;
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
