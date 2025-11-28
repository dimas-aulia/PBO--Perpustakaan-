public class PenggunaPerpustakaan {
    protected String idUser;
    protected String nama;
    protected String telepon;

    public PenggunaPerpustakaan(String idUser, String nama, String telepon) {
        this.idUser = idUser;
        this.nama = nama;
        this.telepon = telepon;
    }

    public void tampilkanInfo() {
        System.out.println(idUser + " | " + nama + " | " + telepon);
    }
}
