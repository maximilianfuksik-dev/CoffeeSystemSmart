package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.db.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmpRepository {
    // Controller --> Service --> Repository --> DB
    // Lesen und speichern
    // Hier soll die Personla nummer abgeglichen werden mit der DB und wahr dann return an
   // --> EmpoyeeService
    // und Kredit score übergeben

    // Falls EMpKarte Kredits neu speichern in DB bzw. neue Berechen lassen in EmpService



    public static Integer getEmployeeCredits(int empId) throws SQLException {
        String sql = "SELECT CREDITS FROM Employee WHERE EmpId = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Credits");
                } else {
                    return null;
                }
            }
        }
    }

    public static void creditCheckOrder(int empId, int priceCents, String coffeeName, boolean withMilk) throws SQLException {
        String selectSql = "SELECT Credits FROM Employee WHERE EmpId = ?";
        String updateSql = "UPDATE Employee SET Credits = ? WHERE EmpId = ?";
        String insertSql = "INSERT INTO Orders (Type, WMilk, Price, EmpID, Date) VALUES (?,?,?,?,?) ";

        try(Connection conn = DatabaseUtils.getConnection()) {
            conn.setAutoCommit(false);

            try(PreparedStatement select = conn.prepareStatement(selectSql)) {
                select.setInt(1,empId);
                Integer credits;
                try (ResultSet rs = select.executeQuery()) {
                    if(!rs.next()) throw new SQLException("Employee not found: " + empId);
                    credits = rs.getInt("Credits");
                }
                if (credits < priceCents) {
                    throw new SQLException("NOT_ENOUGH_CREDITS");
                }
                int newCredits = credits - priceCents;

                try(PreparedStatement upd = conn.prepareStatement(updateSql)) {
                    upd.setInt(1, newCredits);
                    upd.setInt(2, empId);
                    upd.executeUpdate();
                }

                try(PreparedStatement ins = conn.prepareStatement(insertSql)) {
                    ins.setString(1, coffeeName);
                    ins.setInt(2, withMilk ? 1:0);
                    ins.setInt(3, priceCents);
                    ins.setInt(4, empId);
                    ins.setString(5, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    ins.executeUpdate();
                }
                conn.commit();
            }catch (SQLException exc) {
                conn.rollback();
                throw exc;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
