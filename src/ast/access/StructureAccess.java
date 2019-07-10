package ast.access;

import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.struct.StructureDescriptor;
import symtab.dscp.variable.VariableDescriptor;

public class StructureAccess extends Access {

    private String id;

    public StructureAccess(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setDescriptor(String id) {
        descriptor = TableStack.getInstance().find(id);
        if (!(descriptor instanceof StructureDescriptor))
            Logger.error("struct not declared");
    }

    @Override
    public void compile() {
        Logger.log("struct access load");
        StructureDescriptor descriptor = (StructureDescriptor) getDescriptor();
        VariableDescriptor structureVar = descriptor.get(id);
        CodeGenerator.mVisit.visitVarInsn(Opcodes.ALOAD, descriptor.getStackIndex());
        CodeGenerator.mVisit.visitFieldInsn(Opcodes.GETFIELD, descriptor.getName(), structureVar.getName(), structureVar.getType().getTypeName());
    }

    @Override
    public int determineOp(Type type) {
        return 0;
    }

}
