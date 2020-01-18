import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Data data = new Data();
        Calculations calc = new Calculations();
        data.steps = calc.GetSteps(data.start, data.end, data.step);
        data.yx = calc.GetYX(data.steps);
        final Out out = new Out();
        out.DrawFirstTable(data.steps, data.yx);
        data.derivativeLag = calc.GetDerivative(data.expression, data.lagranj + 1);
        System.out.print("\n P(t)L -> " + data.derivativeLag);
        data.derivativeNew = calc.GetDerivative(data.expression, data.newton + 1);
        System.out.print("\n P(t)N -> " + data.derivativeNew);
        for (int i = 0; i < data.points.length; i++) {
            data.gapsLag.add(calc.GetGap(data.points[i], data.steps, data.lagranj));
            data.gapsFLag.add(calc.GetGap(data.points[i], data.steps, data.lagranj - 1));
            out.OutGaps(data.gapsLag.get(i), data.points[i], "Лагранж");
            data.arrLagranj[i] = calc.CalcLagranj(data.gapsLag.get(i), data.lagranj, data.points[i]);
            out.OutPower(data.points[i], data.arrLagranj[i], "Лагранж");
            System.out.print("\n Погрешность равна " + calc.CalcError(data.lagranj, data.derivativeLag, data.points[i], data.gapsLag.get(i)));
            System.out.println("\n L(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.points[i], data.arrLagranj[i]));
            data.gapsNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            data.gapsFNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            out.OutGaps(data.gapsNew.get(i), data.points[i], "Ньютон");
            data.arrNewton[i] = calc.CalcNewton(data.gapsNew.get(i), data.newton, data.points[i], data.step);
            out.OutPower(data.points[i], data.arrNewton[i], "Ньютон");
            System.out.print("\n Погрешность равна " + calc.CalcError(data.newton, data.derivativeNew, data.points[i], data.gapsNew.get(i)));
            System.out.println("\n N(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.points[i], data.arrNewton[i]));
        }
        try {
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/z2pourxil1");
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/jfdrzrv3fu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
