package com;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;
public class CustomerController 
{
    CustomerModel model;
    Builder<Region> SignInview;
    Builder<Region> SignUpview;
    CustomerInteractor interactor;
    CustomerController()
    {
        model = new CustomerModel();
        interactor = new CustomerInteractor(model);
        SignInview = new CustomerViewBuilder(model, interactor::saveCustomer,this::SignUpWindow);
        SignUpview = new SignupPageViewBuilder(model,interactor::saveCustomer);
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
}
