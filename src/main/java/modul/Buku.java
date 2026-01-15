package modul;

public class Buku {
    private String id;
    private String nama;
    private String penulis;
    private int tahun;
    private Halaman halaman;   // KOMPOSISI hngan ubutidak bisa berdiri sendri
    private String status;

    public Buku(String id, String nama, String penulis, int tahun, int jumlahHalaman, String status) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.halaman = new Halaman(jumlahHalaman); //  dibuat di dalam Buku
        this.status = status;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }

    // 👉 Tetap kompatibel dengan Database
    public int getHalaman() {
        return halaman.getJumlahHalaman();
    }

    public String getStatus() { return status; }
}
