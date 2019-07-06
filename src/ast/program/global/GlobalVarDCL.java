package ast.program.global;

import ast.program.ProgramContent;
import ast.type.Type;
import cg.Logger;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

public class GlobalVarDCL extends ProgramContent {

    private Type type;
    private String id;

    public GlobalVarDCL(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void compile() {
        Logger.log("global variable declaration");
        VariableDescriptor descriptor = new VariableDescriptor();
        descriptor.setName(id);
        descriptor.setType(type);
        descriptor.setConst(false);
        TableStack.getInstance().addGlobalVariable(descriptor);
    }

}
