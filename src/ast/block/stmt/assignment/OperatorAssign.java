package ast.block.stmt.assignment;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.expr.Expression;
import cg.CodeGenerator;

public abstract class OperatorAssign extends Assignment {

    protected int opcode;

    public OperatorAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        int strCode = determineOp(access.getDescriptor().getType());
        if (access instanceof ArrayAccess) {
            arrayStoreInit();
            doArithmetic();
            CodeGenerator.mVisit.visitInsn(strCode);
        } else {
            doArithmetic();
            CodeGenerator.mVisit.visitVarInsn(strCode, access.getDescriptor().getStackIndex());
        }
    }

    private void doArithmetic() {
        access.compile();
        super.compile();
        CodeGenerator.mVisit.visitInsn(opcode);
    }

}
