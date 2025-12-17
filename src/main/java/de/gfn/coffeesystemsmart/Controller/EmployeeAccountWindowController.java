package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;


public class EmployeeAccountWindowController {

    private Coffee coffee;
    private boolean employee;
    private int discountPercent;
    private int cents;

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);


    @FXML
    private TextField personnelNumber;

    @FXML
    private Label creditAmount;

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}





    // Methode wenn man Accept drückt, dass die Kredits vom Konto abgezogen werden, oder eine Meldung erscheint
    // dass nicht genug Kredits auf dem Mitarbeiter-Konto verfügbar sind.

    // Methode für "Mitarbeiter-Chipkarte" Ausgabe="Bitte Chipkarte vorhalten", wird durch Button erreicht.

