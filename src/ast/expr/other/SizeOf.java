package ast.expr.other;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;

public class SizeOf extends Expression {

    private Type type;

    public SizeOf(Type type) {
        super(type);
        this.type = type;
    }

    @Override
    public Type getResultType() {
        return VariableType.INT;
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitVarInsn(Opcodes.BIPUSH, determineOp(type));
    }

    /**
     * In this class this method doesn't return opcode itself but returns argument of the instruction.
     *
     * @param type
     * @return
     */
    @Override
    public int determineOp(Type type) {
        if (type == VariableType.CHAR)
            return 2;
        else if (type == VariableType.LONG || type == VariableType.DOUBLE)
            return 8;
        else
            return 4;
    }

}
