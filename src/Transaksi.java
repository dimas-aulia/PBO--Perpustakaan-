public class Transaksi {

    private String idTransaksi;
    private Anggota anggota;
    private Buku buku;
    private boolean statusDipinjam = false;

    public void pinjamBuku(String idT, Anggota a, Buku b) {
        this.idTransaksi = idT;
        this.anggota = a;
        this.buku = b;
        this.statusDipinjam = true;

        System.out.println("Buku \"" + b.getJudul() + "\" dipinjam oleh " + a.nama);
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
            System.out.println("Buku \"" + buku.getJudul() + "\" telah dikembalikan!");
        } else {
            System.out.println("Transaksi sudah pernah dikembalikan!");
        }
    }

    public void tampilRiwayat() {
        if (idTransaksi == null) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.println("\n===== RIWAYAT =====");
        System.out.println(
                idTransaksi + " | " +
                        anggota.nama + " | " +
                        buku.getJudul() + " | " +
                        (statusDipinjam ? "Dipinjam" : "Dikembalikan")
        );
    }
}
