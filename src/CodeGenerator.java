import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class CodeGenerator {

    public static void main(String[] args) throws IOException {
//        ASMifier.main(new String[]{"A.class"});
//        initClass();
//        writeFinalClassCode();
    }

    private static final String OUTPUT_FILE = "Compiled.class";
    private static ClassWriter clw;

    public static void initClass() {
        Logger.log("Initializing code generator");

        clw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        clw.visit(Opcodes.V1_8, ACC_PUBLIC, "Compiled", null, "java/lang/Object", null);
    }

    public static void writeFinalClassCode() throws IOException {
        Logger.log("Writing the generated code into the executable output file");

        clw.visitEnd();
        try (OutputStream out = new FileOutputStream(OUTPUT_FILE)) {
            out.write(clw.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.log("Code generation finished");
        Logger.close();
    }

}
