package com;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class CustomerDatabase 
{
    public Integer count = 0;
    Map<Integer,Map<String,Object>> map = new HashMap<>();
    public void saveCustomer(Map<String,Object> CustomerDetail)
    {
        CustomerDetail.put("Id:",(++count).toString());
        map.put(count,CustomerDetail);
    }
    // This get method is usually put in a different object called database access object
    public String getDetail(Integer id, String attribute)
    {
        return (String) map.get(id).get(attribute);
    }
    public List<Map<String,String>> getPayments(Integer id, String attribute)
    {
        return (List<Map<String,String>>) map.get(id).get(attribute);
    }
    public void addCreditcard(int id, Map<String,String> cards)
    {
        List<Map<String,String>> temp = (List<Map<String,String>>) map.get(id).get("CreditCards:");
        temp.add(cards);
    }
    public void addUPI(int id, Map<String,String> cards)
    {
        List<Map<String,String>> temp = (List<Map<String,String>>) map.get(id).get("UPI:");
        temp.add(cards);
    }
    public void addNetBanking(int id, Map<String,String> cards)
    {
        List<Map<String,String>> temp = (List<Map<String,String>>) map.get(id).get("NetBanking:");
        temp.add(cards);
    }
}
