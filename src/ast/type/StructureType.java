package ast.type;

public class StructureType extends CastingType {

    public static final Type BOOL = new StructureType("Z");
    public static final Type LONG = new StructureType("J");

    StructureType(String type) {
        super(type);
    }
}
