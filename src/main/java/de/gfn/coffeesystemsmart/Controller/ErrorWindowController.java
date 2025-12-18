package de.gfn.coffeesystemsmart.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ErrorWindowController {

    @FXML
    public Label errorLabel;

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void errorMsgCreate(String msg) {
        errorLabel.setText(msg);
    }


}
