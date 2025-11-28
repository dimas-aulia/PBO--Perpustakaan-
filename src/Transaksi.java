import java.util.ArrayList;

public class Transaksi {

    private ArrayList<TransaksiItem> riwayat = new ArrayList<>();

    // =======================================
    // 1. FUNGSI PINJAM BUKU
    // =======================================
    public void pinjamBuku(String idT, Anggota a, Buku b) {

        TransaksiItem t = new TransaksiItem(idT, a, b);
        riwayat.add(t);

        System.out.println("Buku \"" + b.getJudul() + "\" dipinjam oleh " + a.nama);
    }
}