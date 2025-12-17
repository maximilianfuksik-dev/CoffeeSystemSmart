package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import de.gfn.coffeesystemsmart.MainApplication;
import de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository;
import de.gfn.coffeesystemsmart.config.LanguageChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CashPaymentWindowController {
    @FXML
    public Label pricing; //<-- Label für den Preis
    @FXML
    public Label inputCoin;

    @FXML
    private Button acceptBtn;

    private Coffee coffee;
    private boolean employee;
    private int discountPercent = 0;

    private int cents = 0; // <-- aktueller "Münzeinwurf" in Cent

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    private void updateAcceptEnabled() {
        acceptBtn.setDisable(cents < getPriceCents());
    }


    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        pricing.setText(String.format(Locale.GERMANY, "%.2f €", coffee.getPrice()));
        updateLabel();
        updateAcceptEnabled();
    }

    private void updateLabel() {
        inputCoin.setText(String.format(currency.format(cents / 100.0)));
    }

    public void setDiscountPercent(int id) {
        this.discountPercent = discountPercent;
        updateLabelPricing();
        updateAcceptEnabled();
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
        updateLabelPricing();
    }

    private int getPriceCents() {
        if (coffee == null) return 0;
        int base = (int) Math.round(coffee.getPrice() * 100.0);
        return (int) Math.round(base * (1.0 - discountPercent / 100.0));
    }

    private void updateLabelPricing(){
        int cents = getPriceCents();
        pricing.setText(currency.format(cents / 100.0));
    }

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        updateLabelInput();

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
        updateAcceptEnabled();
    }



    private void addCoin(int add) {
        cents += add;
        updateLabelInput();
        updateAcceptEnabled();
    }

    private void updateLabelInput() {
        inputCoin.setText(currency.format(cents / 100.0));
    }

    @FXML
    private void btnAcceptPay() throws IOException {
        if (coffee == null) return;

        int priceCents = (int) Math.round(coffee.getPrice() * 100.0);
        boolean enough = cents >= priceCents;
        if(enough) {
            FXMLLoader finishLoader = new FXMLLoader(MainApplication.class.getResource("coffee-in-progress-window.fxml"));
            finishLoader.setResources(LanguageChange.getBundle());

            Scene finish = new Scene(finishLoader.load());

            CoffeeInProgressWindowController controller = finishLoader.getController();
            controller.setCoffee(coffee);

            Stage finishStage = new Stage();
            finishStage.setTitle(LanguageChange.getBundle().getString("label.inProgress"));
            finishStage.setScene(finish);
            finishStage.show();
        } else{
            FXMLLoader errorLoader = new FXMLLoader(MainApplication.class.getResource("error-view.fxml"));
            errorLoader.setResources(LanguageChange.getBundle());
            Scene error = new Scene(errorLoader.load());
            Stage errorStage = new Stage();
            errorStage.setTitle(LanguageChange.getBundle().getString("label.error"));
            errorStage.setScene(error);
            errorStage.show();
        }
    }

    public void setCents(int cents) {
    }

    public static List<CoffeeEntity> findAllCoffee(int id) throws SQLException {
        return SmartCoffeeRepository.get("SELECT * FROM Coffeetable WHERE id= "+id);
    }



    // Pricing soll sich den Preis des ausgeählten Kaffee's aus der DB holen.

    // Methode zum Abgleichen von Preis und Input! -> Dann erst "Accept" drückbar.
    // Bei Accept wird dann der Preis abgezogen und die Bestellung fertiggemacht
}


