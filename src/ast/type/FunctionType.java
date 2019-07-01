package ast.type;

public class FunctionType extends StructureType {

    public static final Type STRING = new FunctionType("Ljava/lang/String");

    FunctionType(String type) {
        super(type);
    }
}
