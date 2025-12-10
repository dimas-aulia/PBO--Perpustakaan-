package ModulOOP;

public class PenggunaPerpustakaan {

    protected String idUser;
    protected String nama;
    protected String telepon;

    public PenggunaPerpustakaan(String idUser, String nama, String telepon) {
        this.idUser = idUser;
        this.nama = nama;
        this.telepon = telepon;
    }

    public String getIdUser() { return idUser; }
    public String getNama() { return nama; }
    public String getTelepon() { return telepon; }

    public void tampilkanInfo() {
        System.out.println(idUser + " | " + nama + " | " + telepon);
    }
}
