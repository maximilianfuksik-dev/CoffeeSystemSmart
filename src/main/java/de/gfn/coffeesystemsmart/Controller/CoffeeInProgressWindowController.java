package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Locale;


public class CoffeeInProgressWindowController {

    private Coffee coffee;

    @FXML
    private Label coffeeInProg;

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;

    }
}
