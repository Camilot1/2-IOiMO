package ru.camilot;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Ex1 {

    public void execute() {

        /* Массив из 12 продуктов питания
         *  Данные записаны в виде:
         *  <название>, <белки>, <жиры>, <углеводы>, <ккал>, <цена>
         */
        Food[] food = new Food[]{
                new Food("Яйцо куриное", 12.7, 11.5, 0.7, 157, 15),
                new Food("Грецкий орех", 13.8, 61.3, 10.2, 647, 44),
                new Food("Крупа гречневая", 12.6, 2.6, 68, 345, 6),
                new Food("Рис", 8, 1, 76, 345, 6),
                new Food("Творог", 7.1, 23, 27.5, 345, 13),
                new Food("Сыр", 27, 40, 0, 468, 18),
                new Food("Свинина", 16.4, 27.8, 0, 315, 23),
                new Food("Картофель", 2, 0.1, 19.7, 87, 2.5),
                new Food("Яблоки", 3.2, 0, 68, 284, 7),
                new Food("Бананы", 1.5, 0, 22, 94, 4)
        };

        LinearObjectiveFunction f = createLOFunction(food, 0);

        /* Создаём объект с ограничениями.
         *  Данные записаны в виде:
         *  <белки>, <жиры>, <углеводы>, <ккал>
         */
        Constraint constraint = new Constraint(60, 70, 280, 1826);

        //Создаём список с ограничениями
        List<LinearConstraint> constraints = createLinearConstraints(food, Relationship.GEQ, constraint);

        //constraints.addAll(createIndexedLinearConstraints(food.length, Relationship.LEQ, 4));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(
                new MaxIter(1000), f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true)
        );

        double fs = solution.getValue();
        double[] fx = solution.getPoint();

        System.out.println("Суммарная цена продуктов: " + fs + "\n");

        //Создаём шаблон вывода числовых значений с плавающей точкой, чтобы отбросить символы после 2-ого знака после запятой
        final DecimalFormat df = new DecimalFormat("#.##");

        //Выводим информацию о весе продуктов в рационе питания
        for (int i = 0; i < fx.length; i++) {
            System.out.println(food[i].name + ": " + fx[i] + " ("+ df.format(fx[i] * 100) + " грамм)");
        }
    }

    public LinearObjectiveFunction createLOFunction(Food[] food, double ct) {
        double[] coefficients = new double[food.length];
        for (int i = 0; i < coefficients.length; i++) coefficients[i] = food[i].cost;
        return new LinearObjectiveFunction(coefficients, ct);
    }

    /**
     * Метод, добавляющий ограничение на употребление не более 400 грамм каждого продукта
     * @param length количество продуктов
     * @param relationship тип связи
     * @param constraint максимальное значение
     * @return список ограничителей
     */
    public static List<LinearConstraint> createIndexedLinearConstraints(int length, Relationship relationship, double constraint) {
        List<LinearConstraint> constraints = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            double[] coefficients = new double[length];
            coefficients[i] = 1;
            constraints.add(new LinearConstraint(coefficients, relationship, constraint));
        }

        return constraints;
    }

    /**
     * Метод создания списка с ограничениями норм белков, жиров, углеводов и каллорий
     * @param food массив продуктов питания
     * @param relationship тип связи
     * @param constraint объект с данными о суточной потребности человека в белке, жире, углеводах и каллориях
     * @return список ограничителей
     */
    public List<LinearConstraint> createLinearConstraints(Food[] food, Relationship relationship, Constraint constraint) {
        List<LinearConstraint> constraints = new ArrayList<>();
        double[] coefficients = new double[food.length];

        //Белки
        for (int i = 0; i < coefficients.length; i++) coefficients[i] = food[i].p;
        constraints.add(new LinearConstraint(coefficients, relationship, constraint.p));

        //Жиры
        for (int i = 0; i < coefficients.length; i++) coefficients[i] = food[i].f;
        constraints.add(new LinearConstraint(coefficients, relationship, constraint.f));

        //Углеводы
        for (int i = 0; i < coefficients.length; i++) coefficients[i] = food[i].c;
        constraints.add(new LinearConstraint(coefficients, relationship, constraint.c));

        //Каллории
        for (int i = 0; i < coefficients.length; i++) coefficients[i] = food[i].k;
        constraints.add(new LinearConstraint(coefficients, relationship, constraint.k));

        return constraints;
    }
}
