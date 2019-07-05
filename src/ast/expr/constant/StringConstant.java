package ast.expr.constant;

import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;

public class StringConstant extends Constant {

    public StringConstant(Type type) {
        super(type);
    }

    public StringConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return VariableType.STRING;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
