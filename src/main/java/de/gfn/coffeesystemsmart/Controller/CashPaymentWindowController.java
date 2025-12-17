package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CashPaymentWindowController {
    @FXML
    private Label pricing; //<-- Label für den Preis
    @FXML
    private Label inputCoin;

    private Coffee coffee;

    private int cents = 0; // <-- aktueller "Münzeinwurf" in Cent

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);


    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        updateLabelInput();
        //updateLabelPricing();
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
        updateLabelInput();
    }



    private void addCoin(int add) {
        cents += add;
        updateLabelInput();
    }

    private void updateLabelInput() {
        inputCoin.setText(currency.format(cents / 100.0));
    }

    @FXML
    private void acceptPay() {
    }

    public void setCents(int cents) {
    }

    public static List<CoffeeEntity> findAllCoffee(int id) throws SQLException {
        return SmartCoffeeRepository.get("SELECT * FROM Coffeetable WHERE id= "+id);
    }

    public void updateLabelPricing(int id){
        pricing.setText(currency.format(coffee.getPrice()));
    }

    // Pricing soll sich den Preis des ausgeählten Kaffee's aus der DB holen.

    // Methode zum Abgleichen von Preis und Input! -> Dann erst "Accept" drückbar.
    // Bei Accept wird dann der Preis abgezogen und die Bestellung fertiggemacht
}


