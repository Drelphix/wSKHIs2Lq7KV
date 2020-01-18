import org.apache.commons.math3.util.Precision;

public class Out {
    public void DrawFirstTable(double[] steps, double[] yx) {
        System.out.print("  X   |");
        for (double step : steps) {
            System.out.print(Precision.round(step, 4) + " | ");
        }
        System.out.println();
        System.out.print(" Y(x) |");
        for (double v : yx) {
            System.out.print(Precision.round(v, 4) + " | ");
        }
    }

    public void OutGaps(double[] gaps, double point, String type) {
        System.out.print("\n Точка " + point + " Узлы ");
        for (double gap : gaps) {
            System.out.print(gap + ", ");
        }
    }

    public void OutPower(double point, double value, String type) {
        System.out.print("\n " + type + " получили " + value);
    }
}
