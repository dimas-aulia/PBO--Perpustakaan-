package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Buku;
import java.sql.*;

public class BukuDatabase {
    //membuat table
    public static void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS buku (
            id_buku VARCHAR(10) PRIMARY KEY,
            nama VARCHAR(100),
            penulis VARCHAR(100),
            tahun INT,
            halaman INT,
            status TEXT
        )""";
        try (Connection c = Koneksi.getConnection(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) { e.printStackTrace(); }
    }
//menambahkan buku
    public static void insert(Buku b) {
        String sql = "INSERT INTO buku (id_buku, nama, penulis, tahun, halaman, status) VALUES (?,?,?,?,?,?)";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getId());
            ps.setString(2, b.getNama());
            ps.setString(3, b.getPenulis());
            ps.setInt(4, b.getTahun());
            ps.setInt(5, b.getHalaman());
            ps.setString(6, b.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
//mengubah data
    public static void update(Buku b) {
        String sql = "UPDATE buku SET nama=?, penulis=?, tahun=?, halaman=? WHERE id_buku=?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getNama());
            ps.setString(2, b.getPenulis());
            ps.setInt(3, b.getTahun());
            ps.setInt(4, b.getHalaman());
            ps.setString(5, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
//mengupdate status buku
    public static void updateStatus(String idBuku, String statusBaru) {
        String sql = "UPDATE buku SET status = ? WHERE id_buku = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, statusBaru);
            ps.setString(2, idBuku);
            ps.executeUpdate();

            System.out.println("Status buku " + idBuku + " berhasil diubah menjadi: " + statusBaru);
        } catch (SQLException e) {
            System.err.println("Gagal update status buku: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void delete(String id) {
        String sql = "DELETE FROM buku WHERE id_buku=?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static ObservableList<Buku> getAll() {
        ObservableList<Buku> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM buku";
        try (Connection c = Koneksi.getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                list.add(new Buku(
                        r.getString("id_buku"), r.getString("nama"), r.getString("penulis"),
                        r.getInt("tahun"), r.getInt("halaman"), r.getString("status")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}