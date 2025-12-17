package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import de.gfn.coffeesystemsmart.config.LanguageChange;
import de.gfn.coffeesystemsmart.MainApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository.findCoffeeById;
import static javafx.application.Platform.exit;


public class MainWindowController {

    @FXML
    public Label timeDate;

    @FXML
    public void btnExit() { // Abbrechen-Button Implementierung
        exit();
    }

    @FXML
    public void btnSettings() throws IOException { // Bei Klick wird das neue Fenster "Settings" geöffnet
        FXMLLoader settingsLoader = new FXMLLoader(MainApplication.class.getResource("settings-window.fxml"));
        settingsLoader.setResources(LanguageChange.getBundle());

        Scene settingsScene = new Scene(settingsLoader.load());

        //Main-Stage etablieren:
        Stage mainstage = (Stage) timeDate.getScene().getWindow();

        // Controller erstellen an den die "Mainstage" (-> siehe SettingsWindowController) übergeben wird.
        SettingsWindowController controller = settingsLoader.getController();
        controller.setMainStage(mainstage);

        Stage settingsStage = new Stage();
        controller.setSettingsStage(settingsStage);

        settingsStage.initOwner(mainstage);
        settingsStage.setTitle(LanguageChange.getBundle().getString("label.settings"));
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }
    @FXML
    public void btnCoffeeChoice(ActionEvent event) throws IOException {// Bei Klick wird das neue Fenster "Settings" geöffnet

        Button btn = (Button) event.getSource();
        int coffeeId = Integer.parseInt(btn.getUserData().toString());

        CoffeeEntity coffee;
        try{
            coffee = findCoffeeById(coffeeId);
            if (coffee == null) return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        FXMLLoader paymentLoader = new FXMLLoader(MainApplication.class.getResource("payment-window.fxml"));
        paymentLoader.setResources(LanguageChange.getBundle());

        Scene payment = new Scene(paymentLoader.load());

        PaymentWindowController controller = paymentLoader.getController();
        controller.setCoffee(coffee);

        Stage paymentStage = new Stage();
        paymentStage.setTitle(LanguageChange.getBundle().getString("label.payment"));
        paymentStage.setScene(payment);
        paymentStage.show();
    }

    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        timeDate.setText(LocalDateTime.now().format(formatter));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), e -> {
            timeDate.setText(LocalDateTime.now().format(formatter));  // Aktualisierung durch KeyFrame
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


// Methode LoadCoffeePrices

}

