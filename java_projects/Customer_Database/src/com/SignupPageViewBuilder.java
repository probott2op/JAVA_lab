package com;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import javafx.scene.Node;

import static com.BoilerPlateUI.CreateLabel;
import static com.BoilerPlateUI.CreateTextBox;
import static com.BoilerPlateUI.CreateButton;

public class SignupPageViewBuilder implements Builder<Region>
{
    CustomerModel model;
    Runnable SignUpHandler;
    SignupPageViewBuilder(CustomerModel model,Runnable SignUpHandler)
    {
        this.model = model;
        this.SignUpHandler = SignUpHandler;
    }
    @Override
    public Region build()
    {
        
        Node Heading = CreateLabel("Create New Account", "title");
        Node NameText = CreateTextBox(model.getNameProperty(), "Enter Name", "text-field");
        Node AccountText = CreateTextBox(model.getAccountNoProperty(), "Enter bank Account No", "text-field");
        Node UserIdText = CreateTextBox(model.getUserIdProperty(), "Enter User Id", "text-field");
        Node PasswordText = CreateTextBox(model.getPasswordProperty(), "Enter Password", "text-field");
        Node SignUpButton = CreateButton("Register", "button", event -> SignUpHandler.run());
        VBox main = new VBox(15,Heading,NameText,AccountText,UserIdText,PasswordText,SignUpButton);
        main.getStylesheets().add(this.getClass().getResource("styles/Signup.css").toExternalForm());
        return main;
    }
}
