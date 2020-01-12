public class Application {
    public static void main(String[] args) {
        Data data = new Data();
        data.steps = new Calculations().GetSteps(data.start, data.end, data.step);
        data.yx = new Calculations().GetYX(data.steps);
        new Out().DrawFirstTable(data.steps, data.yx);
        for (int i = 0; i < data.points.length; i++) {
            data.gaps.add(new Calculations().GetGap(data.points[i], data.steps, data.lagranj));
            new Out().OutGaps(data.gaps.get(i), data.points[i]);
        }
        for (int i = 0; i < data.points.length; i++) {
            int j = 0;
            for (int k = 0; k < data.steps.length; k++) {
                if (data.gaps.get(i)[0] == data.steps[k]) {
                    j = k;
                    break;
                }
            }
            data.arrLagranj[i] = new Calculations().CalcLagranj(data.gaps.get(i), data.yx, j, data.lagranj, data.points[i]);
            new Out().OutLagranj(data.points[i], data.arrLagranj[i]);
            System.out.println("\n Ньютон для " + data.points[i] + " = " + new Calculations().CalcNewton(data.gaps.get(i), data.yx, data.newton, data.points[i], data.step));
        }

    }
}
