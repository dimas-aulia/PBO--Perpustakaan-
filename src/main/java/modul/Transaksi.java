package modul;

import java.time.LocalDate;

public class Transaksi {

    private int idTransaksi;

    // 🔹 AGREGASI
    private Anggota anggota;
    private Buku buku;

    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private int denda;

    // ASOSIASI
    private Petugas petugas;

    public Transaksi(int idTransaksi, Anggota anggota, Buku buku,
                     LocalDate tanggalPinjam, LocalDate tanggalKembali, int denda) {

        this.idTransaksi = idTransaksi;
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.denda = denda;
    }

    // =====================
    // GETTER DATABASE
    // =====================
    public int getIdTransaksi() { return idTransaksi; }

    public String getIdUser() {
        return anggota.getIdUser(); // dari superclass
    }

    public String getNamaAnggota() {
        return anggota.getNama(); // dari superclass
    }

    public String getIdBuku() {
        return buku.getId();
    }

    public String getJudulBuku() {
        return buku.getNama();
    }

    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public int getDenda() { return denda; }

    // =====================
    // GETTER OOP
    // =====================
    public Anggota getAnggota() { return anggota; }
    public Buku getBuku() { return buku; }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

}
