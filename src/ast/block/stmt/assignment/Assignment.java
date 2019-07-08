package ast.block.stmt.assignment;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.VariableAccess;
import ast.block.BlockContent;
import ast.expr.Expression;
import ast.type.Type;
import ast.type.VariableType;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;

import static ast.type.CastingType.*;
import static ast.type.StructureType.LONG;

public abstract class Assignment extends BlockContent {

    protected Access access;
    protected Expression expr;

    public Assignment(Access access, Expression expr) {
        this.access = access;
        this.expr = expr;
    }

    @Override
    public void compile() {
        expr.compile();
        expr.doCastCompile(access.getDescriptor().getType());
    }

    public void checkOperation() {
        if (access.getDescriptor().isConst())
            Logger.error("constant variables can't be changed");
    }

    public int determineOp(Type type) {
        boolean varAccess = !(access instanceof ArrayAccess);
        if (type == DOUBLE)
            return varAccess ? Opcodes.DSTORE : Opcodes.DASTORE;
        else if (type == FLOAT)
            return varAccess ? Opcodes.FSTORE : Opcodes.FASTORE;
        else if (type == LONG)
            return varAccess ? Opcodes.LSTORE : Opcodes.LASTORE;
        else if (type == INT)
            return varAccess ? Opcodes.ISTORE : Opcodes.IASTORE;
        else
            return varAccess ? Opcodes.ASTORE : Opcodes.AASTORE;
    }

    void arrayStoreInit() {
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, access.getDescriptor().getStackIndex());
        Expression indexExpr = ((ArrayAccess) access).getIndex();
        if (indexExpr.getResultType() != VariableType.INT)
            Logger.error("arrays can only be accessed using integer values");
        indexExpr.compile();
    }

}
