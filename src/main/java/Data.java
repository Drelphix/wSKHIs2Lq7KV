import java.util.ArrayList;

public class Data {
    String expression = "x * x + 4 * sin(x)";
    String derivativeLag;
    String derivativeNew;
    double start = 0;
    double end = Math.PI;
    double step = Math.PI / 5;
    int lagranj = 4;
    int newton = 2;
    ArrayList<double[]> gapsLag = new ArrayList<double[]>();
    ArrayList<double[]> gapsNew = new ArrayList<double[]>();
    ArrayList<double[]> gapsFLag = new ArrayList<double[]>();
    ArrayList<double[]> gapsFNew = new ArrayList<double[]>();
    double[] points = {0.71, 1.54, 3.01};
    double[] steps;
    double[] yx;
    double[] arrLagranj = new double[points.length];
    double[] arrNewton = new double[points.length];
    double[] arrFLagranj = new double[points.length];
    double[] arrFNewton = new double[points.length];
    double[] functionLag = new double[points.length];
    double[] functionNew = new double[points.length];
    double[] maxFunctionLag = new double[2];
    double[] maxFunctionNew = new double[2];

    public Data() {
       /* Scanner in = new Scanner(System.in);
        System.out.println("Функция x^2+4*sin(x)");
        System.out.println("Для ввода числа pi, введите pi");
        System.out.println("Введите начало отрезка");
        this.start = ifPi(in.nextLine());
        System.out.println("Введите конец отрезка");
        this.end=ifPi(in.nextLine());
        System.out.println("Введите шаг");
        this.step = Math.PI/5;
        System.out.println("Введите степень многочлена Лангранжа");
        this.lagranj = in.nextInt();
        System.out.println("Введите степень многочлена Ньютона");
        this.newton = in.nextInt();
        System.out.println("Введите точки для вычисления значений\n" +
                "интерполяционных многочленов");
        for (int i = 0; i < 3; i++) {
            this.points[i]=in.nextDouble();
        }*/
    }

    public double ifPi(String line) {
        if (line.toLowerCase().equals("pi")) {
            return Math.PI;
        } else return Double.valueOf(line);
    }

}
