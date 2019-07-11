package ast.program.structure;

import ast.Node;
import ast.expr.Expression;
import ast.expr.constant.Constant;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.VariableDescriptor;

public class StructVarDCL implements Node {

    private Expression expr;
    private VariableDescriptor descriptor;

    public StructVarDCL(Expression expr) {
        this.expr = expr;
        descriptor = new VariableDescriptor();
    }

    public VariableDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public void compile() {
        Logger.log("declaring struct field");
        CodeGenerator.structClw
                .visitField(Opcodes.ACC_PUBLIC, descriptor.getName(), descriptor.getType().typeName(), null, null).visitEnd();
    }

    /**
     * structures should be initialized in class constructor so there will be two code generations for them.
     */
    public void init(String typeName) {
        Logger.log("initializing struct value in constructor");
        if (expr == null)
            return;
        else if (!(expr instanceof Constant))
            Logger.error("invalid struct field initialization");
        else {
            CodeGenerator.structMVisit.visitVarInsn(Opcodes.ALOAD, 0);
            expr.compile();
            Type type = TypeChecker.unaryExprTypeCheck(descriptor.getType());
            CodeGenerator.structMVisit.visitFieldInsn(Opcodes.PUTFIELD, typeName, descriptor.getName(), type.typeName());
        }
    }

}
