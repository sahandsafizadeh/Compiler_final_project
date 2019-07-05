package ast.expr.other;

import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.VariableDescriptor;

public class VariablePush extends Expression {

    private VariableDescriptor descriptor;

    public VariablePush(VariableDescriptor descriptor) {
        super(descriptor.getType());
        this.descriptor = descriptor;
    }

    @Override
    public Type getResultType() {
        if (!TypeChecker.isValidVariableType(descriptor.getType()))
            Logger.error("type mismatch");
        return descriptor.getType();
    }

    @Override
    public void compile() {
        CodeGenerator.mVisit.visitVarInsn(determineOp(getResultType()), descriptor.getStackIndex());
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.DOUBLE)
            return Opcodes.DLOAD;
        else if (type == VariableType.FLOAT)
            return Opcodes.FLOAT;
        else if (type == VariableType.LONG)
            return Opcodes.LLOAD;
        else if (type == VariableType.STRING)
            return Opcodes.ALOAD;
        else
            return Opcodes.ILOAD;
    }

}
