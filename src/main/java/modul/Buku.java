package modul;

public class Buku {
    private String id;
    private String nama;
    private String penulis;
    private int tahun;
    private int halaman;
    private String status;

    public Buku(String id, String nama, String penulis, int tahun, int halaman, String status) {
        this.id = id;
        this.nama = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.halaman = halaman;
        this.status = status;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }
    public int getHalaman() { return halaman; }
    public String getStatus() { return status; }
}
