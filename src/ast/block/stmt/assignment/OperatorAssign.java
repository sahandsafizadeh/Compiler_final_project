package ast.block.stmt.assignment;

import ast.access.*;
import ast.expr.Expression;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;
import symtab.dscp.struct.StructureDescriptor;
import symtab.dscp.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public abstract class OperatorAssign extends Assignment {

    protected int opcode;

    public OperatorAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        checkOperation();
        AbstractDescriptor descriptor = access.getDescriptor();
        int strCode = determineOp(access.getDescriptor().getType());
        if (access instanceof VariableAccess) {
            doArithmetic();
            CodeGenerator.mVisit.visitVarInsn(strCode, descriptor.getStackIndex());
        } else if (access instanceof GlobalVariableAccess) {
            doArithmetic();
            CodeGenerator.mVisit.visitFieldInsn(Opcodes.PUTSTATIC, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().getTypeName());
        } else if (access instanceof ArrayAccess) {
            arrayStoreInit();
            doArithmetic();
            CodeGenerator.mVisit.visitInsn(strCode);
        } else {
            doArithmetic();
            VariableDescriptor structVar = ((StructureDescriptor) descriptor).get(((StructureAccess) access).getId());
            CodeGenerator.mVisit.visitFieldInsn(Opcodes.PUTFIELD, descriptor.getName(), structVar.getName(), structVar.getType().getTypeName());
        }
    }

    private void doArithmetic() {
        access.compile();
        super.compile();
        CodeGenerator.mVisit.visitInsn(opcode);
    }

}
