public class Application {
    public static void main(String[] args) {
        Data data = new Data();
        data.steps = new Calculations().GetSteps(data.start, data.end, data.step);
        data.yx = new Calculations().GetYX(data.steps);
        new Out().DrawFirstTable(data.steps, data.yx);
        for (int i = 0; i < data.points.length; i++) {
            data.gaps.add(new Calculations().GetGap(data.points[i], data.steps));
            new Out().OutGaps(data.gaps.get(i));
        }

    }
}
