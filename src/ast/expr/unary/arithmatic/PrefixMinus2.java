package ast.expr.unary.arithmatic;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.VariableAccess;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.Type.*;
import static cg.CodeGenerator.mVisit;

public class PrefixMinus2 extends DualOperation {

    public PrefixMinus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("prefix minus minus");
        super.compile();
        mVisit.visitInsn(constOp);
        mVisit.visitInsn(opcode);
        mVisit.visitInsn(dupOp);
        if (access instanceof VariableAccess) {

        } else if (access instanceof ArrayAccess) {

        } else {

        }
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE)
            opcode = Opcodes.DSUB;
        else if (type == FLOAT)
            opcode = Opcodes.FSUB;
        else if (type == LONG)
            opcode = Opcodes.LSUB;
        else
            opcode = Opcodes.ISUB;
        return super.determineOp(type);
    }

}
