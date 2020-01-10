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

    public double[] GetGap(double value, double[] steps) {
        double[] gap = new double[4];
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] >= value) {
                if (i + 2 <= steps.length) {
                    gap[0] = steps[i];
                    gap[1] = steps[i + 1];
                    gap[2] = steps[i - 1];
                    gap[3] = value;
                    break;
                } else {
                    gap[0] = steps[i];
                    gap[1] = steps[i - 1];
                    gap[2] = steps[i - 2];
                    gap[3] = value;
                    break;
                }
            }
        }
        return gap;
    }
}
