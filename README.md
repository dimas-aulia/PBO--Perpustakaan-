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

Class Transaksi memiliki relasi asosiasi dengan Anggota dan Buku.

Artinya, Transaksi “menggunakan” objek Anggota dan Buku untuk melakukan peminjaman.

Yang belum dibuat: Fungsi catatPeminjaman() di class Petugas.

Contoh konsep:

Petugas mencatat identitas anggota yang meminjam buku.

Anggota hanya pasif menerima pencatatan, tidak mencatat data sendiri.


2. Asosiasi Dua Arah (Bidirectional Association)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru KartuAnggota dengan atribut idAnggota (String).

Contoh kasus:

Seorang calon anggota menyerahkan identitas ke petugas.

Petugas memproses data dan memberikan kartu keanggotaan.

Relasi dua arah: anggota ↔ kartu, kartu ↔ petugas.


3. Dependensi (Dependency)

Status saat ini: Sudah diterapkan.

Penjelasan:

Class pencatat peminjaman (Transaksi) tergantung pada objek Anggota dan Buku.

Jika Anggota atau Buku berubah, Transaksi akan terpengaruh.

Contoh implementasi:

Method void pinjamBuku(String idT, Anggota a, Buku b).

Yang perlu dibuat: 
Class transaksi menambahkan attribut tanggal pinjam (int)


4. Agregasi (Aggregation)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru DaftarPustaka dengan atribut judulReferensi (String).

Penjelasan:

Buku memiliki daftar pustaka, tetapi daftar pustaka tidak wajib.

Jika buku dihapus, daftar pustaka masih bisa ada secara independen.

Contoh kasus:

Buku memiliki pengarang dan daftar pustaka, tapi buku tetap disebut buku walau daftar pustaka kosong.


5. Komposisi (Composition)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru Halaman dengan atribut:

nomorHalaman (int)

isiHalaman (String)

Penjelasan:

Buku selalu memiliki setidaknya satu halaman.

Jika buku dihapus, seluruh halaman ikut terhapus.

Contoh kasus:

Sebuah buku sudah pasti memiliki halaman isi sekurang–kurangnya satu.
