package ru.camilot.num3;

import com.sun.javafx.geom.Vec2d;
import ru.camilot.IFunction;

public class Main {

    /**
     * Точка входа в программу
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        //Функция и её производная задаётся с помощью лямбда-выражений
        Vec2d result = calculate((x) -> (x-11)*(x-11), (x) -> 2*(x-11), 0, 15, 0.1);
        System.out.println("x_min = " + result.x + "; f_min = " + result.y);
    }

    /**
     * Метод поиска точки с наименьшим значение
     * @param f функция для вычисления данных
     * @param df производная функции для вычисления данных
     * @param min левая граница диапазона данных по оси Х
     * @param max правая граница диапазона данных по оси Х
     * @param e точность
     * @return рассчётные данные
     */
    private static Vec2d calculate(IFunction f, IFunction df, double min, double max, double e) {
        double middle, x_min;
        int k = 0;

        while (true) {
            middle = (min + max) / 2;

            if (df.calculate(middle) < 0) min = middle;
            else max = middle;

            k++;
            if (Math.abs(df.calculate(middle)) < e) break;
        }

        x_min = (min + max) / 2;

        System.out.println("Число итераций: " + k);

        return new Vec2d(x_min, f.calculate(x_min));
    }
}
