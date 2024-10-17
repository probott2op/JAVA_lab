package com;

import javafx.event.EventHandler;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BoilerPlateUI
{
    // Creating a heading label
    public static Node CreateLabel(String info,String CssClass)
    {
        Label information = new Label(info);
        information.getStyleClass().add(CssClass);
        return information;
    }
    // creating a Textbox with some placeholder text
    public static Node CreateTextBox(StringProperty bind,String Prompt,String CssClass)
    {
        TextField Text = new TextField("");
        Text.setPromptText(Prompt);
        Text.textProperty().bindBidirectional(bind);
        Text.getStyleClass().add(CssClass);
        Text.setMaxWidth(150);
        return Text;
    }
    // create a button
    public static Node CreateButton(String Text,String CssClass,EventHandler<ActionEvent> event)
    {
        Button btn = new Button(Text);
        btn.getStyleClass().add(CssClass);
        btn.setOnAction(event);
        return btn;
    }
}