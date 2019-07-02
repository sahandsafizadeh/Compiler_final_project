package ast.expr;

import ast.Node;
import ast.type.CastingType;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

public class CastExpr extends Expression {

    private Expression expr;
    private Type type;

    public CastExpr(Type type) {
        super(type);
    }

    public CastExpr(Type type, Expression expr) {
        this.expr = expr;
        this.type = type;
    }

    @Override
    public Node compile() {
        Logger.log("type casting");
        if (!(type instanceof CastingType))
            Logger.error("unsupported cast");

        if (type != expr.getType())
            CodeGenerator.mVisit.visitInsn(determineOp(type));
        return new CastExpr(type);
    }

    @Override
    public int determineOp(Type type) {
        if (type == CastingType.DOUBL && expr.getType() == CastingType.FLOAT)
            return Opcodes.F2D;
        else if (type == CastingType.FLOAT && expr.getType() == CastingType.DOUBL)
            return Opcodes.D2F;
        else if (type == CastingType.DOUBL && expr.getType() == CastingType.INT)
            return Opcodes.I2D;
        else if (type == CastingType.INT && expr.getType() == CastingType.DOUBL)
            return Opcodes.D2I;
        else if (type == CastingType.FLOAT && expr.getType() == CastingType.INT)
            return Opcodes.I2F;
        else if (type == CastingType.INT && expr.getType() == CastingType.FLOAT)
            return Opcodes.F2I;
        else if (type == CastingType.INT && expr.getType() == CastingType.CHAR)
            return 0;//todo
        else if (type == CastingType.CHAR && expr.getType() == CastingType.INT)
            return Opcodes.I2C;
        else {
            //todo booleans
            return 0;
        }
    }

}
