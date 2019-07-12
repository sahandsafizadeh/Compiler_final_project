package ast.dcl.variable;

import ast.dcl.DCL;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.struct.StructureDescriptor;
import symtab.dscp.AbstractDescriptor;

public class StructDCL extends DCL {

    StructDCL(AbstractDescriptor descriptor) {
        this.descriptor = new StructureDescriptor();
        this.descriptor.setType(descriptor.getType());
        this.descriptor.setConst(descriptor.isConst());
        this.descriptor.setStackIndex(descriptor.getStackIndex());
        this.descriptor.setName(descriptor.getName());
    }

    @Override
    public void compile() {
        Logger.log("struct declaration");
        CodeGenerator.mVisit.visitTypeInsn(Opcodes.NEW, descriptor.getType().typeName());
        CodeGenerator.mVisit.visitInsn(Opcodes.DUP);
        CodeGenerator.mVisit.visitMethodInsn(Opcodes.INVOKESPECIAL, descriptor.getName(), "<init>", "()V", false);
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ASTORE, descriptor.getStackIndex());
    }

}
