package ast.block.stmt.loop;

import ast.block.Block;
import ast.block.stmt.Statement;
import cg.Logger;
import symtab.TableStack;
import symtab.dscp.variable.ArrayDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class Foreach extends Statement {

    private VariableDescriptor counter;
    private VariableDescriptor temp;
    private ArrayDescriptor array;
    private Block body;

    public Foreach(String temp, String array, Block body) {
        try {
            this.array = (ArrayDescriptor) TableStack.getInstance().find(array);
        } catch (Exception e) {
            Logger.log("foreach loop should only be used for arrays");
        }
        this.temp = temp;
        this.array = array;
        this.body = body;
    }

    @Override
    public void compile() {

    }

}
