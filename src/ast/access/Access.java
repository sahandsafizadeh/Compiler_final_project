package ast.access;

import ast.Node;
import ast.type.Type;
import symtab.dscp.variable.AbstractDescriptor;

public abstract class Access implements Node {

    AbstractDescriptor descriptor;

    public abstract void setDescriptor(String id);

    public abstract int determineOp(Type type);

    public AbstractDescriptor getDescriptor() {
        return descriptor;
    }

}
