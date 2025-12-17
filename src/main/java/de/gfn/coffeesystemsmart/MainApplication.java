package de.gfn.coffeesystemsmart;

import de.gfn.coffeesystemsmart.Classes.Cappuccino;
import de.gfn.coffeesystemsmart.Controller.MainWindowController;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ResourceBundle;



public class MainApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        //Splash Stage für Temporäres Fenster --> Start Fenster
        Stage splashStage = new Stage();
        FXMLLoader splashLoader = new FXMLLoader(MainApplication.class.getResource("start-view.fxml"));

        Scene splashScene = new Scene(splashLoader.load());
        splashStage.setScene(splashScene);
        splashStage.setTitle("Loading...");
        splashStage.show();

        // Timer wird gestartet
        PauseTransition pause = new PauseTransition(Duration.seconds(0)); // Wieder auf 5 sek ändern nach fertig

        pause.setOnFinished(event -> {
            splashStage.close(); //<-- Timer wird geschlossen

            try {

                // Start Main Window in einem Try Catch für IOExceptions
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));

                ResourceBundle bundle = ResourceBundle.getBundle("lang.msg");
                fxmlLoader.setResources(bundle);

                Scene mainScene = new Scene(fxmlLoader.load()); // Fenster-Größe rausgenommen, damit es richtig angezeigt wird
                primaryStage.setTitle("Smart Coffee");
                primaryStage.setScene(mainScene);
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

