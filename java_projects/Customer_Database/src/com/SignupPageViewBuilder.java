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
        
        Node Heading = CreateLabel("Create New Account", null);
        Node NameText = CreateTextBox(model.getNameProperty(), "Enter Name", null);
        Node AccountText = CreateTextBox(model.getAccountNoProperty(), "Enter bank Account No", null);
        Node UserIdText = CreateTextBox(model.getUserIdProperty(), "Enter User Id", null);
        Node PasswordText = CreateTextBox(model.getPasswordProperty(), "Enter Password", null);
        Node SignUpButton = CreateButton("Register", null, event -> SignUpHandler.run());
        VBox main = new VBox(15,Heading,NameText,AccountText,UserIdText,PasswordText,SignUpButton);
        return main;
    }
}
