package symtab;

import java_cup.runtime.Symbol;
import symtab.dscp.VariableDescriptor;
import symtab.entry.KeywordEntry;
import symtab.entry.VariableEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableStack {

    private static final TableStack instance = new TableStack();
    private final List<SymbolTable> SYM_TAB_STACK = new ArrayList<>();
    private SymbolTable base;
    private static final String[] KEYWORDS = {
            "const",
            "record",
            "bool",
            "char",
            "int",
            "long",
            "float",
            "double",
            "string",
            "void",
            "true",
            "false",
            "extern",
            "auto",
            "function",
            "begin",
            "end",
            "if",
            "else",
            "switch",
            "of",
            "case",
            "default",
            "for",
            "foreach",
            "in",
            "repeat",
            "until",
            "break",
            "continue",
            "return",
            "sizeof",
            ";",
            ":",
            ",",
            ".",
            "~",
            "+",
            "-",
            "*",
            "/",
            "%",
            "=",
            "&",
            "|",
            "^",
            "not",
            "and",
            "or",
            "<",
            ">",
            "{",
            "}",
            "[",
            "]",
            "(",
            ")",
            "++",
            "--",
            "+=",
            "-=",
            "*=",
            "/=",
            "!=",
            "==",
            "<=",
            ">="};

    static {
        instance.SYM_TAB_STACK
                .add(new SymbolTable(Arrays.stream(KEYWORDS).map(KeywordEntry::new).collect(Collectors.toList())));
        instance.base = instance.SYM_TAB_STACK.get(0);
    }

    private TableStack() {
    }

    public static TableStack getInstance() {
        return instance;
    }

    public void addGlobal(VariableDescriptor descriptor) {
        base.getTable().add(new VariableEntry(descriptor.getName(), descriptor));
    }

    public SymbolTable getBase() {
        return base;
    }

    public void push(SymbolTable symbolTable) {
        instance.SYM_TAB_STACK.add(symbolTable);
    }

    public void pop() {
        instance.SYM_TAB_STACK.remove(instance.SYM_TAB_STACK.size() - 1);
    }

    public void addVar(VariableDescriptor descriptor) {
        String key = descriptor.getName();
        int index = base.getTable().indexOf(new VariableEntry(key, null));
//        if ()
    }

    public VariableDescriptor getVar(String name) {
        int index = -1;
        SymbolTable table = null;
        for (int i = instance.SYM_TAB_STACK.size() - 1; i >= 0 && index < 0; i--) {
            table = instance.SYM_TAB_STACK.get(i);
            index = table.getTable().indexOf(new VariableEntry(name, null));
        }

        return (VariableDescriptor) table.getTable().get(index).get(name);
    }

}
