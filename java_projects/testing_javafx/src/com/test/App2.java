package com.test;
// javac --module-path "/Users/Jaiwant/Downloads/javafx-sdk-23/lib" --add-modules javafx.controls,javafx.fxml App.java
// java --module-path "/Users/Jaiwant/Downloads/javafx-sdk-23/lib" --add-modules javafx.controls,javafx.fxml App
// learnt about packages also, can use javac -cp "package_path" -d "path_store,default is present dir" App.java
//java -cp "package_path:path_App.class,default is present dir" App
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
public class App2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Stage stg = new stage()
        Button button = new Button("Click Me");
        button.setOnAction(e -> showAlert());
        // StackPane , group, multiple such exist, it dicates how the children are order in the scene;
        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Simple JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Button Clicked!");
        alert.showAndWait();
    }

    public static void init(String[] args) {
        launch(args);
    }
}
