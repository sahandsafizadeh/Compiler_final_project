package ast.expr.unary.arithmatic;

import ast.access.Access;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static cg.CodeGenerator.mVisit;

public class PrefixPlus2 extends DualOperation {

    public PrefixPlus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("prefix plus plus");
        super.compile();
        mVisit.visitInsn(Opcodes.ICONST_1);
        mVisit.visitInsn(addOp);
        mVisit.visitInsn(dupOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
