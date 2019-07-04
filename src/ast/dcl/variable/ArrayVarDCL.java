package ast.dcl.variable;

import ast.Node;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.ArrayDescriptor;

public class ArrayVarDCL extends VarDCL {

    private Expression expr;

    public ArrayVarDCL(Expression expr) {
        this.expr = expr;
        descriptor = new ArrayDescriptor();
        descriptor.setConst(Variables.getInstance().isConstant());
        descriptor.setType(Variables.getInstance().getType());
    }

    @Override
    public Node compile() {
        Logger.log("array declaration");
        Expression e = (Expression) expr.compile();
        Type type = e.getType();
        if (!TypeChecker.isValidSwitchType(type))
            Logger.error("invalid array size parameter");
        TableStack.getInstance().addArray((ArrayDescriptor) descriptor);
        CodeGenerator.mVisit.visitVarInsn(Opcodes.NEWARRAY, determineType(descriptor.getType()));
        return this;
    }

    private int determineType(Type type) {
        return 0;
    }

}
