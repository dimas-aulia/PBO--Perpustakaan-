public class Buku {
    private String idBuku;
    private String judul;

    public Buku(String idBuku, String judul) {
        this.idBuku = idBuku;
        this.judul = judul;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void tampilkanInfo() {
        System.out.println(idBuku + " | " + judul);
    }
}
