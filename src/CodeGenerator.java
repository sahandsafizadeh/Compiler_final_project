import descriptor.Descriptor;
import descriptor.VariableDescriptor;
import org.objectweb.asm.*;
import org.objectweb.asm.util.ASMifier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

import static org.objectweb.asm.Opcodes.*;

public class CodeGenerator {

    public static boolean inDCL = false;

    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{"A.class"});
//        initClass();
//        writeFinalClassCode();
    }

    private static final Stack<Descriptor> SEMANTIC_STACK = new Stack<>();
    private static final String OUTPUT_FILE = "Compiled.class";
    private static ClassWriter clw;
    private static FieldVisitor fVisitor;
    private static MethodVisitor mVisitor;

    public static void initClass() {
        Logger.log("Initializing code generator");

        clw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        clw.visit(Opcodes.V1_8, ACC_PUBLIC, "Compiled", null, "java/lang/Object", null);
        mVisitor = clw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mVisitor.visitCode();
        mVisitor.visitVarInsn(ALOAD, 0);
        mVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mVisitor.visitInsn(RETURN);
        mVisitor.visitMaxs(1, 1);
        mVisitor.visitEnd();
//        MethodVisitor main = clw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
//        main.visitCode();
//        main.visitEnd();
    }

    public static void dclGlobalVar() {

    }

    public static void add() {
        Logger.log("Operating add");

        VariableDescriptor o1 = (VariableDescriptor) SEMANTIC_STACK.pop();
        VariableDescriptor o2 = (VariableDescriptor) SEMANTIC_STACK.pop();
        OperationSpecifier os = checkType(o1.getType(), o2.getType());
        switch (os.strcode) {
            case ISTORE:
                os.opcode = IADD;
                break;
            case LSTORE:
                os.opcode = LADD;
                break;
            case FSTORE:
                os.opcode = FADD;
                break;
            case DSTORE:
                os.opcode = DADD;
                break;
        }

        mVisitor.visitLdcInsn(o1.getValue());
        mVisitor.visitVarInsn(os.strcode, 1);
        mVisitor.visitLdcInsn(o2.getValue());
        mVisitor.visitVarInsn(os.strcode, 1 + os.spacing);

        mVisitor.visitVarInsn(os.ldrcode, 1);
        mVisitor.visitVarInsn(os.ldrcode, 1 + os.spacing);
        mVisitor.visitInsn(os.opcode);

        mVisitor.visitVarInsn(os.strcode, 1 + 2 * os.spacing);

        Logger.log("Add operation finished");
    }

    public static void mult() {

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

    private static OperationSpecifier checkType(Class<?> type1, Class<?> type2) {
        if (!isValidType(type1) || !isValidType(type2))
            error("type mismatch");

        OperationSpecifier os = new OperationSpecifier();
        if (type1 == boolean.class || type2 == boolean.class) {
            if (type1 != type2)
                error("type mismatch");
            os.strcode = ISTORE;
            os.ldrcode = ILOAD;
            os.spacing = 1;
        } else if (type1 == double.class || type2 == double.class) {
            os.strcode = DSTORE;
            os.ldrcode = DLOAD;
            os.spacing = 2;
        } else if (type1 == float.class || type2 == float.class) {
            os.strcode = FSTORE;
            os.ldrcode = FLOAD;
            os.spacing = 1;
        } else if (type1 == long.class || type2 == long.class) {
            os.strcode = LSTORE;
            os.ldrcode = LLOAD;
            os.spacing = 2;
        } else {
            os.strcode = ISTORE;
            os.ldrcode = ILOAD;
            os.spacing = 1;
        }
        return os;
    }

    private static boolean isValidType(Class<?> type) {
        return type == double.class || type == float.class || type == long.class || type == int.class
                || type == short.class || type == byte.class || type == char.class || type == boolean.class;
    }

    private static void error(String message) {
        Logger.log(message);
        throw new RuntimeException();
    }
}

class OperationSpecifier {
    int opcode;
    int strcode;
    int ldrcode;
    int spacing;
}