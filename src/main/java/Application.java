public class Application {
    public static void main(String[] args) {
        Data data = new Data();
        data.steps = new Calculations().GetSteps(data.start, data.end, data.step);
        data.yx = new Calculations().GetYX(data.steps);
        new Out().DrawFirstTable(data.steps, data.yx);
        for (int i = 0; i < data.points.length; i++) {
            data.gapsLag.add(new Calculations().GetGap(data.points[i], data.steps, data.lagranj));
            new Out().OutGaps(data.gapsLag.get(i), data.points[i], "Лагранж");
            data.gapsNew.add(new Calculations().GetGap(data.points[i], data.steps, data.newton));
            new Out().OutGaps(data.gapsNew.get(i), data.points[i], "Ньютон");
        }
        for (int i = 0; i < data.points.length; i++) {
            data.arrLagranj[i] = new Calculations().CalcLagranj(data.gapsLag.get(i), data.lagranj, data.points[i]);
            new Out().OutPower(data.points[i], data.arrLagranj[i], "Лагранж");
            data.arrNewton[i] = new Calculations().CalcNewton(data.gapsNew.get(i), data.newton, data.points[i], data.step);
            new Out().OutPower(data.points[i], data.arrNewton[i], "Ньютон");
        }

    }
}
