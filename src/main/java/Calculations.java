import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.mariuszgromada.math.mxparser.Argument;

public class Calculations {
    public double[] GetSteps(double start, double end, double step) { //Вычисление шагов, X в таблице
        int length = (int) Math.round((end - start) / step);
        double[] steps = new double[length + 1];
        steps[0] = start;
        for (int i = 1; i < steps.length; i++) {
            steps[i] = steps[i - 1] + step;
            //steps[i] = BigDecimal.valueOf(steps[i]).setScale(5,BigDecimal.ROUND_HALF_DOWN).doubleValue();
        }
        return steps;
    }

    public double[] GetYX(double[] steps) { //Вычисление Y по X
        double[] yx = new double[steps.length];
        for (int i = 0; i < steps.length; i++) {
            yx[i] = CalcExpression(steps[i]);
           // yx[i]=BigDecimal.valueOf(yx[i]).setScale(5,BigDecimal.ROUND_HALF_DOWN).doubleValue();
        }
        return yx;
    }

    public double CalcExpression(double x) {
        return Math.pow(x, 2) + 4 * Math.sin(x);
    } //Рассчет формулы

    public double[] GetGap(double value, double[] steps, int power) { //Получение промежутка
        double[] gap = new double[power + 1];
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] <= value && steps[i + 1] >= value) {
                if (i + power >= steps.length) {
                    i = steps.length - power - 1;
                }
                System.arraycopy(steps, i, gap, 0, power + 1);
                break;

            }
        }
        return gap;
    }

    public double CalcLagranj(double[] steps, int power, double value) { //Рассчет Лагранжа
        double answer = 0.0;
        double temp;
        double calc;
        for (int i = 0; i <= power; i++) {
            temp = 1;
            for (int j = 0; j <= power; j++) {
                if (i == j) {
                    temp *= 1;
                } else {
                    calc = ((value - steps[j]) / (steps[i] - steps[j]));
                    //calc = BigDecimal.valueOf(calc).setScale(5, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //System.out.print("\n"+calc+" = ("+value+" - "+steps[j]+")/("+steps[i]+" - "+steps[j]+")");
                    temp *= calc;

                }

            }
            answer += temp * CalcExpression(steps[i]);
            //answer = BigDecimal.valueOf(CalcExpression(answer)).setScale(64, BigDecimal.ROUND_UNNECESSARY).doubleValue();
           // System.out.print("\n"+answer+" = ("+temp+" * "+BigDecimal.valueOf(CalcExpression(steps[i])).setScale(5, BigDecimal.ROUND_HALF_DOWN).doubleValue()+")");
        }
            return answer;
    }

    public double CalcNewton(double[] steps, int newton, double point, double step) { //Рассчет Ньютона
        double[][] mas = new double[newton + 2][newton + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < newton + 1; j++) {
                if (i == 0)
                    mas[i][j] = steps[j];
                else if (i == 1)
                    mas[i][j] = CalcExpression(steps[j]);
            }
        }
        int m = newton;
        for (int i = 2; i < newton + 2; i++) {
            for (int j = 0; j < m; j++) {
                mas[i][j] = mas[i - 1][j + 1] - mas[i - 1][j];
            }
            m--;
        }
        double[] dy0 = new double[newton + 1];

        for (int i = 0; i < newton + 1; i++) {
            dy0[i] = mas[i + 1][0];
        }
        double res = dy0[0];
        double[] xn = new double[newton];
        xn[0] = point - mas[0][0];
        for (int i = 1; i < newton; i++) {
            double ans = xn[i - 1] * (point - mas[0][i]);
            xn[i] = ans;
            ans = 0;
        }
        int m1 = newton + 1;
        int fact = 1;
        for (int i = 1; i < m1; i++) {
            fact = fact * i;
            res = res + (dy0[i] * xn[i - 1]) / (fact * Math.pow(step, i));
        }
        return res;
    }

    public String GetDerivative(String input, int power) { // Рассчет производной выбранной степени
        Variable xVar = new Variable("x");
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        parser.add(xVar);
        Expression expr = parser.parse(input);
        for (int i = 0; i < power; i++) {
            expr = expr.derivative(xVar);
        }
        return expr.toString();
    }

    public double CalcDifferenceFL(double point, double LLagranj) { //Расчет разницы по модулю
        double answer = Math.abs(CalcExpression(point) - LLagranj);

        return answer;
    }

    public double CalcFunction(String derivative, double points) { //Расчет функции по производной N степени
        Argument x;
        org.mariuszgromada.math.mxparser.Expression e;
        x = new Argument("x = " + points);
        e = new org.mariuszgromada.math.mxparser.Expression(derivative, x);
        double out = Double.valueOf(String.valueOf(e.calculate()));
        return out;
    }

    public double CalcMaxFunction(double[] function) { //Поиск максимума функции
        double out;
        out = function[0];
        double temp;
        for (int i = 1; i < function.length; i++) {
            temp = function[i];
            if (out <= temp) {
                out = temp;
            }
        }
        return out;
    }


    public double CalcError(int power, String derivative, double point,double[] steps) { //Расчет погрешности
        double fact = 1;
        double step = 1;
        for (int i = 1; i <= power + 1; i++) {
            fact *= i;
        }
        for (int i = 0; i < steps.length; i++) {
            step *= (point - steps[i]);
        }
        double answer = (Math.abs(CalcFunction(derivative, steps[steps.length - 1]))) * Math.abs(step) / fact;
        //System.out.print("\n"+Math.abs(CalcFunction(derivative,steps[steps.length-1])));
        //System.out.print("\n"+steps[steps.length-1]);
        // System.out.print("\n"+Math.abs(step));
        return answer;
    }

}
