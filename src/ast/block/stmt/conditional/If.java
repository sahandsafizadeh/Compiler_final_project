package ast.block.stmt.conditional;

import ast.Node;
import ast.block.Block;
import ast.block.stmt.Statement;
import ast.expr.Expression;

public class If extends Statement {

    private Expression expr;
    private Block ifPart;
    private Block elsePart;

    public If(Expression expr, Block ifPart, Block elsePart) {
        this.expr = expr;
        this.ifPart = ifPart;
        this.elsePart = elsePart;
    }

    @Override
    public Node compile() {
        return null;
    }
}
