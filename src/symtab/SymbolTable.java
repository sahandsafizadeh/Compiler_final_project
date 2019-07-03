package symtab;

import symtab.dscp.Descriptor;
import symtab.dscp.VariableDescriptor;

import java.util.Map;

public class SymbolTable {

    private Map<String, Descriptor> table;

    public SymbolTable(Map<String, Descriptor> table) {
        this.table = table;
    }

    public boolean contains(String name) {
        return table.containsKey(name);
    }

    public void put(VariableDescriptor descriptor) {
        table.put(descriptor.getName(), descriptor);
    }

}
