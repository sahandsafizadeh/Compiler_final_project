package ast.block;

import ast.Node;
import cg.CodeGenerator;
import org.objectweb.asm.Label;

public class Block implements Node {

    private Label start = new Label();
    private Label end = new Label();

    public Block() {
        Blocks.getInstance().add(this);
        //todo pushing a new symbol table after finishing symbol table
        CodeGenerator.mVisit.visitLabel(start);
    }

    public Label getStart() {
        return start;
    }

    public Label getEnd() {
        return end;
    }

    @Override
    public Node compile() {
        CodeGenerator.mVisit.visitLabel(end);
        Blocks.getInstance().remove();
        return null;
    }

}
