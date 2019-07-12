package ast.program.structure;

import java.util.ArrayList;
import java.util.List;

public class Structures {

    private static final Structures instance = new Structures();

    private List<StructVarDCL> declarations;

    private Structures() {
    }

    public static Structures getInstance() {
        return instance;
    }

    public void init() {
        declarations = new ArrayList<>();
    }

    public void addDCL(StructVarDCL dcl) {
        declarations.add(dcl);
    }

    public StructureDCL getDCL(String typeName) {
        return new StructureDCL(typeName, declarations);
    }

}
