package ast.block.stmt.assignment;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.expr.Expression;
import cg.CodeGenerator;
import cg.Logger;

public class DirectAssign extends Assignment {

    public DirectAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        Logger.log("direct assignment");
        int strCode = determineOp(access.getDescriptor().getType());
        if (access instanceof ArrayAccess) {
            arrayStoreInit();
            super.compile();
            CodeGenerator.mVisit.visitInsn(strCode);
        } else {
            super.compile();
            CodeGenerator.mVisit.visitVarInsn(strCode, access.getDescriptor().getStackIndex());
        }
    }

}
