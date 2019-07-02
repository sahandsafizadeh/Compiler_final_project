package ast.expr.binary.logical;

import ast.Node;
import ast.expr.Expression;
import ast.expr.binary.BinaryExpression;
import ast.type.CastingType;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class LogicalBinaryExpression extends BinaryExpression {
    public LogicalBinaryExpression(Type type) {
        super(type);
    }

    public LogicalBinaryExpression(Expression expr1, Expression expr2) {
        super(expr1, expr2);
    }

    @Override
    public Node compile() {
        Type resultType = TypeChecker.binaryExprTypeCheck(expr1.getType(), expr2.getType());
        if (resultType != CastingType.INT) {
            Logger.error("unsupported logical operation");
        }

        Label l1 = new Label();
        Label l2 = new Label();
        CodeGenerator.mVisit.visitJumpInsn(determineOp(resultType), l1);
        CodeGenerator.mVisit.visitInsn(Opcodes.ICONST_1);
        CodeGenerator.mVisit.visitJumpInsn(Opcodes.GOTO, l2);
        CodeGenerator.mVisit.visitLabel(l1);
        CodeGenerator.mVisit.visitInsn(Opcodes.ICONST_1);
        CodeGenerator.mVisit.visitLabel(l2);
        return null;
    }

}
