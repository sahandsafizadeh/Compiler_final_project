package symtab;

import symtab.entry.Entry;

import java.util.List;

public class SymbolTable {

    private List<Entry> table;

    public SymbolTable(List<Entry> table) {
        this.table = table;
    }

    public List<Entry> getTable() {
        return table;
    }

}
