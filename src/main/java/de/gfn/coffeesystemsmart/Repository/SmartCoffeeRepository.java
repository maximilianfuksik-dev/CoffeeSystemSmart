package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.Classes.Coffee;
import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import de.gfn.coffeesystemsmart.db.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmartCoffeeRepository {
// Hier müssen Datensätze gelesen und gespeichert werden
// Controller --> Service --> Repository --> DB
// Kein SQL im Controller
// Kein JavaFX im Repository
// Keine DB-Connection im FXML
// Repository = Daten
// Service = Logik
// Controller = Anzeige

    public static double getPricebyCoffeeID(int id) {
        //SQL Befehl
        String SQL = "SELECT CoffeePrice FROM Coffeetable WHERE CoffeeTypeID = ?";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL))
        {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getDouble("CoffeePrice");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
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










}
