package ast.expr.unary.arithmatic;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.StructureAccess;
import ast.access.VariableAccess;
import ast.expr.unary.UnaryExpression;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.AbstractDescriptor;

import static ast.type.Type.*;

public abstract class DualOperation extends UnaryExpression {

    AbstractDescriptor descriptor;
    Access access;
    int constOp;
    int strOp;
    int dupOp;
    int opcode;

    public DualOperation(Access access) {
        super();
        this.access = access;
        descriptor = (AbstractDescriptor) access.getDescriptor();
    }

    public void checkOperation() {
        if (descriptor.isConst() ||
                ((access instanceof StructureAccess) && (((StructureAccess) access)).getStructureVar().isConst()))
            Logger.error("constant variables can't be changed");
    }

    @Override
    public void compile() {
        checkOperation();
        if (access instanceof VariableAccess)
            determineOp(descriptor.getType());
        else if (access instanceof ArrayAccess)
            determineOp(Type.toSimple(descriptor.getType()));
        else
            determineOp(((StructureAccess) access).getStructureVar().getType());
        access.compile();
    }

    /**
     * In this class this method determines 4 operations so it doesn't return a specific value.
     *
     * @param type
     * @return
     */
    public int determineOp(Type type) {
        boolean varAccess = !(access instanceof ArrayAccess);
        if (type == DOUBLE) {
            constOp = Opcodes.DCONST_1;
            strOp = varAccess ? Opcodes.DSTORE : Opcodes.DASTORE;
            dupOp = Opcodes.DUP2;
        } else if (type == FLOAT) {
            constOp = Opcodes.FCONST_1;
            strOp = varAccess ? Opcodes.FSTORE : Opcodes.FASTORE;
            dupOp = Opcodes.DUP;
        } else if (type == LONG) {
            constOp = Opcodes.LCONST_1;
            strOp = varAccess ? Opcodes.LSTORE : Opcodes.LASTORE;
            dupOp = Opcodes.DUP2;
        } else if (type == INT) {
            constOp = Opcodes.ICONST_1;
            strOp = varAccess ? Opcodes.ISTORE : Opcodes.IASTORE;
            dupOp = Opcodes.DUP;
        } else
            Logger.error("invalid operation");
        return 0;
    }

}
