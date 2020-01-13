import org.apache.commons.math3.util.Precision;

public class Out {
    public void DrawFirstTable(double[] steps, double[] yx) {
        System.out.print("  X   |");
        for (int i = 0; i < steps.length; i++) {
            System.out.print(Precision.round(steps[i], 4) + " | ");
        }
        System.out.println();
        System.out.print(" Y(x) |");
        for (int i = 0; i < yx.length; i++) {
            System.out.print(Precision.round(yx[i], 4) + " | ");
        }
    }

    public void OutGaps(double[] gaps, double point, String type) {
        System.out.print("\n Для " + type + " возьмем узлы ");
        for (int i = 0; i < gaps.length; i++) {
            System.out.print("X" + i + " = " + gaps[i] + ", ");
        }
        System.out.print("Для точки " + point);
    }

    public void OutPower(double point, double value, String type) {
        System.out.print("\n " + type + " для точки " + point + " получили " + value);
    }
}
