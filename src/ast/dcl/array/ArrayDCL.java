package ast.dcl.array;

import ast.dcl.DCL;
import ast.dcl.variable.Variables;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.array.ArrayDescriptor;

import static ast.type.VariableType.*;

public class ArrayDCL extends DCL {

    private Expression expr;

    public ArrayDCL(Expression expr) {
        this.expr = expr;
        descriptor = new ArrayDescriptor();
        descriptor.setConst(Variables.getInstance().isConstant());
        descriptor.setType(Variables.getInstance().getType());
    }

    @Override
    public void compile() {
        Logger.log("array declaration");
        if (!TypeChecker.isValidSwitchType(expr.getResultType()))
            Logger.error("invalid array size parameter");
        expr.compile();
        TableStack.getInstance().addArray((ArrayDescriptor) descriptor);

        Type type = descriptor.getType();
        if (TypeChecker.isValidPrimitiveArrayType(type))
            CodeGenerator.mVisit.visitVarInsn(Opcodes.NEWARRAY, determinePrimitiveType(type));
        else
            Logger.error("unsupported array type");
    }

    private int determinePrimitiveType(Type type) {
        if (type == DOUBLE)
            return Opcodes.T_DOUBLE;
        else if (type == FLOAT)
            return Opcodes.T_FLOAT;
        else if (type == LONG)
            return Opcodes.T_LONG;
        else
            return Opcodes.T_INT;
    }

}
