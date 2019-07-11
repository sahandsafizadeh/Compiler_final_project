package ast.access;

import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.array.ArrayDescriptor;

import static ast.type.Type.*;

public class ArrayAccess extends Access {

    private Expression index;

    public ArrayAccess(Expression index) {
        this.index = index;
    }

    public Expression getIndex() {
        return index;
    }

    @Override
    public void setDescriptor(String id) {
        descriptor = TableStack.getInstance().find(id);
    }

    @Override
    public void compile() {
        Logger.log("array access load");
        if (!(descriptor instanceof ArrayDescriptor))
            Logger.error("array not declared");
        ArrayDescriptor descriptor = (ArrayDescriptor) getDescriptor();
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, descriptor.getStackIndex());
        if (index.getResultType() != INT)
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
