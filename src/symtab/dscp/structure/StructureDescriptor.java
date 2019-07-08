package symtab.dscp.structure;

import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import java.util.Map;

public class StructureDescriptor extends AbstractDescriptor {

    private Map<String, VariableDescriptor> variables;

    public VariableDescriptor get(String name) {
        return variables.get(name);
    }

}
