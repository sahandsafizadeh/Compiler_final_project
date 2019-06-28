import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class CodeGenerator {

    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{"A.class"});
    }

    private static final String OUTPUT_FILE = "Compiled.class";
    private static ClassWriter writer;

    public static void initClass() {
        System.out.println("Initializing code generator");
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        writer.visit(Opcodes.V1_8, ACC_PUBLIC, "a", null, "java/lang/Object", null);
        MethodVisitor main = writer.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "()V", null, null);
        main.visitCode();

        main.visitMaxs(1, 1);
        main.visitEnd();
    }

    public static void writeFinalClassCode() {
        System.out.println("Writing the generated code into the executable output file");
        try (OutputStream out = new FileOutputStream(OUTPUT_FILE)) {
            out.write(writer.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Code generation finished");
    }

}
