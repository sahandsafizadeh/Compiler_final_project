package ast.block.stmt.assignment;

import ast.access.Access;
import ast.expr.Expression;
import cg.CodeGenerator;
import cg.Logger;

public class DirectAssign extends Assignment {

    public DirectAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        Logger.log("direct assignment");
        super.compile();
    }

}
