package symtab.dscp.function;

import ast.type.Type;
import symtab.dscp.Descriptor;

import java.util.Arrays;
import java.util.Objects;

public class FunctionDescriptor implements Descriptor {

    private String name;
    private Type returnType;
    private Type[] parameterTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public Type[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Type... parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getDescriptor() {
        StringBuilder descriptor = new StringBuilder("(");
        Arrays.stream(parameterTypes).forEach(type -> descriptor.append(type.getTypeName()));
        descriptor.append(")");
        descriptor.append(returnType.getTypeName());
        return descriptor.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionDescriptor that = (FunctionDescriptor) o;
        return name.equals(that.name) &&
                Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }

}
