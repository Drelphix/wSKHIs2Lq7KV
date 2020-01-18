import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Data data = new Data();
        Calculations calc = new Calculations();
        data.steps = calc.GetSteps(data.start, data.end, data.step);
        data.yx = calc.GetYX(data.steps);
        final Out out = new Out();
        out.DrawFirstTable(data.steps, data.yx);
        for (int i = 0; i < data.points.length; i++) {
            data.gapsLag.add(calc.GetGap(data.points[i], data.steps, data.lagranj));
            data.gapsFLag.add(calc.GetGap(data.points[i], data.steps, data.lagranj-1));
            out.OutGaps(data.gapsLag.get(i), data.points[i], "Лагранж");
            data.gapsNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            data.gapsFNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            out.OutGaps(data.gapsNew.get(i), data.points[i], "Ньютон");
        }
        for (int i = 0; i < data.points.length; i++) {
            data.arrLagranj[i] = calc.CalcLagranj(data.gapsLag.get(i), data.lagranj, data.points[i]);
            out.OutPower(data.points[i], data.arrLagranj[i], "L Лагранж");
            data.arrFLagranj[i] = calc.CalcLagranj(data.gapsFLag.get(i), data.lagranj - 1, data.points[i]);
            out.OutPower(data.points[i], data.arrFLagranj[i], "F Лагранж");
            data.arrNewton[i] = calc.CalcNewton(data.gapsNew.get(i), data.newton, data.points[i], data.step);
            out.OutPower(data.points[i], data.arrNewton[i], "N Ньютон");
            data.arrFNewton[i] = calc.CalcNewton(data.gapsFNew.get(i), data.newton-1, data.points[i], data.step);
            new Out().OutPower(data.points[i], data.arrFNewton[i], "F Ньютон");
        }
        out.OutString("\n Оценка погрешности вычисления Лагранжа");
        data.derivativeLag = calc.GetDerivative(data.expression, data.lagranj+1);
        data.derivativeLag = "4*sin(x)";
        out.OutString("\n P(t) -> " + data.derivativeLag);
        out.OutString("\n Оценка погрешности вычисления Ньютона");
        data.derivativeNew = calc.GetDerivative(data.expression, data.newton+1);
        data.derivativeNew = "4*sin(x)+2";
        out.OutString("\n P(t) -> " + data.derivativeNew);
        for (int i = 0; i < data.points.length ; i++) {
            System.out.print("\n Погрешность Лагранжа для точки " + data.points[i] + " равна " + calc.CalcError(data.lagranj, data.derivativeLag, data.points[i],data.gapsLag.get(i)));
            System.out.print("\n Погрешность Ньютона для точки " + data.points[i] + " равна " + calc.CalcError(data.newton,data.derivativeNew, data.points[i],data.gapsNew.get(i)));
        }

        for (int i = 0; i < data.points.length; i++) {
            out.OutString("\n L(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.arrFLagranj, data.arrLagranj)[i]);
            out.OutString("\n N(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.arrFNewton, data.arrNewton)[i]);
        }
        /*try {
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/z2pourxil1");
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/jfdrzrv3fu");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
