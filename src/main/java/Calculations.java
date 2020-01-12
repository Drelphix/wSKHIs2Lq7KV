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

    public double[] GetGap(double value, double[] steps, int lagranj) {
        double[] gap = new double[lagranj + 1];
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] > value) {
                if (i - lagranj < 0) {
                    System.arraycopy(steps, 0, gap, 0, lagranj + 1);
                    break;
                } else System.arraycopy(steps, i - lagranj, gap, 0, lagranj + 1);
                break;
            }
        }
        return gap;
    }

    public double CalcLagranj(double[] steps, double[] yx, int start, int lagranj, double value) {
        double answer = 0.0;
        double temp;
        for (int i = 0; i <= lagranj; i++) {
            temp = 1;
            for (int j = 0; j <= lagranj; j++) {
                if (i != j) {
                    temp *= ((value - steps[j]) / (steps[i] - steps[j]));
                }
            }
            answer += temp * yx[start];
            start++;
        }
        return answer;
    }

    public double CalcNewton(double[] steps, double[] yx, int newton, double point, double step) {
        double[][] mas = new double[newton + 2][newton + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < newton + 1; j++) {
                if (i == 0)
                    mas[i][j] = steps[j];
                else if (i == 1)
                    mas[i][j] = yx[j];
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

    private double[] CalcTable(double[] table) {
        return table;
    }
}
