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
        double output = steps[0];
        double[][] f;
        double temp;
        double tempo = 1;
        double[] tetete = GetGap(point, steps, newton + 1);
        f = CalcTable(tetete, yx, newton);
        for (int i = 0; i < newton; i++) {
            temp = 1;
            for (int j = 0; j <= i; j++) {
                temp *= (point - steps[j]);
            }
            tempo = temp * f[i][0];
            output += tempo;
        }
        return output;
    }

    private double[][] CalcTable(double[] steps, double[] yx, int newton) {
        double[][] table = new double[steps.length][steps.length];
        for (int i = 0; i < steps.length - 1; i++) {
            table[0][i] = (yx[i + 1] - yx[i]) / (steps[i + 1] - steps[i]);
            System.out.print("\n table" + "0," + i + " " + table[0][i]);
        }
        int k = 0;

        for (int i = 1; i < steps.length - 1; i++) {
            int f = 0;
            k = i;
            for (int j = i + 1; j <= steps.length - i; j++) {
                if (j < steps.length - i - 1) {
                    table[i][j] = ((table[i - 1][j] - table[i - 1][j - 1]) / (steps[k] - steps[i - 1]));
                } else {
                    table[i][j] = ((table[i - 1][j] - table[i - 1][j - 1]) / (steps[k + 1] - steps[f]));
                    f++;
                    System.out.print("\nСтрока " + (i - 1) + " Столбец " + j + " равна" + table[i - 1][j]);
                    System.out.print("\nСтрока " + (i - 1) + " Столбец " + (j - 1) + " равна" + table[i - 1][j - 1]);
                    System.out.print("\nСтрока  равна" + steps[k + 1]);
                    System.out.print("\nСтрока  равна" + steps[f - 1]);
                    System.out.print("\nСтрока " + i + " Столбец " + j + " равна" + table[i][j]);
                    k++;
                }
            }

        }
        return table;
    }
}
