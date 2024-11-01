package com;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;
public class CustomerController 
{
    Stage PrimaryStage;
    CustomerModel model_signup;
    CustomerModel model_signin;
    Builder<Region> SignInview;
    Builder<Region> SignUpview;
    Builder<Region> OptionMenuview;
    CustomerInteractor interactor;
    CustomerDatabaseBroker broker;
    Abhijith continue_with_payment;
    CustomerController(Stage PrimaryStage)
    {
        this.PrimaryStage = PrimaryStage;
        broker = new CustomerDatabaseBroker();
        model_signup = new CustomerModel();
        model_signin = new CustomerModel();
        interactor = new CustomerInteractor(model_signup,broker);
        SignInview = new CustomerViewBuilder(model_signin, this::OptionsMenu,this::SignUpWindow);
        SignUpview = new SignupPageViewBuilder(model_signup,interactor::saveCustomer);
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
    private void OptionsMenu(Node incorrect)
    {
        int flag = 0;
        for (int i = 1; i <= broker.db.count; i++)
        {
            String temp_user = broker.db.getDetail(i, "UserId:");
            String temp_pass = broker.db.getDetail(i, "Password:");
            if (temp_user.equals(model_signin.getUserId()) && temp_pass.equals(model_signin.getPassword()))
            {
                flag = 1;
                OptionMenuview = new OptionMenu(broker, i, this::logout, this::abhijith);
                Scene MenuScene = new Scene(OptionMenuview.build());
                PrimaryStage.setScene(MenuScene);
                break;
            }
        }
        if (flag != 1)
        {
            incorrect.setVisible(true);
        }
    }
    private void logout()
    {
        Scene logout = new Scene(getSignInView());
        PrimaryStage.setScene(logout);
    }
    private void abhijith()
    {
        continue_with_payment = new Abhijith(PrimaryStage);
        Scene continueScene = new Scene(continue_with_payment.build(),650,650);
        PrimaryStage.setScene(continueScene);
    }
}
