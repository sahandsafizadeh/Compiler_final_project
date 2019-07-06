package ast.expr.unary.arithmatic;

import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import static cg.CodeGenerator.mVisit;

public class PrefixPlus2 extends DualOperation {

    public PrefixPlus2(AbstractDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void compile() {
        Logger.log("prefix plus plus");
        checkOperation();
        determineOp(getResultType());
        mVisit.visitInsn(Opcodes.ICONST_1);
        mVisit.visitVarInsn(ldrOp, descriptor.getStackIndex());
        mVisit.visitInsn(addOp);
        mVisit.visitInsn(dupOp);
        mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
    }

}
