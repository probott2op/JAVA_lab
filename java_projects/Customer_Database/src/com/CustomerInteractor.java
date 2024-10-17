package com;

import java.util.Map;
import java.util.HashMap;

public class CustomerInteractor 
{
    CustomerModel model;
    CustomerDatabaseBroker broker;
    CustomerInteractor(CustomerModel model)
    {
        this.model = model;
        broker = new CustomerDatabaseBroker();
    }
    public void saveCustomer()
    {
        System.out.println("Saving... "+"User Id: "+model.getUserId());
        Map<String,String> cust = new HashMap<>();
        cust.put("AccountNo:",model.getAccountNo());
        cust.put("Name:",model.getName());
        cust.put("UserId:",model.getUserId());
        cust.put("Password:",model.getPassword());
        broker.addDetail(cust);
    }
}

