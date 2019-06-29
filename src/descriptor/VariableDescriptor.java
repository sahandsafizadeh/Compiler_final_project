package descriptor;

public class VariableDescriptor implements Descriptor {

    private Class type;
    private Object value;

    public VariableDescriptor(Class type, Object value) {
        this.value = value;
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}
