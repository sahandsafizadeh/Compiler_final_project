package ast.dcl.variable;

import cg.Logger;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

public class VariableDCL extends VarDCL {

    public VariableDCL() {
        descriptor = new VariableDescriptor();
        descriptor.setConst(Variables.getInstance().isConstant());
        descriptor.setType(Variables.getInstance().getType());
    }

    @Override
    public void compile() {
        Logger.log("variable declaration");
        TableStack.getInstance().addVariable((VariableDescriptor) descriptor);
    }

}
