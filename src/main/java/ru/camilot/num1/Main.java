package ru.camilot.num1;

import com.sun.javafx.geom.Vec2d;
import ru.camilot.IFunction;

public class Main {

    /**
     * Точка входа в программу
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        //Функция задаётся с помощью лямбда-выражения
        Vec2d result = calculate((x) -> (x-11)*(x-11), 0, 15, 50);
        System.out.println("x_min = " + result.x + "; f_min = " + result.y);
    }

    /**
     * Метод поиска точки с наименьшим значение
     * @param f функция для вычисления данных
     * @param min левая граница диапазона данных по оси Х
     * @param max правая граница диапазона данных по оси Х
     * @param N число отрезков
     * @return рассчётные данные
     */
    private static Vec2d calculate(IFunction f, double min, double max, int N) {
        double x, y;
        double x_min = min, y_min = f.calculate(min);

        for (int i = 1; i < N; i++) {
            x = min + i*(max - min) / (N + 1);
            y = f.calculate(x);
            if (y < y_min) {
                x_min = x;
                y_min = y;
            }
        }

        return new Vec2d(x_min, y_min);
    }
}


