package ModulOOP;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {

    private String idTransaksi;
    private Petugas petugas;
    private Anggota anggota;
    private Buku buku;
    private boolean statusDipinjam = false;

    private LocalDateTime tanggalPinjam;
    private LocalDateTime tanggalKembali;   // <-- waktu kembali otomatis

    public Transaksi() {}

    public void pinjamBuku(String idT, Petugas p, Anggota a, Buku b) {

        this.idTransaksi = idT;
        this.petugas = p;
        this.anggota = a;
        this.buku = b;
        this.statusDipinjam = true;

        this.tanggalPinjam = LocalDateTime.now();   // <-- waktu pinjam otomatis

        System.out.println("\n=== PEMINJAMAN BERHASIL ===");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Waktu Pinjam : " + format(tanggalPinjam));
        System.out.println("Petugas      : " + p.getNama());
        System.out.println("Anggota      : " + a.getNama());
        System.out.println("Buku         : " + b.getJudul());
    }

    public void kembaliBuku(String idT) {

        if (idTransaksi == null) {
            System.out.println("Belum ada transaksi!");
            return;
        }

        if (!idTransaksi.equals(idT)) {
            System.out.println("ID transaksi salah!");
            return;
        }

        if (statusDipinjam) {
            statusDipinjam = false;

            this.tanggalKembali = LocalDateTime.now();   // <-- waktu kembali otomatis

            System.out.println("\n=== PENGEMBALIAN BERHASIL ===");
            System.out.println("Buku \"" + buku.getJudul() + "\" telah dikembalikan.");
            System.out.println("Waktu Kembali : " + format(tanggalKembali));
        } else {
            System.out.println("Transaksi sudah dikembalikan sebelumnya!");
        }
    }

    public void tampilRiwayat() {
        if (idTransaksi == null) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.println("\n===== RIWAYAT TRANSAKSI =====");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Waktu Pinjam : " + format(tanggalPinjam));
        System.out.println("Waktu Kembali: " + (tanggalKembali == null ? "-" : format(tanggalKembali)));
        System.out.println("Petugas      : " + petugas.getNama());
        System.out.println("Anggota      : " + anggota.getNama());
        System.out.println("Buku         : " + buku.getJudul());
        System.out.println("Status       : " + (statusDipinjam ? "Dipinjam" : "Dikembalikan"));
    }

    // format tanggal rapi
    private String format(LocalDateTime t) {
        if (t == null) return "-";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return t.format(f);
    }
}