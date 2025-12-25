package modul;

public class Petugas {

    private String idUser;
    private String nama;
    private String telepon;
    private String shift;

    public Petugas(String idUser, String nama, String telepon, String shift) {
        this.idUser = idUser;
        this.nama = nama;
        this.telepon = telepon;
        this.shift = shift;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getShift() {
        return shift;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
