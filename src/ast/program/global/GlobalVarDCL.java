package ast.program.global;

import ast.program.Program;
import ast.program.ProgramContent;
import ast.type.Type;
import ast.type.TypeChecker;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.VariableDescriptor;

public class GlobalVarDCL extends ProgramContent {

    private Type type;
    private String id;

    public GlobalVarDCL(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void compile() {
        Logger.log("global variable declaration");
        if (!TypeChecker.isValidVariableType(type))
            Logger.error("invalid type for global variable");

        VariableDescriptor descriptor = new VariableDescriptor();
        descriptor.setName(id);
        descriptor.setType(type);
        descriptor.setConst(false);
        TableStack.getInstance().addGlobal(descriptor);
        Program.getInstance().addContent(this);

        CodeGenerator.mainClw.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, id, type.getTypeName(), null, null).visitEnd();
    }

}
