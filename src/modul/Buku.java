package model;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Koneksi;

public class Buku {

    private String id, nama, penulis;
    private int tahun, halaman;

    public Buku(String id, String nama, String penulis, int tahun, int halaman) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.halaman = halaman;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }
    public int getHalaman() { return halaman; }

    // ===== DAO =====
    public static void insert(Buku b) {
        String sql = "INSERT INTO buku (id_buku, nama, penulis, tahun, halaman) VALUES (?,?,?,?,?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.id);
            ps.setString(2, b.nama);
            ps.setString(3, b.penulis);
            ps.setInt(4, b.tahun);
            ps.setInt(5, b.halaman);
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
            ps.setInt(4, b.halaman);
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
