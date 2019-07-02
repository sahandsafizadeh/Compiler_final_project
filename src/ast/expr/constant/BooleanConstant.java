package ast.expr.constant;

import ast.Node;
import ast.type.StructureType;
import ast.type.Type;
import cg.CodeGenerator;
import org.objectweb.asm.Opcodes;

public class BooleanConstant extends Constant {

    public BooleanConstant(Type type) {
        super(type);
    }

    public BooleanConstant(Object value) {
        super(value);
    }

    @Override
    public Node compile() {
        boolean value = (Boolean) this.value;
        CodeGenerator.mVisit.visitVarInsn(Opcodes.LDC, value ? 1 : 0);
        return new BooleanConstant(StructureType.INT);
    }

}
