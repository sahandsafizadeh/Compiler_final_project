package symtab.dscp.variable;

import symtab.dscp.AbstractDescriptor;

public class VariableDescriptor extends AbstractDescriptor implements Cloneable {

    @Override
    public VariableDescriptor clone() {
        VariableDescriptor other = new VariableDescriptor();
        other.name = name;
        other.type = type;
        other.isConst = isConst;
        return other;
    }

}
