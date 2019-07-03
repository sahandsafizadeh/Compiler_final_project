package ast.block.stmt.conditional.ifstmt;

import ast.Node;
import ast.block.Blocks;
import ast.block.stmt.Statement;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class If extends Statement {

    @Override
    public Node compile() {
        Logger.log("if");
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.IFEQ, Blocks.getInstance().getCurrent().getEnd());
        return null;
    }

}
