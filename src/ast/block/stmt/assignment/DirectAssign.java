package ast.block.stmt.assignment;

import ast.access.*;
import ast.expr.Expression;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.structure.StructureDescriptor;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class DirectAssign extends Assignment {

    public DirectAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        Logger.log("direct assignment");
        checkOperation();
        TypeChecker.inferType(access.getDescriptor(), expr.getResultType());
        AbstractDescriptor descriptor = access.getDescriptor();
        int strCode = determineOp(descriptor.getType());
        if (access instanceof VariableAccess) {
            super.compile();
            CodeGenerator.mVisit.visitVarInsn(strCode, descriptor.getStackIndex());
        } else if (access instanceof GlobalVariableAccess) {
            super.compile();
            CodeGenerator.mVisit.visitFieldInsn(Opcodes.PUTSTATIC, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().getTypeName());
        }
        if (access instanceof ArrayAccess) {
            arrayStoreInit();
            super.compile();
            CodeGenerator.mVisit.visitInsn(strCode);
        } else {
            super.compile();
            StructureDescriptor structDscp = (StructureDescriptor) access.getDescriptor();
            VariableDescriptor structVar = structDscp.get(((StructureAccess) access).getId());
            CodeGenerator.mVisit.visitFieldInsn(Opcodes.PUTFIELD, descriptor.getType().getTypeName(), structVar.getName(), structVar.getType().getTypeName());
        }
    }

}
