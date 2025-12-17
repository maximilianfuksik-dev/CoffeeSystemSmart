package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.config.LanguageChange;
import de.gfn.coffeesystemsmart.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

import static javafx.application.Platform.exit;

public class SettingsWindowController {

    private Stage mainStage;
    private Stage settingsStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }

    // Implementierung der Methoden für Reperatur und Auffüllen, von Kaffee und Milch.

    @FXML
    public void btnPrintReport(ActionEvent event) throws IOException {
        FXMLLoader chartLoader = new FXMLLoader(MainApplication.class.getResource("chart-window.fxml"));
        chartLoader.setResources(LanguageChange.getBundle());

        Scene settings = new Scene(chartLoader.load());

        Stage settingsStage = new Stage();
        settingsStage.setTitle(LanguageChange.getBundle().getString("label.printReport"));
        settingsStage.setScene(settings);
        settingsStage.show();
    }

    @FXML
    public void btnChanges(ActionEvent event) throws IOException {
        FXMLLoader chartLoader = new FXMLLoader(MainApplication.class.getResource("db-coffee-window.fxml"));
        chartLoader.setResources(LanguageChange.getBundle());

        Scene settings = new Scene(chartLoader.load());

        Stage settingsStage = new Stage();
        settingsStage.setTitle(LanguageChange.getBundle().getString("label.changes"));
        settingsStage.setScene(settings);
        settingsStage.show();
    }

    @FXML
    public void btnTurnOffMachine(){ exit(); }



    // Ab hier Methoden für Sprachpakete:
    @FXML
    private void switchToGerman() throws IOException {
        switchLanguage(Locale.GERMAN);
    }

    @FXML
    private void switchToEnglish() throws IOException {
        switchLanguage(Locale.ENGLISH);
    }

    @FXML
    private AnchorPane root;

    private void switchLanguage(Locale locale) throws IOException {
        LanguageChange.setLocale(locale);

        FXMLLoader languageLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"), LanguageChange.getBundle());
        Scene mainScene = new Scene(languageLoader.load());

        mainStage.setScene(mainScene);
        mainStage.setTitle(LanguageChange.getBundle().getString("label.welcomeText"));

        if(settingsStage !=null) settingsStage.close();
    }

}
