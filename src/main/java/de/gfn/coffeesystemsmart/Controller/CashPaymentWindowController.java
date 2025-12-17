package de.gfn.coffeesystemsmart.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class CashPaymentWindowController {
    @FXML
    private Label pricing;

    @FXML
    private Label inputCoin;

    private int cents = 0; // <-- aktueller "Münzeinwurf" in Cent

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);


    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        updateLabel();
    }

    @FXML
    private void twoEur(){
        addCoin(200);
    }
    @FXML
    private void oneEur(){
        addCoin(100);
    }
    @FXML
    private void fiftyCents(){
        addCoin(50);
    }
    @FXML
    private void twentyCents(){
        addCoin(20);
    }
    @FXML
    private void tenCents(){
        addCoin(10);
    }
    @FXML
    private void fiveCents(){
        addCoin(5);
    }

    @FXML
    private void reset() {
        cents = 0;
        updateLabel();
    }

    private void addCoin(int add) {
        cents += add;
        updateLabel();
    }

    private void updateLabel() {
        inputCoin.setText(currency.format(cents / 100.0));
    }

    @FXML
    private void acceptPay() {
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


