module de.gfn.coffeesystemsmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens de.gfn.coffeesystemsmart to javafx.fxml;
    exports de.gfn.coffeesystemsmart;
    exports de.gfn.coffeesystemsmart.Controller;
    opens de.gfn.coffeesystemsmart.Controller to javafx.fxml;
    exports de.gfn.coffeesystemsmart.Repository;
    opens de.gfn.coffeesystemsmart.Repository to javafx.fxml;
    exports de.gfn.coffeesystemsmart.Classes;
    opens de.gfn.coffeesystemsmart.Classes to javafx.fxml;
}