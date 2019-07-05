package symtab;

import ast.type.VariableType;
import cg.Logger;
import symtab.dscp.Descriptor;
import symtab.dscp.KeywordDescriptor;
import symtab.dscp.variable.AbstractDescriptor;
import symtab.dscp.variable.ArrayDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableStack {

    private static final TableStack instance = new TableStack();
    private final List<SymbolTable> SYM_TAB_STACK = new ArrayList<>();
    private int mainStackIndex = 1;
    private int functionStackIndex = 0;
    private boolean inFuncDCL = false;
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

    public void pushSymbolTable(SymbolTable symbolTable) {
        SYM_TAB_STACK.add(symbolTable);
    }

    public void popSymbolTable() {
        SYM_TAB_STACK.remove(SYM_TAB_STACK.size() - 1);
    }

    public void addGlobalVariable(VariableDescriptor descriptor) {
        getBase().put(descriptor);
    }

    public void addVariable(VariableDescriptor descriptor) {
        checkError(descriptor);
        putDescriptor(descriptor.getType() == VariableType.DOUBLE || descriptor.getType() == VariableType.LONG ? 2 : 1, descriptor);
    }

    public void addArray(ArrayDescriptor descriptor) {
        checkError(descriptor);
        putDescriptor(1, descriptor);
    }

    public Descriptor find(String id) {
        for (int i = SYM_TAB_STACK.size() - 1; i >= 0; i--) {
            SymbolTable table = SYM_TAB_STACK.get(i);
            if (table.contains(id))
                return table.get(id);
        }
        return null;
    }

    private void checkError(AbstractDescriptor descriptor) {
        if (getTop().contains(descriptor.getName()) || getBase().contains(descriptor.getName()))
            Logger.error("variable name already exists");
    }

    private void putDescriptor(int increment, AbstractDescriptor descriptor) {
        if (inFuncDCL) {
            descriptor.setStackIndex(functionStackIndex);
            functionStackIndex += increment;
        } else {
            descriptor.setStackIndex(mainStackIndex);
            mainStackIndex += increment;
        }
        getTop().put(descriptor);
    }

}
