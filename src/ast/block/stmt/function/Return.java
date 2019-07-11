package ast.block.stmt.function;

import ast.block.BlockContent;
import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;

import static ast.type.VariableType.*;

public class Return extends BlockContent {

    private Expression expr;

    public Return(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void compile() {
        Logger.log("return statement");
        expr.compile();
        CodeGenerator.mVisit.visitInsn(determineOp(expr.getResultType()));
    }

    private int determineOp(Type type) {
        Type currentFuncReturnType = TableStack.getInstance().getCurrentFunction().getReturnType();
        if (type == DOUBLE && currentFuncReturnType == DOUBLE)
            return Opcodes.DRETURN;
        else if (type == FLOAT && currentFuncReturnType == FLOAT)
            return Opcodes.FRETURN;
        else if (type == LONG && currentFuncReturnType == LONG)
            return Opcodes.LRETURN;
        else if (type == INT && currentFuncReturnType == INT)
            return Opcodes.IRETURN;
        else
            Logger.error("invalid function return type");
        return 0;
    }

}
