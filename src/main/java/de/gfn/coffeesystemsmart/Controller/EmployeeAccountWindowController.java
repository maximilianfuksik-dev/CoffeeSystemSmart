package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.MainApplication;
import de.gfn.coffeesystemsmart.Repository.EmpRepository;
import de.gfn.coffeesystemsmart.config.LanguageChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;


public class EmployeeAccountWindowController {

    private Coffee coffee;
    private boolean employee;
    private int discountPercent;
    private int cents;

    private Integer currentEmpId = null;
    private Integer currentCredits = null;

    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    @FXML
    private Button acceptBtn;

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

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @FXML
    private void initialize() {
        acceptBtn.setDisable(true);
        creditAmount.setText("");
    }

    @FXML
    public void btnCheckPersonnel(ActionEvent event) {
        String text = personnelNumber.getText().trim();

        int empId;
        try{
            empId = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            creditAmount.setText(LanguageChange.getBundle().getString("label.AccNmbrMismatch"));
            acceptBtn.setDisable(true);
            return;
        }

        try {
            Integer credits = EmpRepository.getEmployeeCredits(empId);
            if (credits == null) {
                creditAmount.setText(LanguageChange.getBundle().getString("label.AccNotFound"));
                acceptBtn.setDisable(true);
                currentEmpId = null;
                currentCredits = null;
                return;
            }

            currentEmpId = empId;
            currentCredits = credits;

            creditAmount.setText("Credits: " + currency.format(credits / 100));
            acceptBtn.setDisable(coffee == null);

        } catch (SQLException exc) {
            exc.printStackTrace();
            creditAmount.setText(LanguageChange.getBundle().getString("label.DbError"));
            acceptBtn.setDisable(true);
        }
    }

    @FXML
    public void btnAccept(ActionEvent event) {
        if ( coffee == null || currentEmpId == 0) return;

        int priceCents = (int) Math.round(coffee.getPrice() * 100.0);
        priceCents = (int) Math.round(priceCents *(1.0 - discountPercent / 100.0));

        try{
            EmpRepository.creditCheckOrder(currentEmpId,priceCents, coffee.getName(), false);

            Integer newCredits = EmpRepository.getEmployeeCredits(currentEmpId);
            currentCredits = newCredits;
            creditAmount.setText("Credits: " + currency.format(newCredits / 100.0));

            System.out.println(LanguageChange.getBundle().getString("output.dbSavedOrder"));
            FXMLLoader finishLoader = new FXMLLoader(MainApplication.class.getResource("coffee-in-progress-window.fxml"));
            finishLoader.setResources(LanguageChange.getBundle());

            Scene finish = new Scene(finishLoader.load());

            CoffeeInProgressWindowController controller = finishLoader.getController();
            controller.setCoffee(coffee);

            Stage CoffeeInProgress = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Altes Fenster wird wiederverwertet
            CoffeeInProgress.setTitle(LanguageChange.getBundle().getString("label.inProgress"));
            CoffeeInProgress.setScene(finish);
            CoffeeInProgress.show();
        } catch (SQLException | IOException ex) {
            if(LanguageChange.getBundle().getString("label.AccNmbrMismatch").equals(ex.getMessage())) {
                creditAmount.setText(LanguageChange.getBundle().getString("label.notEnoughCredits"));
            } else {
                ex.printStackTrace();
                creditAmount.setText(LanguageChange.getBundle().getString("label.DbError"));
            }
        }
    }


}





    // Methode wenn man Accept drückt, dass die Kredits vom Konto abgezogen werden, oder eine Meldung erscheint
    // dass nicht genug Kredits auf dem Mitarbeiter-Konto verfügbar sind.

    // Methode für "Mitarbeiter-Chipkarte" Ausgabe="Bitte Chipkarte vorhalten", wird durch Button erreicht.

