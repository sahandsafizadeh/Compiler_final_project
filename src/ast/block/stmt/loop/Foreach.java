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
    private int arrayLdrCode;

    public Foreach(String temp, String array, Block body) {
        this.temp = temp;
        this.array = array;
        this.body = body;
    }

    @Override
    public void compile() {
        Logger.log("foreach loop");
        initLoop();
        determineOp(loopArray.getType());
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
        mVisit.visitInsn(arrayLdrCode);
        mVisit.visitVarInsn(strCode, loopTemp.getStackIndex());

        body.compile();
        body.markStart();
        mVisit.visitIincInsn(loopCounter.getStackIndex(), 1);
        mVisit.visitJumpInsn(Opcodes.GOTO, loopStart);
        body.markEnd();
    }

    private void determineOp(Type type) {
        if (type == DOUBLE) {
            strCode = Opcodes.DSTORE;
            ldrCode = Opcodes.DLOAD;
            arrayLdrCode = Opcodes.DALOAD;
        } else if (type == FLOAT) {
            strCode = Opcodes.FSTORE;
            ldrCode = Opcodes.FLOAT;
            arrayLdrCode = Opcodes.FALOAD;
        } else if (type == LONG) {
            strCode = Opcodes.LSTORE;
            ldrCode = Opcodes.LLOAD;
            arrayLdrCode = Opcodes.LALOAD;
        } else if (type == INT) {
            strCode = Opcodes.ISTORE;
            ldrCode = Opcodes.ILOAD;
            arrayLdrCode = Opcodes.IALOAD;
        } else {
            strCode = Opcodes.ASTORE;
            ldrCode = Opcodes.ALOAD;
            arrayLdrCode = Opcodes.AALOAD;
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
