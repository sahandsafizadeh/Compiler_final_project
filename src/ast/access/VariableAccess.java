package ast.access;

import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

import static ast.type.VariableType.*;

public class VariableAccess extends Access {

    @Override
    public void setDescriptor(String id) {
        descriptor = TableStack.getInstance().find(id);
        if (!(descriptor instanceof VariableDescriptor))
            Logger.error("variable not declared");
    }

    @Override
    public void compile() {
        Logger.log("variable access load");
        VariableDescriptor descriptor = (VariableDescriptor) getDescriptor();
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
