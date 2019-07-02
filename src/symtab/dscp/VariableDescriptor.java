package symtab.dscp;

import ast.type.Type;

public class VariableDescriptor implements Descriptor {

    private Type type;
    private String name;
    private Object value;

    public VariableDescriptor(Type type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

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

}
