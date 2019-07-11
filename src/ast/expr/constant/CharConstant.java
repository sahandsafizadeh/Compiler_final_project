package ast.expr.constant;

import ast.type.Type;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;

public class CharConstant extends Constant {

    public CharConstant(Object value) {
        super(value);
        StringBuilder literal = (StringBuilder) value;
        literal.deleteCharAt(0);
        literal.deleteCharAt(literal.length() - 1);
        this.value = literal.charAt(0);
    }

    @Override
    public Type getResultType() {
        return Type.INT;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitVarInsn(Opcodes.BIPUSH, (int) ((char) value));
    }

}
