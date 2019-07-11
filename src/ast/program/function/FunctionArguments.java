package ast.program.function;

import ast.type.Type;
import symtab.dscp.variable.VariableDescriptor;

import java.util.ArrayList;
import java.util.List;

public class FunctionArguments {

    private static final FunctionArguments instance = new FunctionArguments();

    private final List<VariableDescriptor> arguments = new ArrayList<>();
    private Type currentType;

    private FunctionArguments() {
    }

    public static FunctionArguments getInstance() {
        return instance;
    }

    public void init() {
        arguments.clear();
    }

    public void addArgument(String id, Type type) {
        VariableDescriptor descriptor = new VariableDescriptor();
        descriptor.setName(id);
        descriptor.setType(type);
        descriptor.setConst(false);
        arguments.add(descriptor);
    }

    public List<VariableDescriptor> getArguments() {
        return arguments;
    }

    public Type getCurrentType() {
        return currentType;
    }

    public void setCurrentType(Type currentType) {
        this.currentType = currentType;
    }
}
