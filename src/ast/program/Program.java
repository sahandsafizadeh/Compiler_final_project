package ast.program;

import ast.Node;
import ast.type.FunctionType;
import ast.type.VariableType;
import cg.Logger;
import symtab.dscp.function.Functions;

import java.util.ArrayList;
import java.util.List;

public class Program implements Node {

    private static final Program instance = new Program();

    private List<ProgramContent> contents = new ArrayList<>();

    private Program() {
    }

    public static Program getInstance() {
        return instance;
    }

    public void addContent(ProgramContent content) {
        contents.add(content);
    }

    @Override
    public void compile() {
        Logger.log("compiling program");
        contents.forEach(ProgramContent::compile);
        if (!Functions.getInstance().contains("main", FunctionType.INT))
            Logger.error("program doesn't contain a main function");
    }

}
