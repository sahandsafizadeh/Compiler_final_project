package ast.expr.other;

import ast.access.Access;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.Logger;
import symtab.dscp.AbstractDescriptor;

public class Variable extends Expression {

    private Access access;
    private AbstractDescriptor descriptor;

    public Variable(Access access) {
        super(access.getDescriptor().getType());
        this.access = access;
        descriptor = access.getDescriptor();
    }

    @Override
    public Type getResultType() {
        if (!TypeChecker.isValidVariableType(descriptor.getType()))
            Logger.error("type mismatch");
        return descriptor.getType();
    }

    @Override
    public void compile() {
        Logger.log("variable push");
        access.compile();
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
