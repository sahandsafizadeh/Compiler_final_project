package ast.expr.unary.arithmatic;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.StructureAccess;
import ast.access.VariableAccess;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.GlobalVariableDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import static ast.type.Type.*;
import static cg.CodeGenerator.mVisit;

public class PostfixMinus2 extends DualOperation {

    public PostfixMinus2(Access access) {
        super(access);
    }

    @Override
    public void compile() {
        Logger.log("postfix minus minus");
        super.compile();
        mVisit.visitInsn(dupOp);
        mVisit.visitInsn(constOp);
        mVisit.visitInsn(opcode);
        if (access instanceof VariableAccess) {
            if (descriptor instanceof GlobalVariableDescriptor)
                mVisit.visitFieldInsn(Opcodes.PUTFIELD, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().typeName());
            else
                mVisit.visitVarInsn(strOp, descriptor.getStackIndex());
        } else if (access instanceof ArrayAccess) {

        } else {
            VariableDescriptor structVar = ((StructureAccess) access).getStructureVar();
            mVisit.visitFieldInsn(Opcodes.PUTFIELD, descriptor.getType().typeName(), structVar.getName(), structVar.getType().typeName());
        }
    }

    @Override
    public int determineOp(Type type) {
        if (type == DOUBLE)
            opcode = Opcodes.DSUB;
        else if (type == FLOAT)
            opcode = Opcodes.FSUB;
        else if (type == LONG)
            opcode = Opcodes.LSUB;
        else
            opcode = Opcodes.ISUB;
        return super.determineOp(type);
    }

}
