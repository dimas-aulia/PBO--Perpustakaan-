package ModulOOP;

public class Transaksi {

    private String idTransaksi;
    private Petugas petugas;
    private Anggota anggota;
    private Buku buku;
    private boolean statusDipinjam = false;

    public Transaksi() {}

    public void pinjamBuku(String idT, Petugas p, Anggota a, Buku b) {

        this.idTransaksi = idT;
        this.petugas = p;
        this.anggota = a;
        this.buku = b;
        this.statusDipinjam = true;

        System.out.println("\n=== PEMINJAMAN BERHASIL ===");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Petugas      : " + p.getNama());
        System.out.println("Anggota      : " + a.getNama());
        System.out.println("Buku         : " + b.getJudul());   // FIX
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
            System.out.println("Buku \"" + buku.getJudul() + "\" telah dikembalikan.");  // FIX
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
        System.out.println("Petugas      : " + petugas.getNama());
        System.out.println("Anggota      : " + anggota.getNama());
        System.out.println("Buku         : " + buku.getJudul());   // FIX
        System.out.println("Status       : " + (statusDipinjam ? "Dipinjam" : "Dikembalikan"));
    }
}
