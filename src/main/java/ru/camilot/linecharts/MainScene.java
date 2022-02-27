package ru.camilot.linecharts;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import ru.camilot.IFunction;

import java.util.ArrayList;
import java.util.List;

public class MainScene {

    private final Scene scene;

    public MainScene() {
        AnchorPane root;
        scene = new Scene(root = new AnchorPane());

        LineChart<Number, Number> lineChart;
        root.getChildren().add(
                lineChart = GuiConstructor.createLineChart(
                        5, 5, Main.winWidth - 50, Main.winHeight - 75,
                        "", "X", "f(X)"
                )
        );

        //Создаём набор данных
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

        //Добавляем данные в набор данных из списка, полученного по результату рассчётов в методе calculateData();
        //Функция задаётся с помощью лямбда-выражения
        series.getData().addAll(calculateData((x) -> (x-11)*(x-11), 0, 15, 1));

        //Добавляем набор данных на график в виде линии
        lineChart.getData().add(series);

    }

    /**
     * Метод рассчёта набора данных на определённом промежутке с определённым шагом
     * @param f функция для вычисления данных
     * @param min левая граница диапазона данных по оси Х
     * @param max правая граница диапазона данных по оси Х
     * @param tick шаг данных в диапазоне по оси Х
     * @return список рассчётных данных
     */
    private List<XYChart.Data<Number, Number>> calculateData(IFunction f, double min, double max, double tick) {
        List<XYChart.Data<Number, Number>> list = new ArrayList<XYChart.Data<Number, Number>>();

        double x = min;
        while (x < max) {
            list.add(new XYChart.Data<>(x, f.calculate(x)));
            x += tick;
        }
        list.add(new XYChart.Data<>(x, f.calculate(max)));

        return list;
    }

    public Scene getScene() {
        return scene;
    }
}
