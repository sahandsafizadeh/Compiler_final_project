package ast.access;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.ArrayDescriptor;

import static ast.type.VariableType.*;

public class ArrayAccess extends Access {

    private Expression index;

    public ArrayAccess(Expression index) {
        this.index = index;
    }

    @Override
    public void setDescriptor(String id) {
        descriptor = (AbstractDescriptor) TableStack.getInstance().find(id);
        if (!(descriptor instanceof ArrayDescriptor))
            Logger.error("array not declared");
    }

    @Override
    public void compile() {
        Logger.log("array access load");
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, descriptor.getStackIndex());
        if (index.getResultType() != VariableType.INT)
            Logger.error("arrays can only be accessed using integer types");
        index.compile();
        CodeGenerator.mVisit.visitInsn(determineOp(descriptor.getType()));
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE)
            return Opcodes.DALOAD;
        else if (type == FLOAT)
            return Opcodes.FALOAD;
        else if (type == LONG)
            return Opcodes.LALOAD;
        else if (type == INT)
            return Opcodes.IALOAD;
        else
            return Opcodes.AALOAD;
    }

}
