private void loadPaymentOptionsScene(Stage stage, String paymentMethod) {
VBox boxLayout = new VBox(10);
        boxLayout.setAlignment(Pos.CENTER);
    
        Label headerLabel = new Label("Your " + paymentMethod + " Details");
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
    
   // Display available card details from the database
    private void addDebitCardFields(VBox layout) {
        // Fetch card details from the database
        List<Map<String,String>> cards = broker.db.getCards(ID, "CreditCards:");
        int no_cards = cards.size() ;
        String cardNumber = fetchCardDetail("cardNumber"); // Replace with actual DB call
        String expiryDate = fetchCardDetail("expiryDate"); // Replace with actual DB call
        String cvv = fetchCardDetail("cvv"); // Replace with actual DB call
    
        layout.getChildren().addAll(
            new Label("Card Number: " + cardNumber),
            new Label("Expiry Date (MM/YY): " + expiryDate),
            new Label("CVV: " + cvv)
        );
    }
    
    private void addNEFTFields(VBox layout) {
        // Fetch NEFT details from the database
        String accountNumber = fetchNEFTDetail("accountNumber"); // Replace with actual DB call
        String bankName = fetchNEFTDetail("bankName"); // Replace with actual DB call
        String ifscCode = fetchNEFTDetail("ifscCode"); // Replace with actual DB call
    
        layout.getChildren().addAll(
            new Label("Account Number: " + accountNumber),
            new Label("Bank Name: " + bankName),
            new Label("IFSC Code: " + ifscCode)
        );
    }
    
    private void addNetBankingFields(VBox layout) {
        // Fetch Net Banking details from the database
        String bankName = fetchNetBankingDetail("bankName"); // Replace with actual DB call
        String customerId = fetchNetBankingDetail("customerId"); // Replace with actual DB call
    
        layout.getChildren().addAll(
            new Label("Select Bank: " + bankName),
            new Label("Customer ID: " + customerId),
            new Label("Password: [hidden]") // Consider security best practices for displaying sensitive data
        );
    }
    
    private void addUPIFields(VBox layout) {
        // Fetch UPI details from the database
        String upiId = fetchUPIDetail("upiId"); // Replace with actual DB call
    
        layout.getChildren().addAll(
            new Label("UPI ID: " + upiId),
            new Label("Enter PIN: [hidden]") // Consider security best practices
        );
    }