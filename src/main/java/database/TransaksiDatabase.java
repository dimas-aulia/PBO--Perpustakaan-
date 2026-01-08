package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Transaksi;
import java.sql.*;
import java.time.LocalDate;

public class TransaksiDatabase {

    public static void createTable() {
        // Perintah SQL yang benar dengan id_transaksi
        String sql = """
            CREATE TABLE IF NOT EXISTS transaksi (
                id_transaksi INTEGER PRIMARY KEY AUTOINCREMENT,
                id_user VARCHAR(10),
                nama_anggota VARCHAR(100),
                id_buku VARCHAR(10),
                judul_buku VARCHAR(100),
                tgl_pinjam DATE,
                tgl_kembali DATE DEFAULT NULL
            )
            """;
        try (Connection c = Koneksi.getConnection(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    public static boolean pinjamBuku(String idUser, String nama, String idBuku, String judul) {
        // Validasi: Apakah user meminjam buku yang sama dan belum dikembalikan?
        String cekSql = "SELECT * FROM transaksi WHERE id_user = ? AND id_buku = ? AND tgl_kembali IS NULL";

        try (Connection c = Koneksi.getConnection()) {
            PreparedStatement psCek = c.prepareStatement(cekSql);
            psCek.setString(1, idUser);
            psCek.setString(2, idBuku);
            ResultSet rs = psCek.executeQuery();

            if (rs.next()) return false; // Sudah meminjam buku yang sama

            String sql = "INSERT INTO transaksi (id_user, nama_anggota, id_buku, judul_buku, tgl_pinjam) VALUES (?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, idUser);
            ps.setString(2, nama);
            ps.setString(3, idBuku);
            ps.setString(4, judul);
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

            // Ubah status buku menjadi DIPINJAM
            BukuDatabase.updateStatus(idBuku, "DIPINJAM");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void kembalikanBuku(int idTransaksi, String idBuku) {
        String sql = "UPDATE transaksi SET tgl_kembali = ? WHERE id_transaksi = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, idTransaksi);
            ps.executeUpdate();

            // Ubah status buku menjadi TERSEDIA
            BukuDatabase.updateStatus(idBuku, "TERSEDIA");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void hapus(int id) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static ObservableList<Transaksi> getAll() {
        ObservableList<Transaksi> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM transaksi";
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                list.add(new Transaksi(
                        r.getInt("id_transaksi"),
                        r.getString("id_user"),
                        r.getString("nama_anggota"),
                        r.getString("id_buku"),
                        r.getString("judul_buku"),
                        r.getDate("tgl_pinjam").toLocalDate(),
                        r.getDate("tgl_kembali") != null ? r.getDate("tgl_kembali").toLocalDate() : null
                ));
            }
        } catch (SQLException e) {
            System.out.println("Data gagal dimuat: " + e.getMessage());
        }
        return list;
    }
}