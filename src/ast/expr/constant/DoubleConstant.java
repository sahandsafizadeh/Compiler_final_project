package ast.expr.constant;

import ast.type.Type;
import cg.CodeGenerator;

public class DoubleConstant extends Constant {

    public DoubleConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return Type.DOUBLE;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
