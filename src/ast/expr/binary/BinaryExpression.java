package ast.expr.binary;

import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import cg.TypeChecker;

public abstract class BinaryExpression extends Expression {

    private Expression expr1;
    private Expression expr2;

    public BinaryExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public void compile() {
        Type t1 = expr1.getType();
        Type t2 = expr2.getType();
        int opcode = determineOp(TypeChecker.binaryExprTypeCheck(t1, t2));
        CodeGenerator.mVisit.visitInsn(opcode);
    }

}
