package com;

import static com.BoilerPlateUI.CreateLabel;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class Redirecting implements Builder<Region>
{
    public Region build()
    {
        VBox box = new VBox(CreateLabel("Thank you!", "label"),CreateLabel("Redirecting You to bank....", "label"));
        box.getStylesheets().add(this.getClass().getResource("styles/Redirecting.css").toExternalForm());
        box.getStyleClass().add("vbox");
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
