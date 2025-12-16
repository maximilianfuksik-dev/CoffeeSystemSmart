package de.gfn.coffeesystemsmart.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EmployeeAccountWindowController {

    @FXML
    private TextField personnelNumber;

    @FXML
    private Label creditAmount;

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Methode wenn man Accept drückt, dass die Kredits vom Konto abgezogen werden, oder eine Meldung erscheint
    // dass nicht genug Kredits auf dem Mitarbeiter-Konto verfügbar sind.

    // Methode für "Mitarbeiter-Chipkarte" Ausgabe="Bitte Chipkarte vorhalten", wird durch Button erreicht.
}
