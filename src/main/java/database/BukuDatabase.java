package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Buku;

import java.sql.*;

public class BukuDatabase {

    public static void seedData() {
        String cekSql = "SELECT COUNT(*) FROM buku";

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(cekSql)) {

            if (r.next() && r.getInt(1) > 0) {
                return;
            }

        } catch (Exception e) {
            System.out.println("CEK DATA ERROR: " + e.getMessage());
        }

        // 🔹 Buku awal → SEMUA TERSEDIA
        insert(new Buku("B001", "Pemrograman Java Dasar", "Andi Wijaya", 2021, 320, "TERSEDIA"));
        insert(new Buku("B002", "Struktur Data dan Algoritma", "Budi Santoso", 2022, 450, "TERSEDIA"));
        insert(new Buku("B003", "Basis Data MySQL", "Citra Lestari", 2020, 380, "TERSEDIA"));
        insert(new Buku("B004", "Pemrograman Berorientasi Objek", "Dewi Anggraini", 2023, 410, "TERSEDIA"));
        insert(new Buku("B005", "JavaFX untuk Aplikasi Desktop", "Eko Pratama", 2024, 290, "TERSEDIA"));
        insert(new Buku("B006", "Rekayasa Perangkat Lunak", "Fajar Nugroho", 2019, 500, "TERSEDIA"));
        insert(new Buku("B007", "Analisis dan Perancangan Sistem", "Gita Rahmawati", 2021, 360, "TERSEDIA"));
        insert(new Buku("B008", "Algoritma dan Pemrograman", "Hadi Saputra", 2020, 340, "TERSEDIA"));
        insert(new Buku("B009", "Pemrograman Web Lanjut", "Indah Kurnia", 2022, 420, "TERSEDIA"));
        insert(new Buku("B010", "Kecerdasan Buatan Dasar", "Joko Firmansyah", 2023, 390, "TERSEDIA"));
    }



    public static void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS buku (
            id_buku VARCHAR(10) PRIMARY KEY,
            nama VARCHAR(100),
            penulis VARCHAR(100),
            tahun INT,
            halaman INT,
            status TEXT
        )
        """;

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insert(Buku b) {
        String sql = "INSERT INTO buku VALUES (?,?,?,?,?,?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getId());
            ps.setString(2, b.getNama());
            ps.setString(3, b.getPenulis());
            ps.setInt(4, b.getTahun());
            ps.setInt(5, b.getHalaman());
            ps.setString(6, "TERSEDIA");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateStatus(String id, String status) {
        String sql = "UPDATE buku SET status=? WHERE id_buku=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public static void delete(String id) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM buku WHERE id_buku=?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(Buku b) {
        String sql = "UPDATE buku SET nama=?, penulis=?, tahun=?, halaman=? WHERE id_buku=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getNama());
            ps.setString(2, b.getPenulis());
            ps.setInt(3, b.getTahun());
            ps.setInt(4, b.getHalaman());
            ps.setString(5, b.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ObservableList<Buku> getAll() {
        ObservableList<Buku> list = FXCollections.observableArrayList();
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery("SELECT * FROM buku")) {

            while (r.next()) {
                list.add(new Buku(
                        r.getString("id_buku"),
                        r.getString("nama"),
                        r.getString("penulis"),
                        r.getInt("tahun"),
                        r.getInt("halaman"),
                        r.getString("status") // 🔥 PENTING
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}