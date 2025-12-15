# Projek Perpustakaan

DAFTAR  CLASS :
1. CLASS PENGGUNAPERPUSATKAAN
2. CLASS BUKU
3. CLASS ANGGOTA
4. CLASS PETUGAS
5. CLASS TRANSAKSI

TUGAS BERIKUTNYA YAITU:
1. Asosiasi (Association)

Status saat ini: Sudah diterapkan.

Penjelasan:

Class modul.Transaksi memiliki relasi asosiasi dengan modul.Anggota dan modul.Buku.

Artinya, modul.Transaksi “menggunakan” objek modul.Anggota dan modul.Buku untuk melakukan peminjaman.

Yang belum dibuat: Fungsi catatPeminjaman() di class modul.Petugas.

Contoh konsep:

modul.Petugas mencatat identitas anggota yang meminjam buku.

modul.Anggota hanya pasif menerima pencatatan, tidak mencatat data sendiri.


2. Asosiasi Dua Arah (Bidirectional Association)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru KartuAnggota dengan atribut idAnggota (String).

Contoh kasus:

Seorang calon anggota menyerahkan identitas ke petugas.

modul.Petugas memproses data dan memberikan kartu keanggotaan.

Relasi dua arah: anggota ↔ kartu, kartu ↔ petugas.


3. Dependensi (Dependency)

Status saat ini: Sudah diterapkan.

Penjelasan:

Class pencatat peminjaman (modul.Transaksi) tergantung pada objek modul.Anggota dan modul.Buku.

Jika modul.Anggota atau modul.Buku berubah, modul.Transaksi akan terpengaruh.

Contoh implementasi:

Method void pinjamBuku(String idT, modul.Anggota a, modul.Buku b).

Yang perlu dibuat: 
Class transaksi menambahkan attribut tanggal pinjam (int)


4. Agregasi (Aggregation)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru DaftarPustaka dengan atribut judulReferensi (String).

Penjelasan:

modul.Buku memiliki daftar pustaka, tetapi daftar pustaka tidak wajib.

Jika buku dihapus, daftar pustaka masih bisa ada secara independen.

Contoh kasus:

modul.Buku memiliki pengarang dan daftar pustaka, tapi buku tetap disebut buku walau daftar pustaka kosong.


5. Komposisi (Composition)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru Halaman dengan atribut:

nomorHalaman (int)

isiHalaman (String)

Penjelasan:

modul.Buku selalu memiliki setidaknya satu halaman.

Jika buku dihapus, seluruh halaman ikut terhapus.

Contoh kasus:

Sebuah buku sudah pasti memiliki halaman isi sekurang–kurangnya satu.
