package de.gfn.coffeesystemsmart.Controller;

import javafx.fxml.FXML;

import java.awt.*;

public class ErrorWindowController {

    @FXML
    public Label errorLabel;

    public void errorMsgCreate(String msg) {
        errorLabel.setText(msg);
    }


}
