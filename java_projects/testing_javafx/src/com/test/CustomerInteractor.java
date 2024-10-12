package com.test;

public class CustomerInteractor {
    private CustomerModel model;

    public CustomerInteractor(CustomerModel model) {
        this.model = model;
    }
    public void saveCustomer()
    {
        System.out.println("Saving account: "+model.getAccountNo()+"\n"+"Name: "+model.getName());
    }
}
