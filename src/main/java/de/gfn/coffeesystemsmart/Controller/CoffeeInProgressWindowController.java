package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Random;
import java.util.ResourceBundle;

import static de.gfn.coffeesystemsmart.config.LanguageChange.locale;


public class CoffeeInProgressWindowController {

    @FXML
    private Label coffeeInProg;

    @FXML
    private Label coffeeFacts;

    @FXML
    private ResourceBundle resource = ResourceBundle.getBundle("lang.msg", locale);

    private Coffee coffee;

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public void generateCoffeeFacts() {
        Random rand = new Random();
        String lang = (resource != null) ? resource.getLocale().getLanguage() : "de";
        switch(lang){
            case "en" -> coffeeFacts.setText(SmartCoffeeRepository.coffeeTipsENG[rand.nextInt(20)]);
            default -> coffeeFacts.setText(SmartCoffeeRepository.coffeeTipsDE[rand.nextInt(20)]);
        }
    }

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        generateCoffeeFacts();
    }
}
