import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    String expression = "x^2+4*sin(x)";//work only at sin/cos
    String derivativeLag;
    String derivativeNew;
    double start = 0;
    double end = Math.PI;
    double step = Math.PI / 5;
    int lagranj = 4;
    int newton = 2;
    double[] points = {0.71, 1.54, 3.01};

    ArrayList<double[]> gapsLag = new ArrayList<double[]>();
    ArrayList<double[]> gapsNew = new ArrayList<double[]>();
    double[] steps;
    double[] yx;
    double[] arrLagranj = new double[points.length];
    double[] arrNewton = new double[points.length];


    public Data() {
       Scanner in = new Scanner(System.in);
        /*System.out.println("Функция x^2+4*sin(x)");
        System.out.println("Введите начало отрезка");
        this.start=in.nextDouble();
        System.out.println("Введите конец отрезка");
        this.end = in.nextDouble();
        System.out.println("Введите шаг");
        this.step = in.nextDouble();
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

}
