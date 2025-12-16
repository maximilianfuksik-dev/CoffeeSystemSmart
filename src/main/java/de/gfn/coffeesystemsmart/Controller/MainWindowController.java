package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.MainApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
        settingsLoader.setResources(ResourceBundle.getBundle("lang.msg"));

        Scene settings = new Scene(settingsLoader.load());

        Stage settingsStage = new Stage();
        settingsStage.setTitle(settingsLoader.getResources().getString("label.settings"));
        settingsStage.setScene(settings);
        settingsStage.show();
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




}

