package modul;

public class Anggota extends PenggunaPerpustakaan {

    private String jurusan;
    private String kelas; // TAMBAHAN

    public Anggota(String idUser, String nama, String telepon, String jurusan, String kelas) {
        super(idUser, nama, telepon);
        this.jurusan = jurusan;
        this.kelas = kelas;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Anggota Perpustakaan - Jurusan: " + jurusan);
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getKelas() {
        return kelas;
    }
}
