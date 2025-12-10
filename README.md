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

Class ModulOOP.Transaksi memiliki relasi asosiasi dengan ModulOOP.Anggota dan ModulOOP.Buku.

Artinya, ModulOOP.Transaksi “menggunakan” objek ModulOOP.Anggota dan ModulOOP.Buku untuk melakukan peminjaman.

Yang belum dibuat: Fungsi catatPeminjaman() di class ModulOOP.Petugas.

Contoh konsep:

ModulOOP.Petugas mencatat identitas anggota yang meminjam buku.

ModulOOP.Anggota hanya pasif menerima pencatatan, tidak mencatat data sendiri.


2. Asosiasi Dua Arah (Bidirectional Association)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru KartuAnggota dengan atribut idAnggota (String).

Contoh kasus:

Seorang calon anggota menyerahkan identitas ke petugas.

ModulOOP.Petugas memproses data dan memberikan kartu keanggotaan.

Relasi dua arah: anggota ↔ kartu, kartu ↔ petugas.


3. Dependensi (Dependency)

Status saat ini: Sudah diterapkan.

Penjelasan:

Class pencatat peminjaman (ModulOOP.Transaksi) tergantung pada objek ModulOOP.Anggota dan ModulOOP.Buku.

Jika ModulOOP.Anggota atau ModulOOP.Buku berubah, ModulOOP.Transaksi akan terpengaruh.

Contoh implementasi:

Method void pinjamBuku(String idT, ModulOOP.Anggota a, ModulOOP.Buku b).

Yang perlu dibuat: 
Class transaksi menambahkan attribut tanggal pinjam (int)


4. Agregasi (Aggregation)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru DaftarPustaka dengan atribut judulReferensi (String).

Penjelasan:

ModulOOP.Buku memiliki daftar pustaka, tetapi daftar pustaka tidak wajib.

Jika buku dihapus, daftar pustaka masih bisa ada secara independen.

Contoh kasus:

ModulOOP.Buku memiliki pengarang dan daftar pustaka, tapi buku tetap disebut buku walau daftar pustaka kosong.


5. Komposisi (Composition)

Status saat ini: Belum diterapkan.

Yang perlu dibuat:

Class baru Halaman dengan atribut:

nomorHalaman (int)

isiHalaman (String)

Penjelasan:

ModulOOP.Buku selalu memiliki setidaknya satu halaman.

Jika buku dihapus, seluruh halaman ikut terhapus.

Contoh kasus:

Sebuah buku sudah pasti memiliki halaman isi sekurang–kurangnya satu.
