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

    public double CalcLagranj(double[] steps, double[] yx, int lagranj, double value) {
        double answer = 0.0;
        for (int i = 0; i < lagranj; i++) {
            for (int j = 0; j < lagranj; j++) {
                if (i != j) {
                    answer += yx[i] * ((value - steps[j]) / (steps[i] - steps[j]));
                }
            }
        }
        return answer;
    }

    public double CalcNewton(double[] steps, double[] yx, int newton) {
        double answer = 0.0;

        return answer;
    }
}
