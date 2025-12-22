package modul;

public class Buku {

    private String id;
    private String nama;
    private String penulis;
    private int tahun;
    private Halaman halaman; // KOMPOSISI

    public Buku(String id, String nama, String penulis, int tahun, int jumlahHalaman) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.halaman = new Halaman(jumlahHalaman);
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }
    public int getHalaman() { return halaman.getJumlahHalaman(); }
}
