package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Buku;
import java.sql.*;


public class DaftarBukuDatabase {

    public static ObservableList<Buku> search(String query) {
        ObservableList<Buku> list = FXCollections.observableArrayList();
        // Mencari berdasarkan nama atau penulis
        String sql = "SELECT * FROM buku WHERE nama LIKE ? OR penulis LIKE ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            String pattern = "%" + query + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    list.add(new Buku(
                            r.getString("id_buku"),
                            r.getString("nama"),
                            r.getString("penulis"),
                            r.getInt("tahun"),
                            r.getInt("halaman"),
                            r.getString("status")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Search Error: " + e.getMessage());
        }
        return list;
    }

    public static ObservableList<Buku> getAll() {
        ObservableList<Buku> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM buku";

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            while (r.next()) {
                // Ambil data dari kolom database
                String id = r.getString("id_buku");
                String nama = r.getString("nama");
                String penulis = r.getString("penulis");
                int tahun = r.getInt("tahun");
                int halaman = r.getInt("halaman");
                String status = r.getString("status");
                // Masukkan ke dalam list sebagai objek Buku
                list.add(new Buku(id, nama, penulis, tahun, halaman,status));
            }

        } catch (Exception e) {
            System.out.println("Error getAll: " + e.getMessage());
        }

        return list;
    }
}