package ast.expr.other;

import ast.access.Access;
import ast.access.VariableAccess;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.AbstractDescriptor;

import static ast.type.VariableType.*;

public class Variable extends Expression {

    private Access access;
    private AbstractDescriptor descriptor;

    public Variable(Access access) {
        super(access.getDescriptor().getType());
        this.access = access;
        descriptor = access.getDescriptor();
    }

    @Override
    public Type getResultType() {
        if (!TypeChecker.isValidVariableType(descriptor.getType()))
            Logger.error("type mismatch");
        return descriptor.getType();
    }

    @Override
    public void compile() {
        int opcode = determineOp(getResultType());
        if (access instanceof VariableAccess)
            CodeGenerator.mVisit.visitVarInsn(opcode, descriptor.getStackIndex());
        else
            CodeGenerator.mVisit.visitInsn(opcode);
    }

    @Override
    public int determineOp(Type type) {
        boolean varAccess = access instanceof VariableAccess;
        if (type == DOUBLE)
            return varAccess ? Opcodes.DLOAD : Opcodes.DALOAD;
        else if (type == FLOAT)
            return varAccess ? Opcodes.FLOAT : Opcodes.FALOAD;
        else if (type == LONG)
            return varAccess ? Opcodes.LLOAD : Opcodes.LALOAD;
        else if (type == STRING)
            return varAccess ? Opcodes.ALOAD : Opcodes.AALOAD;
        else
            return varAccess ? Opcodes.ILOAD : Opcodes.IALOAD;
    }

}
