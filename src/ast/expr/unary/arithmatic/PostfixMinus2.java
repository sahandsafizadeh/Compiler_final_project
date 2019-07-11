package ast.expr.unary.arithmatic;

import ast.access.Access;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static cg.CodeGenerator.mVisit;

public class PostfixMinus2 extends DualOperation {

    public PostfixMinus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("postfix minus minus");
        super.compile();
        mVisit.visitInsn(dupOp);
        mVisit.visitInsn(Opcodes.ICONST_M1);
        mVisit.visitInsn(addOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
