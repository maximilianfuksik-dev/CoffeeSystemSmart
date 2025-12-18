package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import de.gfn.coffeesystemsmart.Controller.ErrorWindowController;
import de.gfn.coffeesystemsmart.MainApplication;
import de.gfn.coffeesystemsmart.config.LanguageChange;
import de.gfn.coffeesystemsmart.db.DatabaseUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmartCoffeeRepository {
// Hier müssen Datensätze gelesen und gespeichert werden
// Controller --> Service --> Repository --> DB
// Kein SQL im Controller
// Kein JavaFX im Repository
// Keine DB-Connection im FXML
// Repository = Daten
// Service = Logik
// Controller = Anzeige
public static boolean isBroken;

    public static boolean machineBreaks() throws IOException {

        Random rand = new Random();
        int probability = rand.nextInt(101);
        if (probability < 2) {
            FXMLLoader errorLoader = new FXMLLoader(MainApplication.class.getResource("error-view.fxml"));
            errorLoader.setResources(LanguageChange.getBundle());
            Scene error = new Scene(errorLoader.load());

            ErrorWindowController controller = errorLoader.getController();
            controller.errorMsgCreate(LanguageChange.getBundle().getString("error.msg.machineBreak"));

            Stage errorStage = new Stage();
            errorStage.setTitle(LanguageChange.getBundle().getString("label.error"));
            errorStage.setScene(error);
            errorStage.show();
            return isBroken = true;
        }
        else {
            return isBroken = false;
        }
    }


    public static CoffeeEntity findCoffeeById(int id) throws SQLException {
        String sql = "Select CoffeeTypeID, CoffeeName, CoffeePrice FROM CoffeeTable WHERE CoffeeTypeID = ?";

        try(Connection conn = DatabaseUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CoffeeEntity(
                        rs.getInt("CoffeeTypeID"),
                        rs.getString("CoffeeName"),
                        rs.getDouble("CoffeePrice")
                );
            }
            return null;
        }
    }

    public static List<CoffeeEntity> findAllCoffee() throws SQLException {
        return get("SELECT * FROM Coffeetable ");
    }

    public static List<CoffeeEntity> get(final String SQL) throws SQLException {
        try (Connection conn = DatabaseUtils.getConnection();) {
            Statement stmt = conn.createStatement(); // Anfrage-Objekt erzeugen
            stmt.execute(SQL);
            ResultSet result = stmt.getResultSet(); // Anfrage in zwei Lines (Selbe wie darüber)

            List<CoffeeEntity> coffees = new ArrayList<>();
            while (result.next()) {
                coffees.add((CoffeeEntity) populate(result));
            }
            return coffees;
        }
    }

    private static Coffee populate(ResultSet row) throws SQLException{
        CoffeeEntity c = new CoffeeEntity();
        c.setId(row.getInt("CoffeeTypeID"));
        c.setName(row.getString("CoffeeName"));
        c.setPrice(row.getDouble("CoffeePrice"));
        c.setmLiter(row.getInt("ConLiter"));
        return c;
    }



    public static String[] coffeeTipsDE = {
            "Kaffee ist eines der meistgetrunkenen Getränke der Welt.",
            "Die Kaffeepflanze stammt ursprünglich aus Äthiopien.",
            "Es gibt über 100 Arten von Kaffeepflanzen, aber Arabica und Robusta sind die wichtigsten.",
            "Arabica-Kaffee gilt als aromatischer und milder als Robusta.",
            "Robusta-Kaffee enthält mehr Koffein als Arabica.",
            "Koffein wirkt anregend auf das zentrale Nervensystem.",
            "Kaffeebohnen sind eigentlich die gerösteten Samen einer Kaffeekirsche.",
            "Der Röstgrad beeinflusst Geschmack, Säure und Bitterkeit des Kaffees.",
            "Heller Röstkaffee enthält meist mehr Säure als dunkler Röstkaffee.",
            "Espresso ist die Basis vieler Kaffeespezialitäten wie Cappuccino oder Latte Macchiato.",
            "Kaffee wurde im 15. Jahrhundert erstmals in der arabischen Welt konsumiert.",
            "Deutschland gehört zu den größten Kaffeekonsumenten Europas.",
            "Kaffee enthält neben Koffein auch Antioxidantien.",
            "Der Geschmack von Kaffee wird durch Herkunft, Boden und Klima beeinflusst.",
            "Frisch gemahlener Kaffee schmeckt intensiver als vorgemahlener Kaffee.",
            "Kaffee sollte luftdicht, kühl und dunkel gelagert werden.",
            "Ein Cappuccino besteht klassisch aus Espresso, Milch und Milchschaum.",
            "Entkoffeinierter Kaffee enthält immer noch geringe Mengen Koffein.",
            "Die ideale Brühtemperatur für Kaffee liegt bei etwa 92 bis 96 Grad Celsius.",
            "Kaffee kann die Konzentration und Aufmerksamkeit verbessern."
    };
    public static String[] coffeeTipsENG = {
            "Coffee is one of the most consumed beverages in the world.",
            "The coffee plant originally comes from Ethiopia.",
            "There are over 100 species of coffee plants, but Arabica and Robusta are the most important.",
            "Arabica coffee is considered more aromatic and milder than Robusta.",
            "Robusta coffee contains more caffeine than Arabica.",
            "Caffeine has a stimulating effect on the central nervous system.",
            "Coffee beans are actually the roasted seeds of a coffee cherry.",
            "The roast level influences the flavor, acidity, and bitterness of coffee.",
            "Light roasts usually contain more acidity than dark roasts.",
            "Espresso is the base for many coffee specialties such as cappuccino or latte macchiato.",
            "Coffee was first consumed in the Arab world during the 15th century.",
            "Germany is one of the largest coffee consumers in Europe.",
            "Coffee contains antioxidants in addition to caffeine.",
            "The taste of coffee is influenced by origin, soil, and climate.",
            "Freshly ground coffee tastes more intense than pre-ground coffee.",
            "Coffee should be stored airtight, cool, and away from light.",
            "A classic cappuccino consists of espresso, milk, and milk foam.",
            "Decaffeinated coffee still contains small amounts of caffeine.",
            "The ideal brewing temperature for coffee is about 92 to 96 degrees Celsius.",
            "Coffee can improve concentration and alertness."

    };
}
