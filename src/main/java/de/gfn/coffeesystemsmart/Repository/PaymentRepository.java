package de.gfn.coffeesystemsmart.Repository;

import de.gfn.coffeesystemsmart.db.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PaymentRepository {

    public static Map<Integer, Integer> getChangeInv() throws SQLException {
        String sql = "SELECT Coins, Amount FROM ChangeBack ";
        Map<Integer, Integer> inv = new LinkedHashMap<>();

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int coin = Integer.parseInt(rs.getString("Coins"));
                int amount = rs.getInt("Amount");
                inv.put(coin, amount);
            }
        }
        return inv;
    }

    public static Map<Integer, Integer> makeChange(int changeCents, Map<Integer, Integer> inventory) {
        int[] coinInCent = {200, 100, 50, 20, 10, 5, 1};
        Map<Integer, Integer> result = new LinkedHashMap<>();

        int remaining = changeCents;

        for (int d : coinInCent) {
            int available = inventory.getOrDefault(d, 0);
            int need = Math.min(remaining / d, available);
            if (need > 0) {
                result.put(d, need);
                remaining -= d * need;
            }
        }
        return result;
    }

    public static void payAndOrder(String coffeeName, int priceCents, int empId, boolean withMilk, Map<Integer, Integer> insertedCoins, int changeCents) throws SQLException {
        try (Connection conn = DatabaseUtils.getConnection()) {
            conn.setAutoCommit(false);

            try {
                Map<Integer, Integer> inv = new LinkedHashMap<>();
                try (PreparedStatement ps = conn.prepareStatement("SELECT Coins, Amount FROM ChangeBack ");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        inv.put(Integer.parseInt(rs.getString("Coins")), rs.getInt("Amount"));
                    }
                }

                Map<Integer, Integer> changeCoins = makeChange(changeCents, inv);
                if (changeCoins == null) {
                    throw new SQLException("NO_CHANGE_AVAILABLE");
                }
                try (PreparedStatement upd = conn.prepareStatement("UPDATE ChangeBack SET AMOUNT = AMOUNT + ? WHERE Coins = ? ")) {
                    for (var e : insertedCoins.entrySet()) {
                        upd.setInt(1, e.getValue());
                        upd.setString(2, String.valueOf(e.getKey()));
                        int updated = upd.executeUpdate();

                        if (updated == 0) {
                            try (PreparedStatement ins = conn.prepareStatement(
                                    "INSERT INTO ChangeBack (Coins, Amount) VALUES (?, ?)")) {
                                ins.setString(1, String.valueOf(e.getKey()));
                                ins.setInt(2, e.getValue());
                                ins.executeUpdate();
                            }
                        }

                    }
                }
                try (PreparedStatement upd = conn.prepareStatement(
                        "UPDATE ChangeBack SET Amount = Amount - ? WHERE Coins = ?")) {
                    for (var e : changeCoins.entrySet()) {
                        upd.setInt(1, e.getValue());
                        upd.setString(2, String.valueOf(e.getKey()));
                        upd.executeUpdate();
                    }
                }
                try (PreparedStatement ins = conn.prepareStatement("""
                        INSERT INTO "Orders"(Type, WMilk, Price, EmpID, Date)
                        VALUES (?, ?, ?, ?, ?)
                        """)) {
                    ins.setString(1, coffeeName);
                    ins.setInt(2, withMilk ? 1 : 0);
                    ins.setInt(3, priceCents);
                    ins.setInt(4, empId);
                    ins.setDouble(5, System.currentTimeMillis() / 1000.0);
                    ins.executeUpdate();
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
