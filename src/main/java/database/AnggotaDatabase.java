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
                    jurusan VARCHAR(100),
                    kelas VARCHAR(5)
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

        insert(new Anggota("A001", "Dimas", "0823654379", "Informatika", "E"));
        insert(new Anggota("A002", "Firdi", "0835362721", "Informatika", "E"));
        insert(new Anggota("A003", "Faris", "0812648392", "Manajemen", "E"));
        insert(new Anggota("A004", "Hafid", "08244719924", "Akuntansi", "D"));
        insert(new Anggota("A005", "Budi Santoso", "081234567890", "Teknik Informatika", "A"));
        insert(new Anggota("A006", "Siti Nurhaliza", "082345678901", "Sistem Informasi", "B"));
        insert(new Anggota("A007", "Andi Wijaya", "083456789012", "Teknik Elektro", "C"));
        insert(new Anggota("A008", "Dewi Lestari", "084567890123", "Manajemen", "D"));
        insert(new Anggota("A009", "Eko Prasetyo", "085678901234", "Akuntansi", "E"));
        insert(new Anggota("A010", "Ratna Sari", "086789012345", "Desain Komunikasi Visual", "F"));

    }


    public static void insert(Anggota a) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("INSERT INTO anggota VALUES (?,?,?,?,?)")) {

            ps.setString(1, a.getIdUser());
            ps.setString(2, a.getNama());
            ps.setString(3, a.getTelepon());
            ps.setString(4, a.getJurusan());
            ps.setString(5, a.getKelas());
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
                             "UPDATE anggota SET nama=?, telepon=?, jurusan=?, kelas=? WHERE id_user=?")) {

            ps.setString(1, a.getNama());
            ps.setString(2, a.getTelepon());
            ps.setString(3, a.getJurusan());
            ps.setString(4, a.getKelas());
            ps.setString(5, a.getIdUser());
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
                        r.getString("jurusan"),
                        r.getString("kelas")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}

