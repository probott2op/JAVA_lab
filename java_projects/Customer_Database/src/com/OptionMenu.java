package com;

import static com.BoilerPlateUI.CreateButton;
import static com.BoilerPlateUI.CreateLabel;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;


public class OptionMenu implements Builder<Region>
{
    CustomerDatabaseBroker broker;
    int id;
    Runnable signout;
    Runnable abhijith;
    OptionMenu(CustomerDatabaseBroker broker, int id, Runnable signout, Runnable abhijith)
    {
        this.signout = signout;
        this.broker = broker;
        this.id = id;
        this.abhijith = abhijith;
    }
    @Override
    public Region build()
    {
        Node welcome_label = CreateLabel("Weclome "+broker.db.getDetail(id, "Name:"), "heading");

        // Loading Image and sizing it up
        Image Payment = new Image(this.getClass().getResourceAsStream("Images/Payment.png"));
        ImageView PaymentView = new ImageView(Payment);
        PaymentView.setFitWidth(300);
        PaymentView.setFitHeight(300);
        PaymentView.getStyleClass().add("image-view");
        Image Shopping = new Image(this.getClass().getResourceAsStream("Images/Shopping.png"));
        ImageView ShoppingView = new ImageView(Shopping);
        ShoppingView.setFitWidth(300);
        ShoppingView.setFitHeight(300);
        ShoppingView.getStyleClass().add("image-view");
        // Creating both the Button

        Button Payment_button = new Button("Continue With Payment");
        Payment_button.setGraphic(ShoppingView);
        Payment_button.setContentDisplay(ContentDisplay.TOP);
        Payment_button.getStyleClass().add("button");
        Payment_button.setOnAction(event -> abhijith.run());
        Button Shopping_button = new Button("Add Payment Methods");
        Shopping_button.setGraphic(PaymentView);
        Shopping_button.setContentDisplay(ContentDisplay.TOP);
        Shopping_button.getStyleClass().add("button");
        Node signout_button = CreateButton("Log out", "signout-button", event -> signout.run());

        AnchorPane MainFrame = new AnchorPane();
        MainFrame.setMinHeight(600);
        MainFrame.setMinWidth(800);
        AnchorPane.setTopAnchor(signout_button,0.0);
        AnchorPane.setLeftAnchor(signout_button,0.0);
        AnchorPane.setTopAnchor(welcome_label, 25.0);
        AnchorPane.setLeftAnchor(welcome_label, 225.0);
        AnchorPane.setTopAnchor(Payment_button, 125.0);
        AnchorPane.setLeftAnchor(Payment_button, 50.0);
        AnchorPane.setTopAnchor(Shopping_button, 125.0);
        AnchorPane.setLeftAnchor(Shopping_button, 400.0);
        MainFrame.getChildren().addAll(Payment_button,Shopping_button,welcome_label,signout_button);
        MainFrame.getStylesheets().add(this.getClass().getResource("styles/Options.css").toExternalForm());
        MainFrame.getStyleClass().add("anchor-pane");
        return MainFrame;
    }
}
