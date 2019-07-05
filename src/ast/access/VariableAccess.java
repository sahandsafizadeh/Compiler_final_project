package ast.access;

import cg.Logger;
import symtab.TableStack;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class VariableAccess extends Access {

    @Override
    public void setDescriptor(String id) {
        descriptor = (AbstractDescriptor) TableStack.getInstance().find(id);
        if (!(descriptor instanceof VariableDescriptor))
            Logger.error("variable not declared");
    }

    @Override
    public void compile() {
    }

}
