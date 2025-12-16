package de.gfn.coffeesystemsmart.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static javafx.application.Platform.exit;


public class MainWindowController {

    @FXML
    private Label timeDate;

    @FXML
    public void btnExit() {
        exit();
    }

}

