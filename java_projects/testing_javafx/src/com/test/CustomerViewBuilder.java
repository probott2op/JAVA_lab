package com.test;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.util.Builder;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class CustomerViewBuilder implements Builder<Region> {

    private final CustomerModel model;
    private final Runnable saveHandler;
    public CustomerViewBuilder(CustomerModel model,Runnable savehandler) 
    {
        this.model = model;
        this.saveHandler = savehandler;
    }

    @Override
    public Region build() 
    {
        BorderPane scene1 = new BorderPane();
        System.out.println(this.getClass().getResource("styles/vustomer.css"));
        scene1.getStylesheets().add(this.getClass().getResource("styles/customer.css").toExternalForm());
        scene1.setTop(HeadingLabel("Customer Information"));
        scene1.setCenter(Createcentre());
        scene1.setBottom(CreateButton());
        return scene1;
    }
    public Node CreateButton()
    {
        Button temp = new Button("Save");
        temp.setOnAction(event -> saveHandler.run());
        HBox results = new HBox(10,temp);
        results.setAlignment(Pos.CENTER_RIGHT);
        return results;
    }
    public Node Createcentre()
    {
        VBox temp = new VBox(6,CreateAccountNoBox(),CreateNameBox());
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(20));
        return temp;
    }
    public Node CreateNameBox()
    {
        HBox temp = new HBox(10,PromptLabel("Name: "),BindTextBox(model.NameProperty()));
        temp.setAlignment(Pos.CENTER);
        return temp;
    }
    public Node CreateAccountNoBox()
    {
        HBox temp = new HBox(10,PromptLabel("Account No: "),BindTextBox(model.AccountNoProperty()));
        temp.setAlignment(Pos.CENTER);
        return temp;
    }
    public Node BindTextBox(StringProperty bind)
    {
        TextField temp = new TextField("");
        temp.textProperty().bindBidirectional(bind);
        return temp;
    }
    public Node PromptLabel(String text)
    {
        return StyledLabel(text,"prompt-label");
    }
    public Node HeadingLabel(String heading)
    {
        return StyledLabel(heading,"heading-label");
    }
    public Node StyledLabel(String text,String StyleClass)
    {
        Label temp = new Label(text);
        temp.getStyleClass().add(StyleClass);
        return temp;
    }
}
