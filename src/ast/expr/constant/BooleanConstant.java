package ast.expr.constant;

import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;

public class BooleanConstant extends Constant {

    public BooleanConstant(Type type) {
        super(type);
    }

    public BooleanConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return VariableType.INT;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitInsn((Boolean) this.value ? Opcodes.ICONST_1 : Opcodes.ICONST_0);
    }

}
