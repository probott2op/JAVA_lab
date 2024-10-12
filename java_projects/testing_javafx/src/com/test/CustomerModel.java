package com.test;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel 
{
    private final StringProperty AccountNo = new SimpleStringProperty();
    private final StringProperty Name = new SimpleStringProperty();
    public void setAccountNo(String Account_no)
    {
        this.AccountNo.set(Account_no);
    }
    public String getAccountNo()
    {
        return this.AccountNo.get();
    }
    public StringProperty AccountNoProperty()
    {
        return this.AccountNo;
    }
    public void setName(String Name)
    {
        this.Name.set(Name);
    }
    public String getName()
    {
        return this.Name.get();
    }
    public StringProperty NameProperty()
    {
        return this.Name;
    }
}
