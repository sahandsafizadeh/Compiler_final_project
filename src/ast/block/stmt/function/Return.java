package ast.block.stmt.function;

import ast.block.BlockContent;
import ast.expr.Expression;
import ast.expr.other.Variable;
import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

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
        if (type == VariableType.DOUBLE)
            return Opcodes.DRETURN;
        else if (type == VariableType.FLOAT)
            return Opcodes.FRETURN;
        else if (type == VariableType.LONG)
            return Opcodes.LRETURN;
        else if (type == VariableType.INT)
            return Opcodes.IRETURN;
        else
            return Opcodes.ARETURN;
    }

}
