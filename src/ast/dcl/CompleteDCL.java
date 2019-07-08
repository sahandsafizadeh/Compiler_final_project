package ast.dcl;

import ast.block.BlockContent;
import ast.dcl.array.ArrayVarDCL;
import ast.dcl.variable.VariableDCL;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.VariableType.*;

public class CompleteDCL extends BlockContent {

    private VarDCL dcl;
    private Expression expr;

    public CompleteDCL(VarDCL dcl, Expression expr) {
        this.dcl = dcl;
        this.expr = expr;
    }

    @Override
    public void compile() {
        Logger.log("complete local variable declaration");
        dcl.compile();
        if (expr == null)
            return;
        if (!(dcl instanceof VariableDCL))
            Logger.error("invalid initialization for array");
        else {
            Type type = dcl.getDescriptor().getType();
            if (!TypeChecker.isValidVariableType(type))
                Logger.error("invalid variable initialization");
            expr.compile();
            expr.doCastCompile(type);
            CodeGenerator.mVisit.visitVarInsn(determineOp(type), dcl.getDescriptor().getStackIndex());
        }
    }

    private int determineOp(Type type) {
        if (type == DOUBLE)
            return Opcodes.DSTORE;
        else if (type == FLOAT)
            return Opcodes.FSTORE;
        else if (type == LONG)
            return Opcodes.LSTORE;
        else if (type == INT || type == CHAR || type == BOOL)
            return Opcodes.ISTORE;
        else
            return Opcodes.ASTORE;
    }

}
