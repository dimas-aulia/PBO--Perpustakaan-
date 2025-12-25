package modul;

public class Anggota extends PenggunaPerpustakaan {

    private String jurusan;

    public Anggota(String idUser, String nama, String telepon, String jurusan) {
        super(idUser, nama, telepon);
        this.jurusan = jurusan;
    }

    public String getJurusan() {
        return jurusan;
    }
}
