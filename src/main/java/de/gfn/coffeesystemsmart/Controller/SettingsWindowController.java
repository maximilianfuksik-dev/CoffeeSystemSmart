package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.LanguageChange;
import de.gfn.coffeesystemsmart.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class SettingsWindowController {

    private Stage mainStage;
    private Stage settingsStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }

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
