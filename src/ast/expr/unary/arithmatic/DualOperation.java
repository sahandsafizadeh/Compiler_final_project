package ast.expr.unary.arithmatic;

import ast.access.Access;
import ast.expr.unary.UnaryExpression;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.AbstractDescriptor;

import static ast.type.Type.*;

public abstract class DualOperation extends UnaryExpression {

    AbstractDescriptor descriptor;
    Access access;
    int strOp;
    int addOp;
    int dupOp;

    public DualOperation(Access access) {
        super();
        this.access = access;
        descriptor = (AbstractDescriptor) access.getDescriptor();
    }

    public void checkOperation() {
        if (descriptor.isConst())
            Logger.error("constant variables can't be changed");
    }

    @Override
    public void compile() {
        checkOperation();
        determineOp(getResultType());
        access.compile();
    }

    /**
     * In this class this method determines 4 operations so it doesn't return a specific value.
     *
     * @param type
     * @return
     */
    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE) {
            strOp = Opcodes.DSTORE;
            addOp = Opcodes.DADD;
            dupOp = Opcodes.DUP2;
        } else if (type == FLOAT) {
            strOp = Opcodes.FSTORE;
            addOp = Opcodes.FADD;
            dupOp = Opcodes.DUP;
        } else if (type == LONG) {
            strOp = Opcodes.LSTORE;
            addOp = Opcodes.LADD;
            dupOp = Opcodes.DUP2;
        } else {
            strOp = Opcodes.ISTORE;
            addOp = Opcodes.IADD;
            dupOp = Opcodes.DUP;
        }
        return 0;
    }

}
