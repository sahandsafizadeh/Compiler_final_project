package ast.program;

import ast.Node;

public class Program implements Node {

    private static final Program instance = new Program();

    private Program() {
    }

    public static Program getInstance() {
        return instance;
    }

    @Override
    public void compile() {

    }

}
