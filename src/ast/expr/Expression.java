package ast.expr;

import ast.Node;
import ast.type.Type;

public abstract class Expression implements Node {
    private Type type;

    public Type getType() {
        return type;
    }
}
