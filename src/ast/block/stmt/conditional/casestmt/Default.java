package ast.block.stmt.conditional.casestmt;

import ast.Node;
import ast.block.stmt.Statement;
import cg.CodeGenerator;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class Default extends Statement {

    private Label defaultPoint;

    public Default(Label defaultPoint) {
        this.defaultPoint = defaultPoint;
    }

    @Override
    public Node compile() {
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.GOTO, Cases.getInstance().getOut());
        return null;
    }

}
