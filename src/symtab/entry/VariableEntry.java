package symtab.entry;

import symtab.dscp.Descriptor;
import symtab.dscp.VariableDescriptor;

public class VariableEntry implements Entry {

    private String name;
    private VariableDescriptor descriptor;

    public VariableEntry(String name, VariableDescriptor descriptor) {
        this.name = name;
        this.descriptor = descriptor;
    }

    public String getName() {
        return name;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public Descriptor get(String key) {
        return null;
    }
}
