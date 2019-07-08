package ast.type;

public class VariableType extends StructureType {

    public static final Type AUTO = new StructureType("A");
    public static final Type STRING = new VariableType("java/lang/String");

    public VariableType(String type) {
        super(type);
    }

}
