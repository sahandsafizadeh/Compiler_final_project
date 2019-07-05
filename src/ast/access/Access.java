package ast.access;

import ast.Node;
import symtab.dscp.variable.AbstractDescriptor;

public abstract class Access implements Node {

    AbstractDescriptor descriptor;

    public abstract void setDescriptor(String id);

    public AbstractDescriptor getDescriptor() {
        return descriptor;
    }

}
