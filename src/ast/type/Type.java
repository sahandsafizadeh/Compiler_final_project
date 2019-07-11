package ast.type;

public abstract class Type {

    private String type;

    public Type(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return type;
    }

    public Type toArray(Type t) {
        return new VariableType("[" + t.getTypeName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return type.equals(type1.type);
    }

}
