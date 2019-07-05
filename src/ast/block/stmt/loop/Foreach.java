package ast.block.stmt.loop;

import ast.block.Block;
import ast.block.stmt.Statement;
import symtab.dscp.variable.ArrayDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class Foreach extends Statement {

    private VariableDescriptor temp;
    private ArrayDescriptor array;
    private Block body;

    public Foreach(VariableDescriptor temp, ArrayDescriptor array, Block body) {
        this.temp = temp;
        this.array = array;
        this.body = body;
    }

    @Override
    public void compile() {

    }

}
