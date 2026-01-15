package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Petugas;

import java.sql.*;

public class PetugasDatabase {

    public static void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS petugas (
            id_user VARCHAR(10) PRIMARY KEY,
            nama VARCHAR(100),
            telepon VARCHAR(20),
            shift VARCHAR(20)
        )
        """;

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void insert(Petugas p) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("INSERT INTO petugas VALUES (?,?,?,?)")) {

            ps.setString(1, p.getIdUser());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getTelepon());
            ps.setString(4, p.getShift());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delete(String id) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM petugas WHERE id_user=?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(Petugas p) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement(
                             "UPDATE petugas SET nama=?, telepon=?, shift=? WHERE id_user=?")) {

            ps.setString(1, p.getNama());
            ps.setString(2, p.getTelepon());
            ps.setString(3, p.getShift());
            ps.setString(4, p.getIdUser());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ObservableList<Petugas> getAll() {
        ObservableList<Petugas> list = FXCollections.observableArrayList();
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery("SELECT * FROM petugas")) {

            while (r.next()) {
                list.add(new Petugas(
                        r.getString("id_user"),
                        r.getString("nama"),
                        r.getString("telepon"),
                        r.getString("shift")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
