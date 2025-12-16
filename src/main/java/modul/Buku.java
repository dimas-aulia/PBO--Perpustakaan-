package modul;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import app.Koneksi;

public class Buku {

    private String id, nama, penulis;
    private int tahun;
    private Halaman halaman; // ← KOMPOSISI

    public Buku(String id, String nama, String penulis, int tahun, int jumlahHalaman) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.halaman = new Halaman(jumlahHalaman); // ← dibuat di dalam Buku
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }

    public int getHalaman() {
        return halaman.getJumlahHalaman();
    }

    public static void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS buku (
            id_buku VARCHAR(10) PRIMARY KEY,
            nama    VARCHAR(100) NOT NULL,
            penulis VARCHAR(100) NOT NULL,
            tahun   INT NOT NULL,
            halaman INT NOT NULL
        )
    """;

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement()) {

            s.execute(sql);

        } catch (Exception e) {
            System.out.println("CREATE TABLE ERROR : " + e.getMessage());
        }
    }

    public static void seedData() {
        // cek apakah data sudah ada
        String cekSql = "SELECT COUNT(*) FROM buku";

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(cekSql)) {

            if (r.next() && r.getInt(1) > 0) {
                return; // ❗ data sudah ada, jangan isi ulang
            }

        } catch (Exception e) {
            System.out.println("CEK DATA ERROR : " + e.getMessage());
        }

        // jika kosong → isi 10 data awal
        insert(new Buku("B001", "Pemrograman Java Dasar", "Andi Wijaya", 2021, 320));
        insert(new Buku("B002", "Struktur Data dan Algoritma", "Budi Santoso", 2022, 450));
        insert(new Buku("B003", "Basis Data MySQL", "Citra Lestari", 2020, 380));
        insert(new Buku("B004", "Pemrograman Berorientasi Objek", "Dewi Anggraini", 2023, 410));
        insert(new Buku("B005", "JavaFX untuk Aplikasi Desktop", "Eko Pratama", 2024, 290));
        insert(new Buku("B006", "Rekayasa Perangkat Lunak", "Fajar Nugroho", 2019, 500));
        insert(new Buku("B007", "Analisis dan Perancangan Sistem", "Gita Rahmawati", 2021, 360));
        insert(new Buku("B008", "Algoritma dan Pemrograman", "Hadi Saputra", 2020, 340));
        insert(new Buku("B009", "Pemrograman Web Lanjut", "Indah Kurnia", 2022, 420));
        insert(new Buku("B010", "Kecerdasan Buatan Dasar", "Joko Firmansyah", 2023, 390));
    }


    // ===== DAO =====
    public static void insert(Buku b) {
        String sql = "INSERT INTO buku (id_buku, nama, penulis, tahun, halaman) VALUES (?,?,?,?,?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.id);
            ps.setString(2, b.nama);
            ps.setString(3, b.penulis);
            ps.setInt(4, b.tahun);
            ps.setInt(5, b.halaman.getJumlahHalaman());
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

            ps.setString(1, b.nama);
            ps.setString(2, b.penulis);
            ps.setInt(3, b.tahun);
            ps.setInt(4, b.halaman.getJumlahHalaman());
            ps.setString(5, b.id);
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
                        r.getInt("halaman")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
