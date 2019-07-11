package ast.access;

import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.struct.StructureDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class StructureAccess extends Access {

    private VariableDescriptor structureVar;

    public StructureAccess(Access access) {
        descriptor = access.getDescriptor();
    }

    @Override
    public void setDescriptor(String id) {
        structureVar = ((StructureDescriptor) descriptor).get(id);
    }

    @Override
    public void compile() {
        Logger.log("struct access load");
        StructureDescriptor descriptor = (StructureDescriptor) getDescriptor();
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, descriptor.getStackIndex());
        CodeGenerator.mVisit.visitFieldInsn(Opcodes.GETFIELD, descriptor.getType().typeName(), structureVar.getName(), structureVar.getType().typeName());
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
