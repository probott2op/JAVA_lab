package com.test;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
// import javafx.geometry.Insets;
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

public class App1 extends Application {

    public static void init(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent(),400,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JavaFX"); // Optional: Set title
        primaryStage.show();
    }
    // These get dynamically update see, where I use .bindBiderectional()
    StringProperty name = new SimpleStringProperty();
    StringProperty output = new SimpleStringProperty();
    
    // Region is the super class of HBox & VBox
    private Region createContent() {
        VBox box = new VBox(createInpBox(),final_output(),create_button());
        box.setAlignment(Pos.CENTER);
        // System.out.println(getClass().getResource("App1.css"));
        box.getStylesheets().add(this.getClass().getResource("styles/App1.css").toExternalForm());
        return box;
    }
    // A textbox, coupled with a Label
    private Node createInpBox()
    {
        TextField user_inp = new TextField("");
        user_inp.textProperty().bindBidirectional(name);
        /* Label prompt = new Label("Name: ");
        prompt.getStyleClass().add("prompt-label"); */
        HBox temp_box = new HBox(styledLabel("Name: ", "prompt-label"),user_inp);
        temp_box.setSpacing(6);
        // box.setPadding(new Insets(0,0,0,50));
        // temp_box.setPadding(new Insets(50));
        temp_box.setAlignment(Pos.CENTER);
        return temp_box;
    }
    private Node create_button()
    {
        Button results = new Button("Enter");
        results.setOnAction(event -> setgreeting());
        return results;
    }
    private void setgreeting()
    {
        output.set("Hello "+name.get());
    }
    private Node final_output()
    {
        Label final_out = styledLabel("","greeting-label");
        final_out.textProperty().bind(output);
        // final_out.getStyleClass().add("greeting-label");
        return final_out;
    }
    private Label styledLabel(String inp,String css_class)
    {
        Label temp = new Label(inp);
        temp.getStyleClass().add(css_class);
        return temp;
    }
}
// this is a modularized code where I broke everything into sub-parts