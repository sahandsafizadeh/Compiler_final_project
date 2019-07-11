package ast.expr.constant;

import ast.type.Type;
import cg.CodeGenerator;

public class IntegerConstant extends Constant {

    public IntegerConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return Type.INT;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
