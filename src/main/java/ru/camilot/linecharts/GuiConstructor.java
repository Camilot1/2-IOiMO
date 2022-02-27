package ru.camilot.linecharts;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class GuiConstructor {

    /**
     * Метод создания окна линейного графика
     * @param x позиция окна графика по X
     * @param y позиция окна графика по Y
     * @param width ширина окна графика
     * @param height высота окна графика
     * @param chartName название окна графика
     * @param xAxisName название оси X
     * @param yAxisName название оси Y
     * @return окно линейного графика
     */
    public static LineChart<Number, Number> createLineChart(
            double x, double y, double width, double height,
            String chartName, String xAxisName, String yAxisName
    ) {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(xAxisName);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisName);

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle(chartName);

        lineChart.setLayoutX(x);
        lineChart.setLayoutY(y);
        lineChart.setPrefWidth(width);
        lineChart.setPrefHeight(height);

        return lineChart;
    }
}
