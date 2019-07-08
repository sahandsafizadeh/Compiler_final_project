package ast.program.structure;

import ast.program.ProgramContent;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

import static cg.CodeGenerator.structClw;

public class StructureDCL extends ProgramContent {

    private String typeName;
    private List<StructVarDCL> dcls;

    public StructureDCL(String typeName, List<StructVarDCL> dcls) {
        this.typeName = typeName;
        this.dcls = dcls;
    }

    @Override
    public void compile() {
        structClw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        dcls.forEach(StructVarDCL::compile);
        MethodVisitor structMvisit = structClw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        structMvisit.visitCode();
        structMvisit.visitCode();
        structMvisit.visitVarInsn(Opcodes.ALOAD, 0);
        structMvisit.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        dcls.forEach(dcl -> dcl.init(typeName));
        structMvisit.visitInsn(Opcodes.RETURN);
        structMvisit.visitMaxs(1, 1);
        structMvisit.visitEnd();
    }

}
