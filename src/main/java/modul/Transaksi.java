package modul;

import java.time.LocalDate;

public class Transaksi {
    private int idTransaksi;
    private String idUser;
    private String namaAnggota;
    private String idBuku;
    private String judulBuku;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    public Transaksi(int idTransaksi, String idUser, String namaAnggota, String idBuku, String judulBuku, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.idTransaksi = idTransaksi;
        this.idUser = idUser;
        this.namaAnggota = namaAnggota;
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    // Getters
    public int getIdTransaksi() { return idTransaksi; }
    public String getIdUser() { return idUser; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getIdBuku() { return idBuku; }
    public String getJudulBuku() { return judulBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
}