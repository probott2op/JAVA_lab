package com;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void init(String[] args)
    {
        launch(args);
    }    

    @Override
    public void start(Stage PrimaryStage)
    {
        CustomerController controller = new CustomerController();
        Scene PrimaryScene = new Scene(controller.getSignInView());
        PrimaryStage.setScene(PrimaryScene);
        PrimaryStage.setTitle("Customer Database");
        PrimaryStage.show();
    }
}
