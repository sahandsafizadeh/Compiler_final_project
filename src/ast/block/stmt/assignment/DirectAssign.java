package ast.block.stmt.assignment;

import ast.access.Access;
import ast.access.ArrayAccess;
import ast.access.StructureAccess;
import ast.access.VariableAccess;
import ast.expr.Expression;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.variable.GlobalVariableDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import static cg.CodeGenerator.mVisit;

public class DirectAssign extends Assignment {

    public DirectAssign(Access access, Expression expr) {
        super(access, expr);
    }

    @Override
    public void compile() {
        Logger.log("direct assignment");
        checkOperation();
        if (access instanceof VariableAccess)
            variableDirectAssign();
        else if (access instanceof ArrayAccess)
            arrayDirectAssign();
        else
            structDirectAssign();
    }

    private void variableDirectAssign() {
        Type.inferType(descriptor, expr.getResultType());
        expr.compile();
        expr.doCastCompile(descriptor.getType());
        if (descriptor instanceof GlobalVariableDescriptor)
            mVisit.visitFieldInsn(Opcodes.PUTFIELD, CodeGenerator.GENERATED_CLASS, descriptor.getName(), descriptor.getType().typeName());
        else
            mVisit.visitVarInsn(determineOp(descriptor.getType()), descriptor.getStackIndex());
    }

    private void arrayDirectAssign() {
        Type type = Type.toSimple(descriptor.getType());
        arrayStoreInit();
        expr.compile();
        expr.doCastCompile(Type.toSimple(type));
        mVisit.visitInsn(determineOp(type));
    }

    private void structDirectAssign() {
        VariableDescriptor structVar = ((StructureAccess) access).getStructureVar();
        expr.compile();
        expr.doCastCompile(structVar.getType());
        mVisit.visitFieldInsn(Opcodes.PUTFIELD, descriptor.getType().typeName(), structVar.getName(), structVar.getType().typeName());
    }

}
