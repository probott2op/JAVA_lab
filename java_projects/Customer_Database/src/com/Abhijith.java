package com;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Builder;
import java.util.List;
import java.util.Map;

import static com.BoilerPlateUI.CreateButton;

public class  Abhijith implements Builder<Region>
{
    Stage primaryStage;
    int ID;
    CustomerDatabaseBroker broker;
    Runnable back_options;
    Runnable back_continue_payments;
    String selectedPaymentMethod;
    Runnable redir;
    Abhijith(Stage primaryStage, int ID, CustomerDatabaseBroker broker, Runnable back, Runnable back_continue_payments, Runnable redir)
    {
        this.ID = ID;
        this.primaryStage = primaryStage;
        this.broker = broker;
        this.back_options = back;
        this.back_continue_payments = back_continue_payments;
        this.redir = redir;
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
        paymentOptions.getItems().addAll(/* "NEFT",*/ "Debit Card", "Net Banking", "UPI");
        paymentOptions.setPromptText("Select Payment Method");

        Button proceedButton = new Button("Proceed");
        proceedButton.setFont(new Font("Arial", 20));
        proceedButton.setVisible(false);

        continueButton.setOnAction(event -> {
            paymentOptions.setVisible(true);
            proceedButton.setVisible(true);
        });

        Node back_button = CreateButton("Back", "back-button", event -> back_options.run());
        back_button.setLayoutX(5.0);
        back_button.setLayoutY(5.0);

        VBox mainLayout = new VBox(15);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(welcomeLabel, continueButton, paymentOptions, proceedButton, back_button);
        mainLayout.setBackground(new Background(background));
        paymentOptions.setVisible(false);

        proceedButton.setOnAction(event -> {
            selectedPaymentMethod = paymentOptions.getValue();
            if (selectedPaymentMethod != null) {
                loadPaymentOptionsScene(primaryStage, selectedPaymentMethod);
            }
        });

        mainLayout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
        return mainLayout;
    }

    // Loads the UI for each payment option with respective fields
    private void loadPaymentOptionsScene(Stage stage, String paymentMethod) {
        Node back_button = CreateButton("Back", "back-button", event -> back_continue_payments.run());
        VBox boxLayout = new VBox(10);
        boxLayout.setAlignment(Pos.CENTER);
        
        Label headerLabel = new Label("Your " + paymentMethod + " Details");
        headerLabel.setFont(new Font("Arial", 20));
        headerLabel.setTextFill(Color.DARKBLUE);
        
        boxLayout.getChildren().add(headerLabel);
        
        switch (paymentMethod) {
            case "Debit Card":
                displayCardSelection(stage, boxLayout);
                break;
            /*case "NEFT":
                displayNEFTSelection(stage, boxLayout);
                break;*/
            case "Net Banking":
                displayNetBankingSelection(stage, boxLayout);
                break;
            case "UPI":
                displayUPISelection(stage, boxLayout);
                break;
            default:
                boxLayout.getChildren().add(new Label("No details available for this method."));
        }
        boxLayout.getChildren().add(back_button);
        /*Button confirmButton = new Button("Confirm Payment");
        confirmButton.setFont(new Font("Arial", 18));
        confirmButton.setOnAction(e -> System.out.println("Payment processed for " + paymentMethod));
        boxLayout.getChildren().add(confirmButton);*/

        
        /*AnchorPane.setTopAnchor(back_button,5.0);
        AnchorPane.setLeftAnchor(back_button,5.0);
        AnchorPane.setTopAnchor(boxLayout, 300.0); // Adjust this value to center vertically
        AnchorPane.setLeftAnchor(boxLayout, 200.0); // Adjust this value to center horizontally

        // Calculate center position dynamically
        boxLayout.setLayoutX((600 - boxLayout.prefWidth(-1)) / 2); // Center horizontally
        boxLayout.setLayoutY((600 - boxLayout.prefHeight(-1)) / 2); // Center vertically

        AnchorPane main = new AnchorPane();
        main.getChildren().addAll(boxLayout, back_button);*/
        boxLayout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
        Scene paymentScene = new Scene(boxLayout, 600, 600);
        stage.setScene(paymentScene);
    }
    
    // Card selection method (already provided)
    private void displayCardSelection(Stage stage, VBox layout) {
        List<Map<String, String>> cards = broker.db.getPayments(ID, "CreditCards:");
        
        if (cards.isEmpty()) {
            layout.getChildren().add(new Label("No cards available."));
            return;
        }
    
        ListView<String> cardListView = new ListView<>();
        for (int i = 0; i < cards.size(); i++) {
            cardListView.getItems().add("Card " + (i + 1));
        }
    
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            int selectedIndex = cardListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Map<String, String> selectedCard = cards.get(selectedIndex);
                displayCardDetails(layout, selectedCard);
            } else {
                layout.getChildren().add(new Label("Please select a card."));
            }
        });
    
        layout.getChildren().addAll(cardListView, continueButton);
    }
    
    // Display selected card details (already provided)
    private void displayCardDetails(VBox layout, Map<String, String> card) {
        layout.getChildren().clear();
        layout.getChildren().add(new Label("Selected Card Details:"));
        layout.getChildren().add(new Label("Card Number: " + card.get("CardNo:")));
        layout.getChildren().add(new Label("Expiry Date: " + card.get("ExpiryDate:")));
        layout.getChildren().add(new Label("CVV: " + card.get("CVV:")));
    
        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setOnAction(e -> {
            System.out.println("Payment processed for card: " + card.get("CardNumber:"));
            redir.run();
            // Handle payment processing here
        });
        layout.getChildren().add(confirmButton);
        Node back_button = CreateButton("Back", "back-button", event -> loadPaymentOptionsScene(primaryStage, selectedPaymentMethod));
        layout.getChildren().add(back_button);
        layout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
    }
    
    // NEFT selection method
    /*private void displayNEFTSelection(Stage stage, VBox layout) {
        List<Map<String, String>> neftDetails = broker.db.getPayments(ID, "NEFT:");
        
        if (neftDetails.isEmpty()) {
            layout.getChildren().add(new Label("No NEFT accounts available."));
            return;
        }
    
        ListView<String> neftListView = new ListView<>();
        for (int i = 0; i < neftDetails.size(); i++) {
            neftListView.getItems().add("NEFT Account " + (i + 1));
        }
    
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            int selectedIndex = neftListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Map<String, String> selectedNEFT = neftDetails.get(selectedIndex);
                displayNEFTDetails(layout, selectedNEFT);
            } else {
                layout.getChildren().add(new Label("Please select an NEFT account."));
            }
        });
    
        layout.getChildren().addAll(neftListView, continueButton);
    }
    
    // Display selected NEFT account details
    private void displayNEFTDetails(VBox layout, Map<String, String> neft) {
        layout.getChildren().clear();
        layout.getChildren().add(new Label("Selected NEFT Account Details:"));
        layout.getChildren().add(new Label("Account Number: " + neft.get("accountNumber")));
        layout.getChildren().add(new Label("Bank Name: " + neft.get("bankName")));
        layout.getChildren().add(new Label("IFSC Code: " + neft.get("ifscCode")));
    
        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setOnAction(e -> {
            System.out.println("Payment processed for account: " + neft.get("accountNumber"));
        });
        layout.getChildren().add(confirmButton);
    }
    */
    // Net Banking selection method
    private void displayNetBankingSelection(Stage stage, VBox layout) {
        List<Map<String, String>> netBankingDetails = broker.db.getPayments(ID, "NetBanking:");
    
        if (netBankingDetails.isEmpty()) {
            layout.getChildren().add(new Label("No Net Banking accounts available."));
            return;
        }
    
        ListView<String> netBankingListView = new ListView<>();
        for (int i = 0; i < netBankingDetails.size(); i++) {
            netBankingListView.getItems().add("Net Banking Account " + (i + 1));
        }
    
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            int selectedIndex = netBankingListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Map<String, String> selectedNetBanking = netBankingDetails.get(selectedIndex);
                displayNetBankingDetails(layout, selectedNetBanking);
            } else {
                layout.getChildren().add(new Label("Please select a Net Banking account."));
            }
        });
    
        layout.getChildren().addAll(netBankingListView, continueButton);
    }
    
    // Display selected Net Banking account details
    private void displayNetBankingDetails(VBox layout, Map<String, String> netBanking) {
        layout.getChildren().clear();
        layout.getChildren().add(new Label("Selected Net Banking Account Details:"));
        layout.getChildren().add(new Label("Account Number: " + netBanking.get("AccountNo:")));
        layout.getChildren().add(new Label("Bank Name: " + netBanking.get("BankName:")));
        layout.getChildren().add(new Label("IFSC Code: " + netBanking.get("IFSC:"))); // Consider security best practices
    
        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setOnAction(e -> {
            System.out.println("Payment processed for Net Banking account: " + netBanking.get("AccountNo:"));
            redir.run();
        });
        layout.getChildren().add(confirmButton);

        Node back_button = CreateButton("Back", "back-button", event -> loadPaymentOptionsScene(primaryStage, selectedPaymentMethod));
        layout.getChildren().add(back_button);
        layout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
    }
    
    // UPI selection method
    private void displayUPISelection(Stage stage, VBox layout) {
        List<Map<String, String>> upiDetails = broker.db.getPayments(ID, "UPI:");
    
        if (upiDetails.isEmpty()) {
            layout.getChildren().add(new Label("No UPI accounts available."));
            return;
        }
    
        ListView<String> upiListView = new ListView<>();
        for (int i = 0; i < upiDetails.size(); i++) {
            upiListView.getItems().add("UPI Account " + (i + 1));
        }
    
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            int selectedIndex = upiListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Map<String, String> selectedUPI = upiDetails.get(selectedIndex);
                displayUPIDetails(layout, selectedUPI);
            } else {
                layout.getChildren().add(new Label("Please select a UPI account."));
            }
        });
    
        layout.getChildren().addAll(upiListView, continueButton);
    }
    
    // Display selected UPI account details
    private void displayUPIDetails(VBox layout, Map<String, String> upi) {
        layout.getChildren().clear();
        layout.getChildren().add(new Label("Selected UPI Account Details:"));
        layout.getChildren().add(new Label("UPI ID: " + upi.get("UpiId:")));
        layout.getChildren().add(new Label("Mobile Number: " + upi.get("PhoneNo:"))); // Consider security best practices
    
        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setOnAction(e -> {
            System.out.println("Payment processed for UPI ID: " + upi.get("UpiId:"));
            redir.run();
        });
        layout.getChildren().add(confirmButton);

        Node back_button = CreateButton("Back", "back-button", event -> loadPaymentOptionsScene(primaryStage, selectedPaymentMethod));
        layout.getChildren().add(back_button);
        layout.getStylesheets().add(this.getClass().getResource("styles/Ishaan/ishaan.css").toExternalForm());
    }    
}
// Updated DatabaseManager class (can later be connected to a real database)
/*class DatabaseManager {
    public static String[] getPaymentInfo(String paymentMethod) {
        switch (paymentMethod) {
            case "NEFT": return new String[]{"Account Number", "Bank Name", "IFSC Code"};
            case "Debit Card": return new String[]{"Card Number", "Expiry Date", "CVV"};
            case "Net Banking": return new String[]{"Bank Name", "Customer ID", "Password"};
            case "UPI": return new String[]{"UPI ID", "PIN"};
            default: return new String[]{};
        }
    }
}*/



