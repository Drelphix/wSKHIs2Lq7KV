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
            data.arrLagranj[i] = new Calculations().CalcLagranj(data.gaps.get(i), data.yx, data.lagranj, data.points[i]);
            new Out().OutLagranj(data.points[i], data.arrLagranj[i]);
        }

    }
}
