package ast.expr.unary.arithmatic;

import ast.expr.unary.UnaryExpression;
import ast.type.Type;
import ast.type.VariableType;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public abstract class DualOperation extends UnaryExpression {

    VariableDescriptor descriptor;
    int ldrOp;
    int strOp;
    int addOp;
    int dupOp;

    public DualOperation(AbstractDescriptor descriptor) {
        super(descriptor.getType());
        if (descriptor instanceof VariableDescriptor)
            this.descriptor = (VariableDescriptor) descriptor;
        else
            Logger.error("undefined operation for arrays");
    }

    /**
     * In this class this method determines 4 operations so it doesn't return a specific value.
     *
     * @param type
     * @return
     */
    @Override
    public int determineOp(Type type) {
        if (type == VariableType.DOUBLE) {
            ldrOp = Opcodes.DLOAD;
            strOp = Opcodes.DSTORE;
            addOp = Opcodes.DADD;
            dupOp = Opcodes.DUP2;
        } else if (type == VariableType.FLOAT) {
            ldrOp = Opcodes.FLOAT;
            strOp = Opcodes.FSTORE;
            addOp = Opcodes.FADD;
            dupOp = Opcodes.DUP;
        } else if (type == VariableType.LONG) {
            ldrOp = Opcodes.LLOAD;
            strOp = Opcodes.LSTORE;
            addOp = Opcodes.LADD;
            dupOp = Opcodes.DUP2;
        } else {
            ldrOp = Opcodes.ILOAD;
            strOp = Opcodes.ISTORE;
            addOp = Opcodes.IADD;
            dupOp = Opcodes.DUP;
        }
        return 0;
    }

}
