package com;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.BoilerPlateUI.CreateLabel;
import static com.BoilerPlateUI.CreateTextBox;
import static com.BoilerPlateUI.CreateButton;



public class CustomerViewBuilder implements Builder<Region>
{
    CustomerModel model;
    Runnable SignInHandler;
    Runnable SignUpHandler;
    CustomerViewBuilder(CustomerModel model,Runnable SignInHandler,Runnable SignUpHandler)
    {
        this.model = model;
        this.SignInHandler = SignInHandler;
        this.SignUpHandler = SignUpHandler;
    }
    @Override
    public Region build()
    {
        Image Mainimg = new Image(getClass().getResourceAsStream("Images/BankBackground.jpg"));
        ImageView img = new ImageView(Mainimg);
        img.setFitWidth(800);
        img.setFitHeight(600);
        
        StackPane pane = new StackPane();
        pane.getChildren().addAll(img,Mainframe());
        pane.getStylesheets().add(this.getClass().getResource("styles/Signin.css").toExternalForm());
        return pane;
    }
    public Node CenterBox()
    {
        Node text1 = CreateTextBox(model.getAccountNoProperty(), "User Name","login-text-field");
        Node text2 = CreateTextBox(model.getNameProperty(), "Password","login-text-field");
        VBox temp =  new VBox(10,text1,text2);
        temp.setAlignment(Pos.CENTER);
        return temp;
    }
    public Node SignInBox()
    {
        Node Login = CreateButton("Login", "login-button", event -> SignInHandler.run());
        Node Sign_UpLabel = CreateLabel("Don't Have Account yet?", "signup-label");
        Node Sign_upButton = CreateButton("Sign Up", "signup-button", event -> SignUpHandler.run());
        HBox Sign_upBox = new HBox(20,Sign_UpLabel,Sign_upButton);
        VBox sign_in = new VBox(20,CreateLabel("Login","heading-label"),CenterBox(),Sign_upBox,Login);
        sign_in.getStyleClass().add("login-container");
        sign_in.setMaxHeight(300);
        sign_in.setMaxWidth(500);
        return sign_in;
    }
    public Node Mainframe()
    {
        AnchorPane MainPane = new AnchorPane();
        Node Heading = CreateLabel("Kaiser Payment technologies","page-title");
        Node sign_in = SignInBox();
        AnchorPane.setTopAnchor(Heading,30.0);
        AnchorPane.setLeftAnchor(Heading, 210.0);
        AnchorPane.setLeftAnchor(sign_in, 50.0);
        AnchorPane.setTopAnchor(sign_in, 150.0);
        MainPane.getChildren().addAll(Heading,sign_in);
        MainPane.setMinHeight(600);
        MainPane.setMinWidth(800);
        return MainPane;
    }
}
