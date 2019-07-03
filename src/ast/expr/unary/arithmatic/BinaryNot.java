package ast.expr.unary.arithmatic;

import ast.Node;
import ast.expr.Expression;
import ast.expr.unary.UnaryExpression;
import ast.type.Type;
import ast.type.TypeChecker;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class BinaryNot extends UnaryExpression {

    public BinaryNot(Expression expr) {
        super(expr);
    }

    @Override
    public Node compile() {
        Logger.log("binary not");
        Type resultType = TypeChecker.unaryExprTypeCheck(expr.getType());
        int opcode = determineOp(resultType);
        CodeGenerator.mVisit.visitInsn(Opcodes.ICONST_1);
        CodeGenerator.mVisit.visitInsn(opcode);
        return new UnaryExpression(resultType);
    }

    @Override
    public int determineOp(Type type) {
        if (type == VariableType.INT)
            return Opcodes.IXOR;
        else
            Logger.error("type mismatch");
        return 0;
    }

}
