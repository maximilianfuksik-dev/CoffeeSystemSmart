package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.LanguageChange;
import de.gfn.coffeesystemsmart.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

public class PaymentWindowController {

    @FXML
    private CheckBox isEmployee;

    @FXML
    private Label orderPrice;

    @FXML
    public void btnCashPay(ActionEvent event) throws IOException { // Bei Klick wird das neue Fenster "Cash-Payment" geöffnet
        FXMLLoader cashPayLoader = new FXMLLoader(MainApplication.class.getResource("cash-payment-window.fxml"));
        cashPayLoader.setResources(LanguageChange.getBundle());

        Scene cashPay = new Scene(cashPayLoader.load());

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

        Stage creditPayStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Altes Fenster wird wiederverwertet
        creditPayStage.setTitle(LanguageChange.getBundle().getString("label.cardCredit"));
        creditPayStage.setScene(creditPay);
        creditPayStage.show();
    }
}
