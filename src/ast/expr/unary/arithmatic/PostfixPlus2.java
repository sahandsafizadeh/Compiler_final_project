package ast.expr.unary.arithmatic;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.VariableAccess;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.Type.*;
import static cg.CodeGenerator.mVisit;

public class PostfixPlus2 extends DualOperation {

    public PostfixPlus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("postfix plus plus");
        super.compile();
        mVisit.visitInsn(dupOp);
        mVisit.visitInsn(constOp);
        mVisit.visitInsn(opcode);
        if (access instanceof VariableAccess) {

        } else if (access instanceof ArrayAccess) {

        } else {

        }
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE)
            opcode = Opcodes.DADD;
        else if (type == FLOAT)
            opcode = Opcodes.FADD;
        else if (type == LONG)
            opcode = Opcodes.LADD;
        else
            opcode = Opcodes.IADD;
        return super.determineOp(type);
    }

}
