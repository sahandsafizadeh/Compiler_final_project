package symtab.dscp.struct;

import symtab.dscp.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import java.util.Map;

public class StructureDescriptor extends AbstractDescriptor {

    private Map<String, VariableDescriptor> variables;

    public VariableDescriptor get(String name) {
        return variables.get(name);
    }

}
