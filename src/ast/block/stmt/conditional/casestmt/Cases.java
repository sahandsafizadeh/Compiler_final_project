package ast.block.stmt.conditional.casestmt;

import cg.Logger;
import org.objectweb.asm.Label;

import java.util.LinkedList;
import java.util.List;

public class Cases {

    private static final Cases instance = new Cases();

    private List<Label> jumpTable;
    private List<Integer> cases;
    private Label defaultLabel;
    private Label out;
    private int max;
    private int min;

    private Cases() {
    }

    public static Cases getInstance() {
        return instance;
    }

    public void init() {
        jumpTable = new LinkedList<>();
        cases = new LinkedList<>();
        defaultLabel = null;
        out = new Label();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public void addCase(int ca, Label label) {
        if (cases.contains(ca))
            Logger.error("invalid case");

        jumpTable.add(label);
        cases.add(ca);
        max = Math.max(max, ca);
        min = Math.min(min, ca);
    }

    public void setDefault(Label defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public Label getDefaultLabel() {
        return defaultLabel;
    }

    public Label getOut() {
        return out;
    }

    public Label[] getLabels() {
        return jumpTable.toArray(new Label[jumpTable.size()]);
    }

}
