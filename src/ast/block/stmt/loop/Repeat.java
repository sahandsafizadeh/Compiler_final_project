package ast.block.stmt.loop;

import ast.Node;
import ast.block.Block;
import ast.block.stmt.Statement;
import ast.expr.Expression;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Repeat extends Statement {

    private Block block;
    private Expression expr;

    public Repeat(Block block, Expression expr) {
        this.block = block;
        this.expr = expr;
    }

    @Override
    public Node compile() {
        Logger.log("repeat");
        block.markStart();
        block.compile();
        expr.compile();
        block.markEnd();
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.IFEQ, block.getStart());
        return this;
    }

}
