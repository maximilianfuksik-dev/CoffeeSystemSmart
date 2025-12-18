package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.db.DatabaseUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatRepository {

    public static long insertOrderCash(String coffename, boolean withMilk, int priceCents, Integer empId) throws SQLException {
        String sql = """
                INSERT INTO Orders (Type, WMilk, Price, EmpID, Date)
                VALUES (?,?,?,?,?)
                """;
        try(Connection conn = DatabaseUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, coffename);
            ps.setInt(2, withMilk ? 1 : 0);
            ps.setInt(3, priceCents);
            ps.setInt(4, empId);
            ps.setString(5, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
            return -1;
        }

    }
// Controller --> Service --> Repository --> DB
// Kein SQL im Controller
// Kein JavaFX im Repository
// Keine DB-Connection im FXML
// Repository = Daten
// Service = Logik
// Controller = Anzeige
    // Hier müssen Datensätzen nur glesen werden bzw. Ausgewertet


    // Method for counting the all orders
    // UpdateMethod
    // Method for evaluating data by type

    // WHERE Clause make it dynamic

    // Mfilter by profit ((Sort by Day , Week , Month, Year, All Time )
    // Mfilter by customer / Worker (Sort by Day , Week , Month, Year, All Time )
    // Mfilter by order (Sort by Day , Week , Month, Year, All Time )
    // Mfilter by date --> Time + Calendar Day (Sort by Day , Week , Month, Year, All Time )
    // Mfilter by consumption Milk / Water / Coffeebeans (Sort by Day , Week , Month, Year, All Time )
    // Mfilter by defect (Sort  All Time)

// Method for Connection to Database


    // Method for implementing the data onto the charts
}
