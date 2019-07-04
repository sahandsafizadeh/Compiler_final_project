package ast.dcl.variable;

import ast.Assignment;
import ast.Node;
import ast.block.BlockContent;

public class CompleteDCL extends BlockContent {

    private VarDCL dcl;
    private Assignment assignment;

    public CompleteDCL(VarDCL dcl, Assignment assignment) {
        this.dcl = dcl;
        this.assignment = assignment;
    }

    @Override
    public Node compile() {
        return null;
    }

}
