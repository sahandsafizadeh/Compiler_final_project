package ast.expr;

import ast.Node;
import ast.type.Type;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.Type.*;
import static cg.CodeGenerator.mVisit;

public abstract class Expression implements Node {

    public abstract Type getResultType();

    public void doCastCompile(Type resultType) {
        Type type = getResultType();
        if (type == DOUBLE) {
            if (resultType == FLOAT)
                mVisit.visitInsn(Opcodes.D2F);
            else if (resultType == LONG)
                mVisit.visitInsn(Opcodes.D2L);
            else
                mVisit.visitInsn(Opcodes.D2I);
        } else if (type == FLOAT) {
            if (resultType == DOUBLE)
                mVisit.visitInsn(Opcodes.F2D);
            else if (resultType == LONG)
                mVisit.visitInsn(Opcodes.F2L);
            else
                mVisit.visitInsn(Opcodes.F2I);
        } else if (type == LONG) {
            if (resultType == DOUBLE)
                mVisit.visitInsn(Opcodes.L2D);
            else if (resultType == FLOAT)
                mVisit.visitInsn(Opcodes.L2F);
            else
                mVisit.visitInsn(Opcodes.L2I);
        } else if (type == INT) {
            if (resultType == DOUBLE)
                mVisit.visitInsn(Opcodes.I2D);
            else if (resultType == FLOAT)
                mVisit.visitInsn(Opcodes.I2F);
            else
                mVisit.visitInsn(Opcodes.I2L);
        } else
            Logger.error("type mismatch");
    }

}
