package ast.dcl.variable;

import ast.dcl.VarDCL;
import ast.dcl.Variables;
import ast.dcl.struct.StructDCL;
import ast.type.TypeChecker;
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
        if (TypeChecker.isValidVariableType(descriptor.getType()))
            TableStack.getInstance().addVariable((VariableDescriptor) descriptor);
        else
            new StructDCL(descriptor).compile();
    }

}
