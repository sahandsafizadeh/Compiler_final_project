package ast.expr.constant;

import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;

public class DoubleConstant extends Constant {

    public DoubleConstant(Type type) {
        super(type);
    }

    public DoubleConstant(Object value) {
        super(value);
    }

    @Override
    public Type getResultType() {
        return VariableType.DOUBL;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitLdcInsn(value);
    }

}
