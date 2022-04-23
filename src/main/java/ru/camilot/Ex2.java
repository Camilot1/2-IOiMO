package ru.camilot;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.List;

public class Ex2 {

    public void execute() {

        String[] roots = new String[] {
                "Омск -> Нижний Новгород", "Омск -> Пермь", "Омск -> Краснодар",
                "Новосибирск -> Нижний Новгород", "Новосибирск -> Пермь", "Новосибирск -> Краснодар",
                "Томск -> Нижний Новгород", "Томск -> Пермь", "Томск -> Краснодар",
        };

        LinearObjectiveFunction f = new LinearObjectiveFunction(
                new double[] {
                        3637, 3043,	4386,
                        3793, 3165, 4610,
                        4509, 3714,	5607
                }, 0);

        //Создаём список с ограничениями
        List<LinearConstraint> constraints = new ArrayList<>();

        constraints.add(new LinearConstraint(new double[] {
                                1, 1, 1,
                                0, 0, 0,
                                0, 0, 0
                        }, Relationship.EQ, 2000));
        constraints.add(new LinearConstraint(new double[] {
                                0, 0, 0,
                                1, 1, 1,
                                0, 0, 0
                        }, Relationship.EQ, 900));
        constraints.add(new LinearConstraint(new double[] {
                                0, 0, 0,
                                0, 0, 0,
                                1, 1, 1
                        }, Relationship.EQ, 400));
        constraints.add(new LinearConstraint(new double[] {
                                1, 0, 0,
                                1, 0, 0,
                                1, 0, 0
                        }, Relationship.EQ, 1500));
        constraints.add(new LinearConstraint(new double[] {
                                0, 1, 0,
                                0, 1, 0,
                                0, 1, 0
                        }, Relationship.EQ, 1000));
        constraints.add(new LinearConstraint(new double[] {
                                0, 0, 1,
                                0, 0, 1,
                                0, 0, 1
                        }, Relationship.EQ, 800));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(
                new MaxIter(1000), f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true)
        );

        double fs = solution.getValue();
        double[] fx = solution.getPoint();

        System.out.println("Затраты на перевозку: " + fs + "\n");

        for (int i = 0; i < fx.length; i++) {
            System.out.println(roots[i] + ": " + fx[i] + " изделий");
        }
    }
}