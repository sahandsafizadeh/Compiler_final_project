package ast.type;

public class VariableType extends StructureType {

    public static final Type STRING = new VariableType("Ljava/lang/String");
    //id for structures?

    VariableType(String type) {
        super(type);
    }

}
