package com;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;
public class CustomerController 
{
    Stage PrimaryStage;
    CustomerModel model;
    Builder<Region> SignInview;
    Builder<Region> SignUpview;
    Builder<Region> OptionMenuview;
    CustomerInteractor interactor;
    CustomerController(Stage PrimaryStage)
    {
        this.PrimaryStage = PrimaryStage;
        model = new CustomerModel();
        interactor = new CustomerInteractor(model);
        SignInview = new CustomerViewBuilder(model, this::OptionsMenu,this::SignUpWindow);
        SignUpview = new SignupPageViewBuilder(model,interactor::saveCustomer);
        OptionMenuview = new OptionMenu();
    }
    public Region getSignInView()
    {
        return SignInview.build();
    }
    private void SignUpWindow()
    {
        Stage SignUpStage = new Stage();
        Scene SignUpScene = new Scene(SignUpview.build());
        SignUpStage.setScene(SignUpScene);
        SignUpStage.show();
    }
    private void OptionsMenu()
    {
        Scene MenuScene = new Scene(OptionMenuview.build());
        PrimaryStage.setScene(MenuScene);
    }    
}
