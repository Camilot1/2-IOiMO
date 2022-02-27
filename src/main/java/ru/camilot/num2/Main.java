package ru.camilot.num2;

import com.sun.javafx.geom.Vec2d;
import ru.camilot.IFunction;

public class Main {

    /**
     * Точка входа в программу
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        //Функция задаётся с помощью лямбда-выражения
        Vec2d result = calculate((x) -> (x-11)*(x-11), 0, 15, 0.1);
        System.out.println("x_min = " + result.x + "; f_min = " + result.y);
    }

    /**
     * Метод поиска точки с наименьшим значение
     * @param f функция для вычисления данных
     * @param min левая граница диапазона данных по оси Х
     * @param max правая граница диапазона данных по оси Х
     * @param e точность
     * @return рассчётные данные
     */
    private static Vec2d calculate(IFunction f, double min, double max, double e) {
        double x1 = min, x2, x3, dx = 1.5, xp;

        x2 = x1 + dx;
        if (f.calculate(x2) < f.calculate(x1)) x3 = x1 + 2*dx;
        else x3 = x1 - dx;

        if (x3 < x1) {
            xp = x3;
            x3 = x2;
            x2 = x1;
            x1 = xp;
        }

        double y1, y2, y3, y_min, x_min, a1, a2, xs;

        while (true) {
            y1 = f.calculate(x1);
            y2 = f.calculate(x2);
            y3 = f.calculate(x3);
            y_min = y1;
            x_min = x1;

            if (y2 < y_min) {
                x_min = x2;
                y_min = y2;
            }
            if (y3 < y_min) {
                x_min = x3;
                y_min = y3;
            }

            a1 = (y2 - y1) / (x2 - x1);
            a2 = 1 / (x3 - x2) * ((y3 - y1) / (x3 - x1) - (y2 - y1) / (x2 - x1));
            xs = (x2 + x1) / 2 - a1 / (2 *a2);

            if (Math.abs(y_min - f.calculate(xs)) < e && Math.abs(x_min - xs) < e) break;

            if (x2 < xs) {
                x1 = x2;
                x2 = xs;
            } else if (x2 > x2) x1 = x2;

        }

        if (y_min < f.calculate(xs)) return new Vec2d(x_min, y_min);
        else return new Vec2d(xs, f.calculate(xs));
    }
}
