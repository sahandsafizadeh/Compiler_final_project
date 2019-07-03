package symtab;

import cg.Logger;
import symtab.dscp.KeywordDescriptor;
import symtab.dscp.VariableDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableStack {

    private static final TableStack instance = new TableStack();
    private final List<SymbolTable> SYM_TAB_STACK = new ArrayList<>();
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
                .add(new SymbolTable(Arrays.stream(KEYWORDS).collect(Collectors.toMap(key -> key, key -> new KeywordDescriptor()))));
    }

    private TableStack() {
    }

    public static TableStack getInstance() {
        return instance;
    }

    public SymbolTable getBase() {
        return SYM_TAB_STACK.get(0);
    }

    public SymbolTable getTop() {
        return SYM_TAB_STACK.get(SYM_TAB_STACK.size() - 1);
    }

    public void addVariable(VariableDescriptor descriptor) {
        if (getTop().contains(descriptor.getName()) || getBase().contains(descriptor.getName()))
            Logger.error("name already exists");
        getTop().put(descriptor);
    }

}
