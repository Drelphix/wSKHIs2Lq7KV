import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.mariuszgromada.math.mxparser.Argument;
public class Calculations {
    public double[] GetSteps(double start, double end, double step) {
        int length = (int) Math.round((end - start) / step);
        double[] steps = new double[length + 1];
        steps[0] = start;
        for (int i = 1; i < steps.length; i++) {
            steps[i] = steps[i - 1] + step;
        }
        return steps;
    }

    public double[] GetYX(double[] steps) {
        double[] yx = new double[steps.length];
        for (int i = 0; i < steps.length; i++) {
            yx[i] = CalcExpression(steps[i]);
        }
        return yx;
    }

    public double CalcExpression(double x) {
        return x * x + 4 * Math.sin(x);
    }

    public double[] GetGap(double value, double[] steps, int power) {
        double[] gap = new double[power + 1];
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] > value) {
                if (i - power < 0) {
                    System.arraycopy(steps, 0, gap, 0, power + 1);
                    break;
                } else System.arraycopy(steps, i - power, gap, 0, power + 1);
                break;
            }
        }
        return gap;
    }

    public double CalcLagranj(double[] steps, int power, double value) {
        double answer = 0.0;
        double temp;
        for (int i = 0; i <= power; i++) {
            temp = 1;
            for (int j = 0; j <= power; j++) {
                if (i == j) {
                    temp *= 1;
                } else temp *= ((value - steps[j]) / (steps[i] - steps[j]));
            }
            answer += temp * CalcExpression(steps[i]);
        }
        return answer;
    }

    public double CalcNewton(double[] steps, int newton, double point, double step) {
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

    public String GetDerivative(String input, int power) {
        Variable xVar = new Variable("x");
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        parser.add(xVar);
        Expression expr = parser.parse(input);
        for (int i = 0; i < power; i++) {
            expr = expr.derivative(xVar);
        }
        return expr.toString();
    }

    public double[] CalcDifferenceFL(double[] FLagranj, double[] LLagranj) {
        double[] answer = new double[FLagranj.length];
        for (int i = 0; i < FLagranj.length; i++) {
            answer[i] = Math.abs(LLagranj[i] - FLagranj[i]);
        }
        return answer;
    }

    public double[] CalcFunction(String derivative, double[] points) {
        Argument x;
        org.mariuszgromada.math.mxparser.Expression e;
        double[] out = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            x = new Argument("x = " + points[i]);
            e = new org.mariuszgromada.math.mxparser.Expression(derivative, x);
            out[i] = Double.valueOf(String.valueOf(e.calculate()));
        }

        return out;
    }

    public double[] CalcMaxFunction(double[] function) {
        double[] out = new double[2];
        out[1] = function[0];
        double temp;
        for (int i = 1; i < function.length; i++) {
            temp = function[i];
            if (out[1] <= temp) {
                out[1] = temp;
                out[0] = i;
            }
        }
        return out;
    }

    public double CalcError(int power, double[] maxFunction, double function) {
        double fact = 1;
        for (int i = 1; i <= power + 1; i++) {
            fact *= i;
        }
        double answer = function / fact * maxFunction[1];
        return answer;
    }

}
