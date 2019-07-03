package ast.block.stmt.conditional.casestmt;

import ast.Node;
import ast.block.stmt.Statement;
import cg.CodeGenerator;

public class Switch extends Statement {

    @Override
    public Node compile() {
        Cases cases = Cases.getInstance();
        CodeGenerator.mVisit.visitTableSwitchInsn(cases.getMin(), cases.getMax(), cases.getDefaultLabel(), cases.getLabels());
        return null;
    }

}
