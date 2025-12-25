package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Anggota;

import java.sql.*;

public class AnggotaDatabase {

    public static void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS anggota (
            id_user VARCHAR(10) PRIMARY KEY,
            nama VARCHAR(100),
            telepon VARCHAR(20),
            jurusan VARCHAR(100)
        )
        """;

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void seedData() {
        if (!getAll().isEmpty()) return;

        insert(new Anggota("A001","Andi","0811111111","Informatika"));
        insert(new Anggota("A002","Budi","0822222222","Sistem Informasi"));
        insert(new Anggota("A003","Citra","0833333333","Manajemen"));
        insert(new Anggota("A004","Dewi","0844444444","Akuntansi"));
        insert(new Anggota("A005","Eko","0855555555","Informatika"));
        insert(new Anggota("A006","Fajar","0866666666","Elektro"));
        insert(new Anggota("A007","Gita","0877777777","Hukum"));
        insert(new Anggota("A008","Hadi","0888888888","Ekonomi"));
        insert(new Anggota("A009","Indah","0899999999","Psikologi"));
        insert(new Anggota("A010","Joko","0810000000","Informatika"));
    }

    public static void insert(Anggota a) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("INSERT INTO anggota VALUES (?,?,?,?)")) {

            ps.setString(1, a.getIdUser());
            ps.setString(2, a.getNama());
            ps.setString(3, a.getTelepon());
            ps.setString(4, a.getJurusan());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delete(String id) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM anggota WHERE id_user=?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(Anggota a) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement(
                             "UPDATE anggota SET nama=?, telepon=?, jurusan=? WHERE id_user=?")) {

            ps.setString(1, a.getNama());
            ps.setString(2, a.getTelepon());
            ps.setString(3, a.getJurusan());
            ps.setString(4, a.getIdUser());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ObservableList<Anggota> getAll() {
        ObservableList<Anggota> list = FXCollections.observableArrayList();
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery("SELECT * FROM anggota")) {

            while (r.next()) {
                list.add(new Anggota(
                        r.getString("id_user"),
                        r.getString("nama"),
                        r.getString("telepon"),
                        r.getString("jurusan")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
