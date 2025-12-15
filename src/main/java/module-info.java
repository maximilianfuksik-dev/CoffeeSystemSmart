module de.gfn.coffeesystemsmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens de.gfn.coffeesystemsmart to javafx.fxml;
    exports de.gfn.coffeesystemsmart;
    exports de.gfn.coffeesystemsmart.Controller;
    opens de.gfn.coffeesystemsmart.Controller to javafx.fxml;
}