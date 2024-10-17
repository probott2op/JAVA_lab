package com;
import javafx.scene.layout.Region;
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
            SignInview = new CustomerViewBuilder(model, interactor::saveCustomer);
            SignUpview = new SignupPageViewBuilder(model,interactor::saveCustomer);
        }
        public Region getSignInView()
        {
            return SignInview.build();
        }
        
}
