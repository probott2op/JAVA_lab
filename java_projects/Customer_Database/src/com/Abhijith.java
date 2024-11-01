package com;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Builder;
import javafx.scene.layout.Region;

public class  Abhijith implements Builder<Region>
{
    Stage primaryStage;
    Abhijith(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }
    @Override
    public Region build() 
    {
        // Background image setup
        Image backgroundImage = new Image(this.getClass().getResourceAsStream("Images/abhijith.png"));
        BackgroundImage background = new BackgroundImage(
                backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // Main UI elements
        Label welcomeLabel = new Label("Welcome to the Payment Gateway");
        welcomeLabel.setFont(new Font("Cambria", 25));
        welcomeLabel.setTextFill(Color.BLACK);

        Button continueButton = new Button("Continue with Payment");
        continueButton.setFont(new Font("Arial", 20));

        ComboBox<String> paymentOptions = new ComboBox<>();
        paymentOptions.getItems().addAll("NEFT", "Debit Card", "Net Banking", "UPI");
        paymentOptions.setPromptText("Select Payment Method");

        Button proceedButton = new Button("Proceed");
        proceedButton.setFont(new Font("Arial", 20));
        proceedButton.setVisible(false);

        continueButton.setOnAction(event -> {
            paymentOptions.setVisible(true);
            proceedButton.setVisible(true);
        });

        VBox mainLayout = new VBox(15);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(welcomeLabel, continueButton, paymentOptions, proceedButton);
        mainLayout.setBackground(new Background(background));
        paymentOptions.setVisible(false);

        proceedButton.setOnAction(event -> {
            String selectedPaymentMethod = paymentOptions.getValue();
            if (selectedPaymentMethod != null) {
                loadPaymentOptionsScene(primaryStage, selectedPaymentMethod);
            }
        });
        return mainLayout;
    }

    // Loads the UI for each payment option with respective fields
    private void loadPaymentOptionsScene(Stage stage, String paymentMethod) {
        VBox boxLayout = new VBox(10);
        boxLayout.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Enter " + paymentMethod + " Details");
        headerLabel.setFont(new Font("Arial", 20));
        headerLabel.setTextFill(Color.DARKBLUE);

        boxLayout.getChildren().add(headerLabel);

        switch (paymentMethod) {
            case "Debit Card":
                addDebitCardFields(boxLayout);
                break;
            case "NEFT":
                addNEFTFields(boxLayout);
                break;
            case "Net Banking":
                addNetBankingFields(boxLayout);
                break;
            case "UPI":
                addUPIFields(boxLayout);
                break;
            default:
                boxLayout.getChildren().add(new Label("No details available for this method."));
        }

        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setFont(new Font("Arial", 18));
        confirmButton.setOnAction(e -> System.out.println("Payment processed for " + paymentMethod));
        boxLayout.getChildren().add(confirmButton);

        Scene paymentScene = new Scene(boxLayout, 400, 400);
        stage.setScene(paymentScene);
    }

    // Field setup methods for each payment option
    private void addDebitCardFields(VBox layout) {
        layout.getChildren().addAll(
            new Label("Card Number:"), new TextField(),
            new Label("Expiry Date (MM/YY):"), new TextField(),
            new Label("CVV:"), new PasswordField()
        );
    }

    private void addNEFTFields(VBox layout) {
        layout.getChildren().addAll(
            new Label("Account Number:"), new TextField(),
            new Label("Bank Name:"), new TextField(),
            new Label("IFSC Code:"), new TextField()
        );
    }

    private void addNetBankingFields(VBox layout) {
        layout.getChildren().addAll(
            new Label("Select Bank:"), new ComboBox<String>(),
            new Label("Customer ID:"), new TextField(),
            new Label("Password:"), new PasswordField()
        );
    }

    private void addUPIFields(VBox layout) {
        layout.getChildren().addAll(
            new Label("UPI ID:"), new TextField(),
            new Label("Enter PIN:"), new PasswordField()
        );
    }
}

// Updated DatabaseManager class (can later be connected to a real database)
class DatabaseManager {
    public static String[] getPaymentInfo(String paymentMethod) {
        switch (paymentMethod) {
            case "NEFT": return new String[]{"Account Number", "Bank Name", "IFSC Code"};
            case "Debit Card": return new String[]{"Card Number", "Expiry Date", "CVV"};
            case "Net Banking": return new String[]{"Bank Name", "Customer ID", "Password"};
            case "UPI": return new String[]{"UPI ID", "PIN"};
            default: return new String[]{};
        }
    }
}