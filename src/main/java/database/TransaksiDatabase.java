package database;

import app.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TransaksiDatabase {

    public static void createTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS transaksi (
                id_transaksi INTEGER PRIMARY KEY AUTOINCREMENT,
                id_user TEXT,
                nama_anggota TEXT,
                id_buku TEXT,
                judul_buku TEXT,
                tgl_pinjam DATE,
                tgl_kembali DATE,
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

    // =========================
    // PINJAM BUKU
    // =========================
    public static boolean pinjamBuku(Transaksi t) {

        String cek = """
            SELECT * FROM transaksi
            WHERE id_user=? AND id_buku=? AND tgl_kembali IS NULL
        """;

        try (Connection c = Koneksi.getConnection();
             PreparedStatement psCek = c.prepareStatement(cek)) {

            psCek.setString(1, t.getIdUser());
            psCek.setString(2, t.getIdBuku());

            try (ResultSet rs = psCek.executeQuery()) {
                if (rs.next()) return false;
            }

            String sql = """
                INSERT INTO transaksi
                (id_user, nama_anggota, id_buku, judul_buku, tgl_pinjam)
                VALUES (?,?,?,?,?)
            """;

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, t.getIdUser());
                ps.setString(2, t.getNamaAnggota());
                ps.setString(3, t.getIdBuku());
                ps.setString(4, t.getJudulBuku());
                ps.setDate(5, Date.valueOf(t.getTanggalPinjam()));
                ps.executeUpdate();
            }

            BukuDatabase.updateStatus(t.getIdBuku(), "DIPINJAM");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // KEMBALIKAN BUKU
    // =========================
    public static void kembalikanBuku(int idTransaksi, String idBuku,
                                      LocalDate pinjam, LocalDate kembali) {

        long selisih = ChronoUnit.DAYS.between(pinjam, kembali);
        int denda = (selisih > 7) ? 10000 : 0;

        String sql = """
            UPDATE transaksi
            SET tgl_kembali=?, denda=?
            WHERE id_transaksi=?
        """;

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(kembali));
            ps.setInt(2, denda);
            ps.setInt(3, idTransaksi);
            ps.executeUpdate();

            BukuDatabase.updateStatus(idBuku, "TERSEDIA");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // GET ALL
    // =========================
    public static ObservableList<Transaksi> getAll() {

        ObservableList<Transaksi> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";

        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            while (r.next()) {

                Anggota a = new Anggota(
                        r.getString("id_user"),
                        r.getString("nama_anggota"),
                        "-",
                        "-",
                        "-"
                );

                Buku b = new Buku(
                        r.getString("id_buku"),
                        r.getString("judul_buku"),
                        "-",
                        0,
                        0,
                        "TERSEDIA"
                );

                list.add(new Transaksi(
                        r.getInt("id_transaksi"),
                        a,
                        b,
                        r.getDate("tgl_pinjam").toLocalDate(),
                        r.getDate("tgl_kembali") != null
                                ? r.getDate("tgl_kembali").toLocalDate()
                                : null,
                        r.getInt("denda")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void hapus(int id) {

        String sql = "DELETE FROM transaksi WHERE id_transaksi=?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
