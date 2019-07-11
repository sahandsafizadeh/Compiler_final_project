package ast.access;

import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.GlobalVariableDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import static ast.type.Type.*;

public class VariableAccess extends Access {

    @Override
    public void setDescriptor(String id) {
        descriptor = TableStack.getInstance().find(id);
    }

    @Override
    public void compile() {
        Logger.log("variable access load");
        if (!(descriptor instanceof VariableDescriptor))
            Logger.error("variable not declared");
        VariableDescriptor descriptor = (VariableDescriptor) getDescriptor();
        if (descriptor instanceof GlobalVariableDescriptor)
            CodeGenerator.mVisit.visitFieldInsn(Opcodes.GETSTATIC, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().typeName());
        else
            CodeGenerator.mVisit.visitVarInsn(determineOp(descriptor.getType()), descriptor.getStackIndex());
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE)
            return Opcodes.DLOAD;
        else if (type == FLOAT)
            return Opcodes.FLOAT;
        else if (type == LONG)
            return Opcodes.LLOAD;
        else if (type == INT)
            return Opcodes.ILOAD;
        else
            return Opcodes.ALOAD;
    }

}
