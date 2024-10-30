package com;
import java.util.Map;
import java.util.HashMap;

public class CustomerDatabase 
{
    public Integer count = 0;
    Map<Integer,Map<String,String>> map = new HashMap<>();
    public void saveCustomer(Map<String,String> CustomerDetail)
    {
        CustomerDetail.put("Id:",(++count).toString());
        map.put(count,CustomerDetail);
    }
    // This get method is usually put in a different object called database access object
    public String getDetail(Integer id, String attribute)
    {
        return map.get(id).get(attribute);
    }
}
