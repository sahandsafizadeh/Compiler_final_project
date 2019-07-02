package ast.expr.constant;

import ast.Node;
import ast.type.Type;

public class StringConstant extends Constant {

    public StringConstant(Type type) {
        super(type);
    }

    public StringConstant(Object value) {
        super(value);
    }

    @Override
    public Node compile() {
        super.compile();
        return new StringConstant(null);//this type is invalid for expressions
    }

}
