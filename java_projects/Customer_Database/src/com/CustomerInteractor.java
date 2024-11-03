package com;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerInteractor 
{
    CustomerModel model;
    CustomerDatabaseBroker broker;
    CustomerInteractor(CustomerModel model, CustomerDatabaseBroker broker)
    {
        this.model = model;
        this.broker = broker;
    }
    public void saveCustomer()
    {
        System.out.println("Saving... "+"User Id: "+model.getUserId());
        Map<String,Object> cust = new HashMap<>();
        cust.put("AccountNo:",model.getAccountNo());
        cust.put("Name:",model.getName());
        cust.put("UserId:",model.getUserId());
        cust.put("Password:",model.getPassword());
        cust.put("CreditCards:", new ArrayList<Map<String, String>>());
        cust.put("UPI:", new ArrayList<Map<String, String>>());
        cust.put("NetBanking:", new ArrayList<Map<String, String>>());
        broker.addDetail(cust);
    }
}

/*
 * ArrayList<Map<String, String>> payment = new ArrayList<>();
        Map<String,String> account = new HashMap<>();
        account.put("AccountNo:",model.getAccountNo());
        payment.add(account);
 */