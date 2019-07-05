package ast.block.stmt.loop;

import ast.block.Block;
import ast.block.stmt.Statement;
import ast.type.Type;
import ast.type.VariableType;
import cg.Logger;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import symtab.TableStack;
import symtab.dscp.variable.ArrayDescriptor;
import symtab.dscp.variable.VariableDescriptor;

import static ast.type.VariableType.*;
import static cg.CodeGenerator.mVisit;

public class Foreach extends Statement {

    private String temp;
    private String array;
    private Block body;

    private ArrayDescriptor loopArray;
    private VariableDescriptor loopTemp;
    private VariableDescriptor loopCounter;
    private int strCode;
    private int ldrCode;

    public Foreach(String temp, String array, Block body) {
        this.temp = temp;
        this.array = array;
        this.body = body;
    }

    @Override
    public void compile() {
        Logger.log("foreach loop");
        initLoop();
        mVisit.visitInsn(Opcodes.ICONST_0);
        mVisit.visitVarInsn(Opcodes.ISTORE, loopCounter.getStackIndex());

        Label loopStart = new Label();
        mVisit.visitLabel(loopStart);
        mVisit.visitVarInsn(Opcodes.ALOAD, loopArray.getStackIndex());
        mVisit.visitInsn(Opcodes.ARRAYLENGTH);
        mVisit.visitVarInsn(ldrCode, loopCounter.getStackIndex());
        mVisit.visitJumpInsn(Opcodes.IFGE, body.getEnd());

        mVisit.visitVarInsn(Opcodes.ALOAD, loopArray.getStackIndex());
        mVisit.visitVarInsn(Opcodes.ILOAD, loopCounter.getStackIndex());
        mVisit.visitVarInsn(strCode, loopTemp.getStackIndex());

        body.compile();
        body.markStart();
        mVisit.visitIincInsn(loopCounter.getStackIndex(), 1);
        mVisit.visitJumpInsn(Opcodes.GOTO, loopStart);
        body.markEnd();
    }

    private void determineOp(Type type) {
        if (type == DOUBLE) {
            ldrCode = Opcodes.DLOAD;
            strCode = Opcodes.DASTORE;
        } else if (type == FLOAT) {
            ldrCode = Opcodes.FLOAT;
            strCode = Opcodes.FASTORE;
        } else if (type == LONG) {
            ldrCode = Opcodes.LLOAD;
            strCode = Opcodes.LASTORE;
        } else if (type == INT) {
            ldrCode = Opcodes.ILOAD;
            strCode = Opcodes.IASTORE;
        } else {
            ldrCode = Opcodes.ALOAD;
            strCode = Opcodes.AASTORE;
        }
    }

    private void initLoop() {
        loopArray = (ArrayDescriptor) TableStack.getInstance().find(array);

        loopTemp = new VariableDescriptor();
        loopTemp.setName(temp);
        loopTemp.setConst(false);
        loopTemp.setType(loopArray.getType());
        TableStack.getInstance().addVariable(loopTemp);

        loopCounter = new VariableDescriptor();
        loopCounter.setName("$t");
        loopCounter.setConst(false);
        loopCounter.setType(VariableType.INT);
        TableStack.getInstance().addVariable(loopCounter);
    }

}
