package ast.expr.unary.arithmatic;

import ast.access.Access;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static cg.CodeGenerator.mVisit;

public class PrefixMinus2 extends DualOperation {

    public PrefixMinus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("prefix minus minus");
        super.compile();
        mVisit.visitInsn(Opcodes.ICONST_M1);
        mVisit.visitInsn(addOp);
        mVisit.visitInsn(dupOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
