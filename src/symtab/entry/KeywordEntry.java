package symtab.entry;

import symtab.dscp.KeywordDescriptor;

public class KeywordEntry implements Entry {

    private String name;
    private KeywordDescriptor descriptor = new KeywordDescriptor();

    public KeywordEntry(String name) {
        this.name = name;
    }

    @Override
    public KeywordDescriptor get(String name) {
        return descriptor;
    }

}
