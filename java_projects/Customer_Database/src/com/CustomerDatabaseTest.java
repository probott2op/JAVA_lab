package com;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CustomerDatabaseTest 
{
    @Test
    public void saveCustomerTestId1()
    {
        CustomerDatabase db = new CustomerDatabase();
        db.saveCustomer(new HashMap<String,String>());
        assertEquals("1",db.getDetail(1, "Id:"));
    }
    @Test
    public void saveCustomerTestId2()
    {
        CustomerDatabase db = new CustomerDatabase();
        db.saveCustomer(new HashMap<String,String>());
        db.saveCustomer(new HashMap<String,String>());
        assertEquals("2",db.getDetail(2, "Id:"));
    }
    @Test
    public void saveCustomerName()
    {
        CustomerDatabase db = new CustomerDatabase();
        Map<String,String> cust1 = new HashMap<>();
        cust1.put("Name:","Harsh");
        cust1.put("Email:","Harsh@gmail.com");
        Map<String,String> cust2 = new HashMap<>();
        cust2.put("Name:","Manish");
        cust2.put("Email:","Manish@gmail.com");
        db.saveCustomer(cust1);
        db.saveCustomer(cust2);
        assertEquals("Manish",db.getDetail(2, "Name:"));
        assertEquals("Harsh",db.getDetail(1, "Name:"));
        assertEquals("Manish@gmail.com",db.getDetail(2, "Email:"));
    }              
}
