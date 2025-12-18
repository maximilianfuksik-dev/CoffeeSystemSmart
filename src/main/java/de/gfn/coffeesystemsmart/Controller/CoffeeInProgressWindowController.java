package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.ResourceBundle;

import static de.gfn.coffeesystemsmart.config.LanguageChange.locale;


public class CoffeeInProgressWindowController {

    @FXML
    private Label coffeeInProg;

    @FXML
    private Label coffeeFacts;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ResourceBundle resource = ResourceBundle.getBundle("lang.msg", locale);

    //Timer für Ladebalken
    private static final double TOTAL_SECONDS = 15.0;

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

    private void startProgress() {
        progressBar.setProgress(0);
        int steps = 50;
        double step = 1.0 /steps;
        double frameMillis = (TOTAL_SECONDS * 1000.0) / steps;

        Timeline t1 = new Timeline();
        for(int s = 1; s<=steps; s++) {
            final double p = s * step;
            t1.getKeyFrames().add(new KeyFrame(Duration.millis(s * frameMillis), e -> progressBar.setProgress(p)));
        }
        t1.setOnFinished(e -> {Stage stage = (Stage) progressBar.getScene().getWindow(); stage.close();});
        t1.play();
    }

    @FXML
    public void btnCancel(ActionEvent event) { // Abbrechen-Button Implementierung
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        generateCoffeeFacts();
        startProgress();
    }
}
