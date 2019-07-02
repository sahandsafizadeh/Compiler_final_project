package symtab.entry;

import symtab.dscp.Descriptor;

public interface Entry {

    Descriptor get(String key);

}
