package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel 
{
    StringProperty AccountNo = new SimpleStringProperty();
    StringProperty Name = new SimpleStringProperty();
    StringProperty UserId = new SimpleStringProperty();
    StringProperty Password = new SimpleStringProperty();
    public String getAccountNo()
    {
        return AccountNo.get();
    }
    public void setAccountNo(String value)
    {
        AccountNo.set(value);
    }
    public StringProperty getAccountNoProperty()    
    {
        return AccountNo;
    }
    public String getName()
    {
        return Name.get();
    }
    public void setName(String value)
    {
        Name.set(value);
    }
    public StringProperty getNameProperty()    
    {
        return Name;
    }
    public StringProperty getUserIdProperty()
    {
        return UserId;
    }
    public String getUserId()
    {
        return UserId.get();
    }
    public StringProperty getPasswordProperty()
    {
        return Password;
    }
    public String getPassword()
    {
        return Password.get();
    }
}
