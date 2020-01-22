import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    String expression = "x*x+4*sin(x)";
    String derivativeLag;
    String derivativeNew;
    double start = 0;
    double end = 10; //3.14159265359
    double step = 2;//0.62831853071
    int lagranj = 3;
    int newton = 2;
    double[] points = {5, 6, 9};

    ArrayList<double[]> gapsLag = new ArrayList<double[]>();
    ArrayList<double[]> gapsNew = new ArrayList<double[]>();
    ArrayList<double[]> gapsFLag = new ArrayList<double[]>();
    ArrayList<double[]> gapsFNew = new ArrayList<double[]>();


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
