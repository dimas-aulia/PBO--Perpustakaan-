package modul;

// Mewarisi dari abstract class PenggunaPerpustakaan
public class Petugas extends PenggunaPerpustakaan {

    private String shift;

    public Petugas(String idUser, String nama, String telepon, String shift) {

        super(idUser, nama, telepon);
        this.shift = shift;
    }

    //polimorfisem, memliki lain method ,overiding mendfinisikan ulang dari suer class
    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Petugas Perpustakaan - Shift: " +  shift);
    }


    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


}