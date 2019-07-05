package ast.expr.constant;

import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;

public class IntegerConstant extends Constant {

    public IntegerConstant(Type type) {
        super(type);
    }

    public IntegerConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return VariableType.INT;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
