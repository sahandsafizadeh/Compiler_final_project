package ast.dcl;

import ast.Node;
import symtab.dscp.AbstractDescriptor;

public abstract class DCL implements Node {

    protected String id;
    protected AbstractDescriptor descriptor;

    public void setId(String id) {
        this.id = id;
    }

    public AbstractDescriptor getDescriptor() {
        return descriptor;
    }

}
