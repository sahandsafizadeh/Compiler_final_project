package ast.dcl.variable;

import ast.dcl.DCL;
import ast.type.TypeChecker;
import cg.Logger;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

public class VariableDCL extends DCL {

    public VariableDCL() {
        descriptor = new VariableDescriptor();
        descriptor.setConst(Variables.getInstance().isConstant());
        descriptor.setType(Variables.getInstance().getType());
    }

    @Override
    public void compile() {
        Logger.log("variable declaration");
        if (TypeChecker.isValidVariableType(descriptor.getType()))
            TableStack.getInstance().addVariable(descriptor);
        else
            new StructDCL(descriptor).compile();
    }

}
