package ast.program.structure;

import ast.program.ProgramContent;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import symtab.dscp.struct.StructureDescriptor;
import symtab.dscp.struct.Structures;

import java.util.List;

import static cg.CodeGenerator.structClw;
import static cg.CodeGenerator.structMVisit;

public class StructureDCL extends ProgramContent {

    private String typeName;
    private List<StructVarDCL> declarations;

    public StructureDCL(String typeName, List<StructVarDCL> declarations) {
        this.typeName = typeName;
        this.declarations = declarations;
    }

    @Override
    public void compile() {
        createDescriptor();
        structClw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        structClw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, typeName, null, CodeGenerator.SUPER_CLASS, null);
        declarations.forEach(StructVarDCL::compile);

        structMVisit = structClw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        structMVisit.visitCode();
        structMVisit.visitVarInsn(Opcodes.ALOAD, 0);
        structMVisit.visitMethodInsn(Opcodes.INVOKESPECIAL, CodeGenerator.SUPER_CLASS, "<init>", "()V", false);
        declarations.forEach(dcl -> dcl.init(typeName));
        structMVisit.visitInsn(Opcodes.RETURN);
        structMVisit.visitMaxs(1, 1);
        structMVisit.visitEnd();

        structClw.visitEnd();
        CodeGenerator.writeStructureClassCode(typeName + ".java");
    }

    private void createDescriptor() {
        StructureDescriptor descriptor = new StructureDescriptor();
        if (Structures.getInstance().typeExists(typeName))
            Logger.error("structure type already exists");
        descriptor.setType(new Type(typeName));
        declarations.stream().map(StructVarDCL::getDescriptor).forEach(descriptor::addVariable);
        Structures.getInstance().addStructure(descriptor);
    }

}
