import java.util.ArrayList;

public class Transaksi {

    private class Item {
        String idTransaksi;
        Anggota anggota;
        Buku buku;
        boolean statusDipinjam;

        Item(String idTransaksi, Anggota anggota, Buku buku) {
            this.idTransaksi = idTransaksi;
            this.anggota = anggota;
            this.buku = buku;
            this.statusDipinjam = true;
        }
    }

    private ArrayList<Item> riwayat = new ArrayList<>();

    public void pinjamBuku(String idT, Anggota a, Buku b) {

        Item t = new Item(idT, a, b);
        riwayat.add(t);

        System.out.println("Buku \"" + b.getJudul() + "\" dipinjam oleh " + a.nama);
    }

    public void kembaliBuku(String idT) {

        boolean ditemukan = false;

        for (Item t : riwayat) {
            if (t.idTransaksi.equals(idT)) {

                if (t.statusDipinjam) {
                    t.statusDipinjam = false;
                    System.out.println("Buku \"" + t.buku.getJudul() + "\" telah dikembalikan!");
                } else {
                    System.out.println("Transaksi sudah pernah dikembalikan!");
                }

                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("ID Transaksi tidak ditemukan!");
        }
    }
    
    public void tampilRiwayat() {

        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.println("\n===== RIWAYAT TRANSAKSI =====");

        for (Item t : riwayat) {
            System.out.println(
                    t.idTransaksi + " | " +
                            t.anggota.nama + " | " +
                            t.buku.getJudul() + " | " +
                            (t.statusDipinjam ? "Dipinjam" : "Dikembalikan")
            );
        }
    }
}
