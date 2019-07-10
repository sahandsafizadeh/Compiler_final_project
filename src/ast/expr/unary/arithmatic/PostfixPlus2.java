package ast.expr.unary.arithmatic;

import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.AbstractDescriptor;

import static cg.CodeGenerator.mVisit;

public class PostfixPlus2 extends DualOperation {

    public PostfixPlus2(AbstractDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void compile() {
        Logger.log("postfix plus plus");
        checkOperation();
        determineOp(getResultType());
        mVisit.visitVarInsn(ldrOp, descriptor.getStackIndex());
        mVisit.visitInsn(dupOp);
        mVisit.visitInsn(Opcodes.ICONST_1);
        mVisit.visitInsn(addOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
