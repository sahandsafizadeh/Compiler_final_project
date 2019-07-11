package ast.block.stmt.function;

import ast.access.FunctionAccess;
import ast.block.BlockContent;
import ast.type.FunctionType;
import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.function.FunctionDescriptor;

public class FunctionCall extends BlockContent {

    private FunctionAccess access;

    public FunctionCall(FunctionAccess access) {
        this.access = access;
    }

    @Override
    public void compile() {
        Logger.log("function call statement");
        access.compile();
        Type returnType = ((FunctionDescriptor) access.getDescriptor()).getReturnType();
        if (returnType != FunctionType.VOID)
            CodeGenerator.mVisit.visitInsn(!(returnType == VariableType.DOUBLE || returnType == VariableType.LONG) ? Opcodes.POP : Opcodes.POP2);
    }

}
