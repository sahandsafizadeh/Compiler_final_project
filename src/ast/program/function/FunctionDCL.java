package ast.program.function;

import ast.block.Block;
import ast.program.ProgramContent;
import ast.type.Type;
import cg.CodeGenerator;
import cg.Logger;
import org.objectweb.asm.Opcodes;
import symtab.dscp.AbstractDescriptor;
import symtab.dscp.function.FunctionDescriptor;
import symtab.dscp.function.Functions;
import symtab.dscp.variable.VariableDescriptor;

import java.util.Map;
import java.util.stream.Collectors;

public class FunctionDCL extends ProgramContent {

    private Type type;
    private String id;
    private Block block;

    public FunctionDCL(Type type, String id, Block block) {
        this.type = type;
        this.id = id;
        this.block = block;
    }

    @Override
    public void compile() {
        Logger.log("function declaration");
        FunctionDescriptor descriptor = new FunctionDescriptor();
        descriptor.setName(id);
        descriptor.setReturnType(type);
        descriptor.setParameters(getParameters());
        Functions.getInstance().addFunction(descriptor);
        if (block == null)
            ;//todo putting in symbol table
        else {
            CodeGenerator.mVisit =
                    CodeGenerator.mainClw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, descriptor.getName(), descriptor.getDescriptor(), null, null);
            CodeGenerator.mVisit.visitCode();
            block.compile();
            CodeGenerator.mVisit.visitMaxs(1, 1);
            CodeGenerator.mVisit.visitEnd();
        }
    }

    private Map<String, VariableDescriptor> getParameters() {
        return FunctionArguments.getInstance().getArguments().stream().collect(Collectors.toMap(AbstractDescriptor::getName, d -> d));
    }

}
