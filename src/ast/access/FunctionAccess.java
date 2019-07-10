package ast.access;

import ast.access.function.FunctionAccessData;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.function.FunctionDescriptor;
import symtab.dscp.function.Functions;

public class FunctionAccess extends Access {

    public FunctionAccess(String id) {
        setDescriptor(id);
    }

    @Override
    public void setDescriptor(String id) {
        descriptor = Functions.getInstance().get(id, FunctionAccessData.getInstance().getParameters());
    }

    @Override
    public void compile() {
        if (descriptor == null)
            Logger.error("no function definition found");
        FunctionDescriptor descriptor = (FunctionDescriptor) getDescriptor();
        CodeGenerator.mVisit.visitMethodInsn(Opcodes.INVOKESTATIC, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getDescriptor(), false);
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
