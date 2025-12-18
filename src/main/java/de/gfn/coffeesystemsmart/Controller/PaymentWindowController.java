package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.config.LanguageChange;
import de.gfn.coffeesystemsmart.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.scene.control.Label;

public class PaymentWindowController {

    @FXML
    private CheckBox isEmployee;

    @FXML
    private Button creditBtn;

    @FXML
    private Label orderPrice;

    private Coffee coffee;

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    private static final int EMPLOYEE_DISC = 10; // In prozent (Anpassbar)



    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        orderPrice.setText(currency.format(coffee.getPrice()));
    }

    private int priceWithDisc() {
        if(coffee == null) return 0;

        int baseCents = (int) Math.round(coffee.getPrice() * 100.0);

        if(isEmployee.isSelected()) {
            return(int) Math.round(baseCents * (1.0 - EMPLOYEE_DISC / 100.0));
        }
        return baseCents;
    }

    private int priceWithDisc(double priceEuro, int discountPercent) {
        int baseCents = (int) Math.round(priceEuro * 100.0);
        double factor = 1.0 - (discountPercent / 100.0);
        return(int) Math.round(baseCents * (1.0 - EMPLOYEE_DISC / 100.0));
    }

    @FXML
    public void initialize() {
        isEmployee.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            creditBtn.setDisable(!isNowSelected);
        });
        creditBtn.setDisable(!isEmployee.isSelected());
    }

    @FXML
    public void btnCashPay(ActionEvent event) throws IOException { // Bei Klick wird das neue Fenster "Cash-Payment" geöffnet
        FXMLLoader cashPayLoader = new FXMLLoader(MainApplication.class.getResource("cash-payment-window.fxml"));
        cashPayLoader.setResources(LanguageChange.getBundle());

        Scene cashPay = new Scene(cashPayLoader.load());

        CashPaymentWindowController controller = cashPayLoader.getController();
        controller.setCoffee(coffee);
        controller.setEmployee(isEmployee.isSelected());
        controller.setDiscountPercent(isEmployee.isSelected() ? EMPLOYEE_DISC : 0);

        Stage cashPayStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Altes Fenster wird wiederverwertet
        cashPayStage.setTitle(LanguageChange.getBundle().getString("label.cashpay"));
        cashPayStage.setScene(cashPay);
        cashPayStage.show();
    }
    @FXML
    public void btnCreditPay(ActionEvent event) throws IOException { // Bei Klick wird das neue Fenster "Employee-Acc" geöffnet
        FXMLLoader creditPayLoader = new FXMLLoader(MainApplication.class.getResource("employee-account-window.fxml"));
        creditPayLoader.setResources(LanguageChange.getBundle());

        Scene creditPay = new Scene(creditPayLoader.load());

        EmployeeAccountWindowController controller = creditPayLoader.getController();
        controller.setCoffee(coffee);
        controller.setEmployee(isEmployee.isSelected());
        controller.setDiscountPercent(isEmployee.isSelected() ? EMPLOYEE_DISC : 0);

        Stage creditPayStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Altes Fenster wird wiederverwertet
        creditPayStage.setTitle(LanguageChange.getBundle().getString("label.cardCredit"));
        creditPayStage.setScene(creditPay);
        creditPayStage.show();
    }
}
