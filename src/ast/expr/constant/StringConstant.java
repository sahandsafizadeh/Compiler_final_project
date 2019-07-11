package ast.expr.constant;

import ast.type.Type;
import cg.CodeGenerator;

public class StringConstant extends Constant {

    public StringConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return Type.STRING;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
