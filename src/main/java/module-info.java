module de.gfn.coffeesystemsmart {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.gfn.coffeesystemsmart to javafx.fxml;
    exports de.gfn.coffeesystemsmart;
}