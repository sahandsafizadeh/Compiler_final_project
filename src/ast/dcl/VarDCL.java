package ast.dcl;

import ast.Node;
import symtab.dscp.variable.AbstractDescriptor;

public abstract class VarDCL implements Node {

    protected String id;
    protected AbstractDescriptor descriptor;

    public void setId(String id) {
        this.id = id;
    }

    public AbstractDescriptor getDescriptor() {
        return descriptor;
    }

}
