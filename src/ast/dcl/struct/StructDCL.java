package ast.dcl.struct;

import ast.dcl.VarDCL;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.structure.StructureDescriptor;
import symtab.dscp.variable.AbstractDescriptor;

public class StructDCL extends VarDCL {

    public StructDCL(AbstractDescriptor descriptor) {
        this.descriptor = new StructureDescriptor();
        this.descriptor.setType(descriptor.getType());
        this.descriptor.setConst(descriptor.isConst());
        this.descriptor.setStackIndex(descriptor.getStackIndex());
        this.descriptor.setName(descriptor.getName());
    }

    @Override
    public void compile() {
        Logger.log("structure declaration");
        CodeGenerator.mVisit.visitTypeInsn(Opcodes.NEW, descriptor.getType().getTypeName());
        CodeGenerator.mVisit.visitInsn(Opcodes.DUP);
        CodeGenerator.mVisit.visitMethodInsn(Opcodes.INVOKESPECIAL, descriptor.getName(), "<init>", "()V", false);
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ASTORE, descriptor.getStackIndex());
    }

}
