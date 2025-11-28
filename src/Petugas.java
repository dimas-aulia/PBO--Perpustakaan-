public class Petugas extends PenggunaPerpustakaan {

    private String shift;

    public Petugas(String idUser, String nama, String telepon, String shift) {
        super(idUser, nama, telepon);
        this.shift = shift;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Shift   : " + shift);
    }
}
