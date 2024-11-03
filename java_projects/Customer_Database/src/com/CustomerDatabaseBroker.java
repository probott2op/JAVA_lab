package com;

import java.util.Map;

public class CustomerDatabaseBroker 
{
    CustomerDatabase db;
    CustomerDatabaseBroker()
    {
        db = new CustomerDatabase();
    }
    public void addDetail(Map<String,Object> cust)
    {
        db.saveCustomer(cust);
    }
}
