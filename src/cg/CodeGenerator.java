package cg;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.ASMifier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.objectweb.asm.Opcodes.*;

public class CodeGenerator {

    public static final String OUTPUT_FILE = "Compiled.class";

    public static ClassWriter clWriter;
    public static MethodVisitor mVisit;


    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{"A.class"});
//        initClass();
//        writeFinalClassCode();
    }


    public static void initClass() {
        Logger.log("Initializing code generator");

        clWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        clWriter.visit(V1_8, ACC_PUBLIC, "Compiled", null, "java/lang/Object", null);
        mVisit = clWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mVisit.visitCode();
        mVisit.visitVarInsn(ALOAD, 0);
        mVisit.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mVisit.visitInsn(RETURN);
        mVisit.visitMaxs(1, 1);
        mVisit.visitEnd();
        MethodVisitor main = clWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        main.visitCode();
        main.visitEnd();
    }

    public static Label putLabel() {
        Label label = new Label();
        mVisit.visitLabel(label);
        return label;
    }

    public static void writeFinalClassCode() throws IOException {
        Logger.log("Writing the generated code into the executable output file");

        clWriter.visitEnd();
        try (OutputStream out = new FileOutputStream(OUTPUT_FILE)) {
            out.write(clWriter.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.log("Code generation finished");
        Logger.close();
    }

}
