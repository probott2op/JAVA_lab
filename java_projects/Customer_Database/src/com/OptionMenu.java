package com;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;
public class OptionMenu implements Builder<Region>
{
    @Override
    public Region build()
    {
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
        Button Shopping_button = new Button("Add Payment Methods");
        Shopping_button.setGraphic(PaymentView);
        Shopping_button.setContentDisplay(ContentDisplay.TOP);
        Shopping_button.getStyleClass().add("button");

        AnchorPane MainFrame = new AnchorPane();
        MainFrame.setMinHeight(600);
        MainFrame.setMinWidth(800);
        AnchorPane.setTopAnchor(Payment_button, 125.0);
        AnchorPane.setLeftAnchor(Payment_button, 50.0);
        AnchorPane.setTopAnchor(Shopping_button, 125.0);
        AnchorPane.setLeftAnchor(Shopping_button, 400.0);
        MainFrame.getChildren().addAll(Payment_button,Shopping_button);
        MainFrame.getStylesheets().add(this.getClass().getResource("styles/Options.css").toExternalForm());
        MainFrame.getStyleClass().add("anchor-pane");
        return MainFrame;
    }
}
