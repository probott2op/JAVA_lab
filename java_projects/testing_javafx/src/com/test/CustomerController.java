package com.test;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Region;
public class App3 extends Application 
{
    public static void init(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setScene(new Scene(new CustomerController().getView()));
        primaryStage.show();
    }
       
}
public class CustomerController
{
    private Builder<Region> viewBuilder;
    private CustomerInteractor interactor;

    public CustomerController() {
        CustomerModel model = new CustomerModel();
        viewBuilder = new CustomerViewBuilder(model);
        interactor = new CustomerInteractor(model);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
