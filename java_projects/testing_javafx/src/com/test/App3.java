package com.test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App3 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new CustomerController().getView()));
        primaryStage.show();
    }
    public static void init(String[] args)
    {
        launch(args);
    }
}
