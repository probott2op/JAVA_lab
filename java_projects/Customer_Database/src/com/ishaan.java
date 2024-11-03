package com;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Builder;

import static com.BoilerPlateUI.CreateButton;

public class ishaan implements Builder<Region> 
{
    CustomerDatabaseBroker broker;
    int ID;
    Runnable back;
    ishaan(int ID, CustomerDatabaseBroker broker, Runnable back)
    {
        this.broker = broker;
        this.ID = ID;
        this.back = back;
    }
    private Background getBackground(String imagePath) {
        Image backgroundImage = new Image(this.getClass().getResourceAsStream(imagePath));
        BackgroundImage background = new BackgroundImage(
        backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(background);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(new Font("Arial", 14));
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-pref-width: 200; -fx-pref-height: 40;");
        return button;
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 16));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-background-color: black; -fx-padding: 5px; -fx-alignment: center;");
        return label;
    }

    @Override
    public Region build() 
    {
        Button addPaymentMethodButton = createStyledButton("Add Payment Methods");
        addPaymentMethodButton.setOnAction(event -> showPaymentOptionsWindow());

        /*VBox mainLayout = new VBox(20, addPaymentMethodButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setBackground(getBackground("Images/Ishaan/payments.png"));*/
        Node back_button = CreateButton("Back", "back-button", event -> back.run());
        AnchorPane.setTopAnchor(back_button,5.0);
        AnchorPane.setLeftAnchor(back_button,5.0);
        AnchorPane.setTopAnchor(addPaymentMethodButton, 250.0);
        AnchorPane.setLeftAnchor(addPaymentMethodButton, 250.0);
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(addPaymentMethodButton, back_button);
        mainLayout.setBackground(getBackground("Images/Ishaan/payments.png"));
        mainLayout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
        return mainLayout;
    }

    private void showPaymentOptionsWindow() {
        Stage optionsStage = new Stage();
        optionsStage.setTitle("Select Payment Method");

        Button upiButton = createStyledButton("UPI");
        upiButton.setOnAction(event -> {
            optionsStage.close();
            showUPIInterface();
        });

        Button cardButton = createStyledButton("Card");
        cardButton.setOnAction(event -> {
            optionsStage.close();
            showCardInterface();
        });

        Button netBankingButton = createStyledButton("Net Banking");
        netBankingButton.setOnAction(event -> {
            optionsStage.close();
            showNetBankingInterface();
        });

        VBox optionsLayout = new VBox(20, upiButton, cardButton, netBankingButton);
        optionsLayout.setAlignment(Pos.TOP_CENTER);
        optionsLayout.setPadding(new Insets(30));
        optionsLayout.setBackground(getBackground("Images/Ishaan/payments.png"));
        Scene optionsScene = new Scene(optionsLayout, 400, 300);

        optionsStage.setScene(optionsScene);
        optionsStage.show();
    }

    private void showUPIInterface() {
        Stage upiStage = new Stage();
        upiStage.setTitle("UPI Payment");

        Label upiLabel = createStyledLabel("Enter UPI ID:");
        TextField upiField = new TextField();
        upiField.setPromptText("UPI ID");

        Label mobileLabel = createStyledLabel("Enter Mobile Number:");
        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");

        Button saveButton = createStyledButton("Save UPI Details");
        saveButton.setOnAction(event -> {
            System.out.println("UPI ID: " + upiField.getText());
            System.out.println("Mobile Number: " + mobileField.getText());
             Map<String,String> account = new HashMap<>();
            account.put("UpiId:",upiField.getText());
            account.put("PhoneNo:",mobileField.getText());
            broker.db.addUPI(ID, account);
            upiStage.close();
            showPaymentOptionsWindow();
        });

        VBox upiLayout = new VBox(15, upiLabel, upiField, mobileLabel, mobileField, saveButton);
        upiLayout.setAlignment(Pos.TOP_CENTER);
        upiLayout.setPadding(new Insets(20));
        upiLayout.setBackground(getBackground("Images/Ishaan/upi.png"));
        Scene upiScene = new Scene(upiLayout, 400, 300);

        upiStage.setScene(upiScene);
        upiStage.show();
    }

    private void showCardInterface() {
        Stage cardStage = new Stage();
        cardStage.setTitle("Card Payment");

        Label cardLabel = createStyledLabel("Enter Card Number:");
        TextField cardField = new TextField();
        cardField.setPromptText("Card Number");

        Label expiryDateLabel = createStyledLabel("Enter Expiry Date (MM/YY):");
        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("MM/YY");

        Label cvvLabel = createStyledLabel("Enter CVV:");
        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");

        Button saveButton = createStyledButton("Save Card Details");
        saveButton.setOnAction(event -> {
            System.out.println("Card Number: " + cardField.getText());
            System.out.println("Expiry Date: " + expiryDateField.getText());
            System.out.println("CVV: " + cvvField.getText());
            Map<String,String> account = new HashMap<>();
            account.put("CardNo:",cardField.getText());
            account.put("ExpiryDate:",expiryDateField.getText());
            account.put("CVV:",cvvField.getText());
            broker.db.addCreditcard(ID, account);
            cardStage.close();
            showPaymentOptionsWindow();
        });

        VBox cardLayout = new VBox(15, cardLabel, cardField, expiryDateLabel, expiryDateField, cvvLabel, cvvField, saveButton);
        cardLayout.setAlignment(Pos.TOP_CENTER);
        cardLayout.setPadding(new Insets(20));
        cardLayout.setBackground(getBackground("Images/Ishaan/card.png"));
        Scene cardScene = new Scene(cardLayout, 400, 350);
        cardStage.setScene(cardScene);
        cardStage.show();
    }

    private void showNetBankingInterface() {
        Stage netBankingStage = new Stage();
        netBankingStage.setTitle("Net Banking");

        Label bankLabel = createStyledLabel("Enter Bank Account Number:");
        TextField bankField = new TextField();
        bankField.setPromptText("Account Number");
        bankField.setText(broker.db.getDetail(ID, "AccountNo:"));

        Label ifscLabel = createStyledLabel("Enter IFSC Code:");
        TextField ifscField = new TextField();
        ifscField.setPromptText("IFSC Code");

        Label bankNameLabel = createStyledLabel("Enter Bank Name:");
        TextField bankNameField = new TextField();
        bankNameField.setPromptText("Bank Name");

        Button saveButton = createStyledButton("Save Bank Details");
        saveButton.setOnAction(event -> {
            System.out.println("Bank Account Number: " + bankField.getText());
            System.out.println("IFSC Code: " + ifscField.getText());
            System.out.println("Bank Name: " + bankNameField.getText());
            Map<String,String> account = new HashMap<>();
            account.put("AccountNo:",bankField.getText());
            account.put("IFSC:",ifscField.getText());
            account.put("BankName:",bankNameField.getText());
            broker.db.addNetBanking(ID, account);
            netBankingStage.close();
            showPaymentOptionsWindow();
        });

        VBox netBankingLayout = new VBox(15, bankLabel, bankField, ifscLabel, ifscField, bankNameLabel, bankNameField, saveButton);
        netBankingLayout.setAlignment(Pos.TOP_CENTER);
        netBankingLayout.setPadding(new Insets(20));
        netBankingLayout.setBackground(getBackground("Images/Ishaan/netbanking.png"));
        Scene netBankingScene = new Scene(netBankingLayout, 400, 350);

        netBankingStage.setScene(netBankingScene);
        netBankingStage.show();
    }
}