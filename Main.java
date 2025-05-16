import java.util.Scanner;

public class Main {

    // Основная функция, корень которой мы ищем
    public static double f(double x) {
        //return x * x - 2.6; // Функция f(x) = x^2 - 2.6 (квадратное уравнение)
        return Math.log(x) - 1; // Функция f(x) = ln(x)
    }

    // Первая производная функции f(x)
    public static double df(double x) {
        //return 2 * x;  // Производная для x^2 - 2.6: f'(x) = 2x
        return 1/x;  // Производная f'(x) = 1/x
    }

    // Вторая производная функции f(x)
    public static double d2f(double x) {
        //return 2; // Вторая производная для x^2 - 2.6: f''(x) = 2
        return -1/Math.pow(x,2);  // Вторая производная f''(x) = -1/x*x
    }

    /*
     Модифицированный метод Ньютона (касательных) для нахождения корня уравнения
     a       - левая граница интервала
     b       - правая граница интервала
     epsilon - требуемая точность вычислений
     return приближенное значение корня
     */
    public static double method(double a, double b, double epsilon) {
        // Выбираем начальное приближение x0
        double x0;

        // Проверяем условие сходимости метода: f(x0)*f''(x0) > 0
        // Это условие гарантирует, что выбранная точка лежит с "правильной" стороны
        if (f(a) * d2f(a) > 0) {
            x0 = a; // Если условие выполняется для левой границы
        } else if (f(b) * d2f(b) > 0) {
            x0 = b; // Если условие выполняется для правой границы
        } else {
            // Если условие не выполняется ни для одной из границ
            throw new IllegalArgumentException("Не выполнено условие f(x0)*f''(x0) > 0 на концах отрезка");
        }

        // Вычисляем минимальное значение производной на отрезке
        // Это нужно для критерия остановки
        double m = Math.min(Math.abs(df(a)), Math.abs(df(b)));

        // Текущее приближение корня
        double xn = x0;
        // Значение функции в текущей точке
        double fxn;
        // Счетчик итераций
        int iteration = 0;
        // Максимальное количество итераций для защиты от бесконечного цикла
        final int MAX_ITERATIONS = 100;
        // Первая производная в т. x0
        double dfx0 = df(x0);

        // Выводим заголовок таблицы итераций
        System.out.println("Итерации метода Ньютона:");
        System.out.printf("%-5s %-10s %-12s%n", "n", "x_n", "f(x_n)");

        // Основной цикл метода
        do {
            // Вычисляем значение функции в текущей точке
            fxn = f(xn);
            // Выводим информацию о текущей итерации
            System.out.printf("%-5d %-10.5f %-12.5f%n", iteration, xn, fxn);

            // Проверяем условие остановки: |f(xn)| ≤ m*ε
            if (Math.abs(fxn) <= m * epsilon) {
                break; // Если достигнута требуемая точность
            }

            // Вычисляем следующее приближение по формуле метода
            // xn+1 = xn - f(xn)/f'(x0)
            xn = xn - fxn / df(x0);
            iteration++;

            // Проверяем, не превышено ли максимальное число итераций
            if (iteration >= MAX_ITERATIONS) {
                System.out.println("Достигнуто максимальное количество итераций");
                break;
            }
        } while (true); // Бесконечный цикл, прерывается по условиям внутри

        return xn; // Возвращаем найденное приближение корня
    }

    public static void main(String[] args) {
        // Создаем объект Scanner для ввода данных (хотя в текущей реализации не используется)
        Scanner sc = new Scanner(System.in);

        // Параметры для уравнения x^2 - 2.6 = 0
        //double a = 1.0;
        //double b = 2.0;
        //double epsilon = 0.001;
        System.out.print("Введите левую границу: "); // Ввод параметров
        double a = sc.nextDouble();
        System.out.print("Введите правую границу: ");
        double b = sc.nextDouble();
        System.out.print("Введите epsilon: ");
        double epsilon = sc.nextDouble();
        // Параметры для уравнения ln(x) - 1 (альтернативный вариант)
        //double a = 2.0;
        //double b = 3.0;
        //double epsilon = 0.001;

        // Находим корень уравнения
        double root = method(a, b, epsilon);

        // Выводим результаты
        System.out.printf("Найденный корень: %.5f%n", root);
        System.out.printf("Проверка: f(%.5f) = %.5f%n", root, f(root));
    }
}
