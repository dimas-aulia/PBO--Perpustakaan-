package modul;

public class Halaman {
    private int jumlahHalaman;

    public Halaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public void tampilHalaman() {
        System.out.println("Jumlah Halaman : " + jumlahHalaman);
    }
}