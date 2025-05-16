import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);


    public static double f(double x) {
        return x * x - 2.6; // Функция f(x) = x^2 - 2.6
    }

    public static double df(double x) {
        return 2 * x;  // Производная функции f'(x) = 2x
    }

    public static double d2f(double x) {
        return 2; // Вторая производная f''(x) = 2
    }

    // Метод Ньютона для нахождения корня
    public static double newtonMethod(double a, double b, double epsilon) {
        // Выбираем начальное приближение x0
        double x0;

        // Проверяем условие f(x0)*f''(x0) > 0
        if (f(a) * d2f(a) > 0) {
            x0 = a;
        } else if (f(b) * d2f(b) > 0) {
            x0 = b;
        } else {
            throw new IllegalArgumentException("Не выполнено условие f(x0)*f''(x0) > 0 на концах отрезка");
        }

        double m = Math.min(Math.abs(df(a)), Math.abs(df(b))); // m = min|f'(x)| на [a,b]
        double xn = x0;
        double fxn;
        int iteration = 0;
        final int MAX_ITERATIONS = 100; // Защита от бесконечного цикла

        System.out.println("Итерации метода Ньютона:");
        System.out.printf("%-5s %-10s %-12s%n", "n", "x_n", "f(x_n)");

        do {
            fxn = f(xn);
            System.out.printf("%-5d %-10.5f %-12.5f%n", iteration, xn, fxn);

            if (Math.abs(fxn) <= m * epsilon) {
                break;
            }

            xn = xn - fxn / df(xn);
            iteration++;

            if (iteration >= MAX_ITERATIONS) {
                System.out.println("Достигнуто максимальное количество итераций");
                break;
            }
        } while (true);

        return xn;
    }

    public static void main(String[] args) {
        double a = 1.0;
        double b = 2.0;
        double epsilon = 0.001;

        System.out.println("Решение уравнения x^2 - 2.6 = 0 методом Ньютона");
        System.out.println("Отрезок: [" + a + ", " + b + "]");
        System.out.println("Точность: " + epsilon);

        double root = newtonMethod(a, b, epsilon);

        System.out.printf("Найденный корень: %.5f%n", root);
        System.out.printf("Проверка: f(%.5f) = %.5f%n", root, f(root));
    }
}
