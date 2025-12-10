package ModulOOP;

public class Buku {
    private String idBuku;
    private String judul;
    private String kategori;
    private Halaman halaman;

    public Buku(String idBuku, String judul, String kategori, int jumlahHalaman) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.kategori = kategori;
        this.halaman = new Halaman(jumlahHalaman);
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {   // <-- tambah getter untuk dipakai di Transaksi
        return judul;
    }

    public void tampilkanInfo() {
        System.out.println("ID Buku     : " + idBuku);
        System.out.println("Judul       : " + judul);
        System.out.println("Kategori    : " + kategori);
        halaman.tampilHalaman();
    }
}
