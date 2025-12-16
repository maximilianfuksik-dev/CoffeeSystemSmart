package de.gfn.coffeesystemsmart;

import de.gfn.coffeesystemsmart.Controller.MainWindowController;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;



public class MainApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));

        ResourceBundle bundle = ResourceBundle.getBundle("lang.msg");
        fxmlLoader.setResources(bundle);

        Scene scene = new Scene(fxmlLoader.load()); // Fenster-Größe rausgenommen, damit es richtig angezeigt wird
        stage.setTitle("Smart Coffee");
        stage.setScene(scene);
        stage.show();
        Logger log = new Logger("MainApplication");
        log.log("start", "Programm Starts", "21");
    }

    public static void main(String[] args) {
        launch();
    }

}
