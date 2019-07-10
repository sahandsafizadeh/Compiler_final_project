package ast.access;

import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

public class GlobalVariableAccess extends Access {

    @Override
    public void setDescriptor(String id) {
        descriptor = TableStack.getInstance().findGlobal(id);
        if (descriptor == null)
            Logger.error("no global variable found");
    }

    @Override
    public void compile() {
        Logger.log("global variable access load");
        VariableDescriptor descriptor = (VariableDescriptor) getDescriptor();
        CodeGenerator.mVisit.visitFieldInsn(Opcodes.GETSTATIC, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().getTypeName());
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
