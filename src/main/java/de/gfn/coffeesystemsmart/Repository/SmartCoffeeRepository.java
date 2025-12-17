package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.db.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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








}
