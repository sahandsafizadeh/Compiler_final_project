package ast.expr.unary.arithmatic;

import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.AbstractDescriptor;

import static cg.CodeGenerator.mVisit;

public class PostfixMinus2 extends DualOperation {

    public PostfixMinus2(AbstractDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void compile() {
        Logger.log("postfix minus minus");
        checkOperation();
        determineOp(getResultType());
        mVisit.visitVarInsn(ldrOp, descriptor.getStackIndex());
        mVisit.visitInsn(dupOp);
        mVisit.visitInsn(Opcodes.ICONST_M1);
        mVisit.visitInsn(addOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
