package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.Transaksi;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TransaksiDatabase {

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS transaksi (
                id_transaksi INTEGER PRIMARY KEY AUTOINCREMENT,
                id_user VARCHAR(10),
                nama_anggota VARCHAR(100),
                id_buku VARCHAR(10),
                judul_buku VARCHAR(100),
                tgl_pinjam DATE,
                tgl_kembali DATE NULL,
                denda INTEGER DEFAULT 0
            )
            """;
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean pinjamBuku(String idUser, String nama, String idBuku, String judul, LocalDate tgl) {
        String cek = "SELECT * FROM transaksi WHERE id_user=? AND id_buku=? AND tgl_kembali IS NULL";

        // Pastikan setiap PreparedStatement berada dalam try-with-resources agar tidak terjadi 'Database Locked'
        try (Connection c = Koneksi.getConnection();
             PreparedStatement psCek = c.prepareStatement(cek)) {

            psCek.setString(1, idUser);
            psCek.setString(2, idBuku);

            // Tutup ResultSet segera setelah pengecekan
            try (ResultSet rs = psCek.executeQuery()) {
                if (rs.next()) return false;
            }

            String sql = "INSERT INTO transaksi (id_user, nama_anggota, id_buku, judul_buku, tgl_pinjam) VALUES (?,?,?,?,?)";
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, idUser);
                ps.setString(2, nama);
                ps.setString(3, idBuku);
                ps.setString(4, judul);
                ps.setDate(5, Date.valueOf(tgl));
                ps.executeUpdate();
            }

            // Memperbarui status buku menjadi DIPINJAM
            BukuDatabase.updateStatus(idBuku, "DIPINJAM");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void kembalikanBuku(int id, String idBuku, LocalDate p, LocalDate k) {
        // Hitung selisih hari antara tanggal pinjam dan kembali
        long selisih = ChronoUnit.DAYS.between(p, k);
        // Jika lebih dari 7 hari, denda 10.000
        int denda = (selisih > 7) ? 10000 : 0;

        String sql = "UPDATE transaksi SET tgl_kembali=?, denda=? WHERE id_transaksi=?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(k));
            ps.setInt(2, denda);
            ps.setInt(3, id);

            int rowsAffected = ps.executeUpdate();

            // SINKRONISASI: Hanya update status buku jika update transaksi berhasil
            if (rowsAffected > 0) {
                BukuDatabase.updateStatus(idBuku, "TERSEDIA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Transaksi> getAll() {
        ObservableList<Transaksi> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";

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
                        r.getDate("tgl_kembali") != null ? r.getDate("tgl_kembali").toLocalDate() : null,
                        r.getInt("denda")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void hapus(int id) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}