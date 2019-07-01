package ast.block;

import ast.Node;

import java.util.ArrayList;
import java.util.List;

public class Block implements Node {

    private List<BlockContent> content = new ArrayList<>();

    public Block() {
        Blocks.BLOCK_CONTENT.push(this);
    }

    public void add(BlockContent cont) {
        content.add(cont);
    }

    @Override
    public void compile() {
        content.forEach(Node::compile);
        Blocks.BLOCK_CONTENT.pop();
    }

}
