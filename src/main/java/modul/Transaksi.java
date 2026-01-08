package modul;

import java.time.LocalDate;

public class Transaksi {
    private int idTransaksi;
    private String idUser, namaAnggota, idBuku, judulBuku;
    private LocalDate tanggalPinjam, tanggalKembali;
    private int denda;

    public Transaksi(int id, String user, String nama, String buku, String judul, LocalDate p, LocalDate k, int denda) {
        this.idTransaksi = id;
        this.idUser = user;
        this.namaAnggota = nama;
        this.idBuku = buku;
        this.judulBuku = judul;
        this.tanggalPinjam = p;
        this.tanggalKembali = k;
        this.denda = denda;
    }

    // Getters
    public int getIdTransaksi() { return idTransaksi; }
    public String getIdUser() { return idUser; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getIdBuku() { return idBuku; }
    public String getJudulBuku() { return judulBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public int getDenda() { return denda; }
}