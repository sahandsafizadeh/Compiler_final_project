package symtab.dscp;

import ast.type.Type;

public class VariableDescriptor implements Descriptor, Cloneable {

    private Type type;
    private String name;
    private Object value;
    private boolean isConst;
    private boolean isArgument;
    private int stackIndex;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isConst() {
        return isConst;
    }

    public void setConst(boolean aConst) {
        isConst = aConst;
    }

    public boolean isArgument() {
        return isArgument;
    }

    public void setArgument(boolean argument) {
        isArgument = argument;
    }

    public int getStackIndex() {
        return stackIndex;
    }

    public void setStackIndex(int stackIndex) {
        this.stackIndex = stackIndex;
    }

    @Override
    public VariableDescriptor clone() {
        VariableDescriptor other = new VariableDescriptor();
        other.name = name;
        other.type = type;
        other.value = value;
        other.isConst = isConst;
        return other;
    }

}
