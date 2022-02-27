package ru.camilot.linecharts;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double winWidth = 600;
    public static final double winHeight = 500;

    /**
     * Точка входа в программу
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Метод запуска графического интерфейса
     * @param primaryStage главное окно программы
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Program");
        primaryStage.setWidth(winWidth);
        primaryStage.setHeight(winHeight);

        primaryStage.setScene(new MainScene().getScene());
        primaryStage.show();
    }

}
