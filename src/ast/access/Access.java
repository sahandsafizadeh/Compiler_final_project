package ast.access;

import ast.Node;
import ast.type.Type;
import symtab.dscp.Descriptor;

public abstract class Access implements Node {

    Descriptor descriptor;

    public abstract void setDescriptor(String id);

    public abstract int determineOp(Type type);

    public Descriptor getDescriptor() {
        return descriptor;
    }

}
