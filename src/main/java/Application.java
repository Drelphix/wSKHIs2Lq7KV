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
            data.gapsFLag.add(calc.GetGap(data.points[i], data.steps, data.lagranj - 1));
            out.OutGaps(data.gapsLag.get(i), data.points[i], "Лагранж");
            data.gapsNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            data.gapsFNew.add(calc.GetGap(data.points[i], data.steps, data.newton - 1));
            out.OutGaps(data.gapsNew.get(i), data.points[i], "Ньютон");
        }
        for (int i = 0; i < data.points.length; i++) {
            data.arrLagranj[i] = calc.CalcLagranj(data.gapsLag.get(i), data.lagranj, data.points[i]);
            out.OutPower(data.points[i], data.arrLagranj[i], "L Лагранж");
            data.arrFLagranj[i] = calc.CalcLagranj(data.gapsFLag.get(i), data.lagranj - 1, data.points[i]);
            out.OutPower(data.points[i], data.arrFLagranj[i], "F Лагранж");
            data.arrNewton[i] = calc.CalcNewton(data.gapsNew.get(i), data.newton, data.points[i], data.step);
            out.OutPower(data.points[i], data.arrNewton[i], "N Ньютон");
            data.arrFNewton[i] = new Calculations().CalcNewton(data.gapsFNew.get(i), data.newton - 1, data.points[i], data.step);
            new Out().OutPower(data.points[i], data.arrFNewton[i], "F Ньютон");
        }
        out.OutString("\n Оценка погрешности вычисления Лагранжа");
        data.derivativeLag = calc.GetDerivative(data.expression, data.lagranj);
        out.OutString("\n P(t) -> " + data.derivativeLag);
        out.OutString("\n Оценка погрешности вычисления Ньютона");
        data.derivativeNew = calc.GetDerivative(data.expression, data.newton);
        out.OutString("\n P(t) -> " + data.derivativeNew);
        for (int i = 0; i < data.points.length; i++) {
            out.OutString("\n L(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.arrFLagranj, data.arrLagranj)[i]);
            out.OutString("\n N(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.arrFNewton, data.arrNewton)[i]);
        }
        data.functionLag = calc.CalcFunction(data.derivativeLag, data.points);
        data.maxFunctionLag = calc.CalcMaxFunction(data.functionLag);
        data.functionNew = calc.CalcFunction(data.derivativeNew, data.points);
        data.maxFunctionNew = calc.CalcMaxFunction(data.functionNew);
        System.out.print("\n Максимум функции Лагранжа в точке " + data.maxFunctionLag[0] + " равен " + data.maxFunctionLag[1]);
        System.out.print("\n Максимум функции Ньютона в точке " + data.maxFunctionNew[0] + " равен " + data.maxFunctionNew[1]);
        System.out.print("\n Погрешность Лагранжа для точки " + data.maxFunctionLag[0] + " равна " + calc.CalcError(data.lagranj, data.maxFunctionLag, data.functionLag[(int) data.maxFunctionLag[0]]));
        System.out.print("\n Погрешность Ньютона для точки " + data.maxFunctionNew[0] + " равна " + calc.CalcError(data.newton, data.maxFunctionNew, data.functionNew[(int) data.maxFunctionNew[0]]));
        try {
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/z2pourxil1");
            Runtime.getRuntime().exec("cmd /c start https://www.desmos.com/calculator/jfdrzrv3fu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
