package modul;

// Mewarisi dari abstract class PenggunaPerpustakaan
public class Petugas extends PenggunaPerpustakaan {

    private String shift;

    public Petugas(String idUser, String nama, String telepon, String shift) {
        // Mengirimkan data ke constructor superclass (PenggunaPerpustakaan)
        super(idUser, nama, telepon);
        this.shift = shift;
    }

    // Implementasi wajib dari method abstract induk
    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Petugas Perpustakaan - Shift: " + shift);
    }

    // Getter dan Setter khusus untuk Petugas
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    // Catatan: Getter idUser, nama, dan telepon sudah ada di class induk
}