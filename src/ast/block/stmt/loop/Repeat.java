package ast.block.stmt.loop;

import ast.Node;
import ast.block.Blocks;
import ast.block.stmt.Statement;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class Repeat extends Statement {

    @Override
    public Node compile() {
        Logger.log("repeat");
        Blocks blocks = Blocks.getInstance();
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.IFEQ, blocks.getCurrent().getStart());
        blocks.getCurrent().compile();
        return null;
    }

}
