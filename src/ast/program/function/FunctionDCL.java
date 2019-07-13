package ast.program.function;

import ast.block.Block;
import ast.program.ProgramContent;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.function.FunctionDescriptor;
import symtab.dscp.function.Functions;

import static cg.CodeGenerator.mVisit;
import static cg.CodeGenerator.mainClw;

public class FunctionDCL extends ProgramContent {

    private Type type;
    private String id;
    private Block block;

    public FunctionDCL(Type type, String id, Block block) {
        this.type = type;
        this.id = id;
        this.block = block;
    }

    @Override
    public void compile() {
        Logger.log("function declaration");
        FunctionDescriptor descriptor = generate();
        if (checkOperation(descriptor))
            Functions.getInstance().addFunction(descriptor);
        else {
            if (!Functions.getInstance().contains(descriptor.getName(), descriptor.getParameterTypes()))
                Functions.getInstance().addFunction(descriptor);
            descriptor.setCompleteDCL(true);
            writeFunction(descriptor);
        }
    }

    private FunctionDescriptor generate() {
        FunctionDescriptor descriptor = new FunctionDescriptor();
        descriptor.setName(id);
        descriptor.setReturnType(type);
        descriptor.setParameters(FunctionArguments.getInstance().getArguments());
        return descriptor;
    }

    private boolean checkOperation(FunctionDescriptor descriptor) {
        if (Functions.getInstance().contains(descriptor.getName(), descriptor.getParameterTypes())) {
            if (descriptor.isCompleteDCL() || block == null)
                Logger.error("invalid function declaration");
            return false;
        } else
            return block == null;
    }

    private void writeFunction(FunctionDescriptor descriptor) {
        boolean isMain = descriptor.getName().equals("main") && descriptor.getReturnType() == Type.INT && descriptor.getParameterTypes().length == 0;
        mVisit = mainClw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, descriptor.getName(),
                isMain ? "([Ljava/lang/String;)V" : descriptor.getDescriptor(), null, null);
        mVisit.visitCode();
        block.init();
        TableStack.getInstance().newFunction(descriptor, isMain);
        block.markStart();
        block.compile();
        block.markEnd();
        if (descriptor.getReturnType() == Type.VOID || isMain)
            mVisit.visitInsn(Opcodes.RETURN);
        mVisit.visitMaxs(1, 1);
        mVisit.visitEnd();
    }

}
