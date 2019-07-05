package ast.access;

import ast.expr.Expression;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.ArrayDescriptor;

public class ArrayAccess extends Access {

    private Expression index;

    public ArrayAccess(Expression index) {
        this.index = index;
    }

    @Override
    public void setDescriptor(String id) {
        descriptor = (AbstractDescriptor) TableStack.getInstance().find(id);
        if (!(descriptor instanceof ArrayDescriptor))
            Logger.error("variable not declared");
    }

    @Override
    public void compile() {
        Logger.log("array access");
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, descriptor.getStackIndex());
        if (index.getResultType() != VariableType.INT)
            Logger.error("arrays can only be accessed using integer types");
        index.compile();
    }

}
