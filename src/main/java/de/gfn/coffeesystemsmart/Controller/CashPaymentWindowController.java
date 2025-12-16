package de.gfn.coffeesystemsmart.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CashPaymentWindowController {
    @FXML
    private Label pricing;

    @FXML
    private Label inputCoin;


    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /*
    Pricing soll sich den Preis des ausgeählten Kaffee's aus der DB holen.

    Methoden für onActionPres="": Methoden sollen dem "Input" Feld ihren Wert beifügen. -> Reset-Knopf?
    btn.twoEur
    btn.oneEur
    btn.fiftyCent
    btn.twentyCent
    btn.tenCent
    btn.fiveCent
    */
    // Methode zum Abgleichen von Preis und Input! -> Dann erst "Accept" drückbar.
    // Bei Accept wird dann der Preis abgezogen und die Bestellung fertiggemacht
}
