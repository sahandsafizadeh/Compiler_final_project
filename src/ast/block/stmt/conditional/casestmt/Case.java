package ast.block.stmt.conditional.casestmt;

import ast.Node;
import ast.block.stmt.Statement;
import cg.CodeGenerator;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class Case extends Statement {

    private int ca;
    private Label casePoint;

    public Case(int ca, Label casePoint) {
        this.ca = ca;
        this.casePoint = casePoint;
        Cases.getInstance().addCase(ca, casePoint);
    }

    @Override
    public Node compile() {
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.GOTO, Cases.getInstance().getOut());
        return null;
    }

}
