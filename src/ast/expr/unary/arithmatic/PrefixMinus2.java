package ast.expr.unary.arithmatic;

import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.VariableDescriptor;

import static cg.CodeGenerator.mVisit;

public class PrefixMinus2 extends DualOperation {

    public PrefixMinus2(VariableDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void compile() {
        Logger.log("prefix minus minus");
        checkOperation();
        determineOp(getResultType());
        mVisit.visitInsn(Opcodes.ICONST_M1);
        mVisit.visitVarInsn(ldrOp, descriptor.getStackIndex());
        mVisit.visitInsn(addOp);
        mVisit.visitInsn(dupOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
