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
            System.out.print("\n Погрешность равна " + calc.CalcError(data.lagranj, data.derivativeLag, data.points[i], data.steps));
            System.out.println("\n L(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.points[i], data.arrLagranj[i]));
            data.gapsNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            data.gapsFNew.add(calc.GetGap(data.points[i], data.steps, data.newton));
            out.OutGaps(data.gapsNew.get(i), data.points[i], "Ньютон");
            data.arrNewton[i] = calc.CalcNewton(data.gapsNew.get(i), data.newton, data.points[i], data.step);
            out.OutPower(data.points[i], data.arrNewton[i], "Ньютон");
            System.out.print("\n Погрешность равна " + calc.CalcError(data.newton, data.derivativeNew, data.points[i], data.steps));
            System.out.println("\n N(" + i + ") - f(" + i + ") = " + calc.CalcDifferenceFL(data.points[i], data.arrNewton[i]));
        }
       /* try {
          //  Runtime.getRuntime().exec("cmd /c start http://yotx.ru/#!1/3_h/sH@1v7flJqbf9o/2D/YN9PSq3t74H3oCDwPxC@sbe7f7BPomE3dk4Zj6dbjMety4vd/a39re2dzbPLHfDe@sX2BeIMunMJ3tve2oHuHFyC99Z3thFnUBAIBt5bB21vwRBn@wf7JBp2Y@fsjPG4tXXJeNxC7O5v7W8cQE53YBdbsLOLA8QW4vIMdsrbgSBAZ5e7G1uQnbPT0wvEwekW6OLscguxwzuDXMAuQLsbWxAYDHR5dnZ2ubN1dgFDnF3yEJCLM8TF7sYO5GzrYgcB2trZgSG2YBegMx4McnqKQOxuXEC2QFtnlzunZxdnsEvE5QXvEgI7vTzd3d/ZJ9GwGzunjMfTLcbj1uXF7v7B/j4C");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
