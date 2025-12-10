import ModulOOP.Anggota;
import ModulOOP.Buku;
import ModulOOP.Petugas;
import ModulOOP.Transaksi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Transaksi transaksi = new Transaksi();

        Buku buku = null;
        Anggota anggota = null;
        Petugas petugas = null;

        int pilih;

        do {
            System.out.println("\n===== MENU PERPUSTAKAAN =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Tambah Petugas");
            System.out.println("4. Lihat Buku");
            System.out.println("5. Lihat Anggota");
            System.out.println("6. Lihat Petugas");
            System.out.println("7. Pinjam Buku");
            System.out.println("8. Kembalikan Buku");
            System.out.println("9. Tampil Riwayat Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {

                case 1:
                    System.out.print("ID Buku : ");
                    String idB = sc.nextLine();
                    System.out.print("Judul   : ");
                    String judul = sc.nextLine();
                    buku = new Buku(idB, judul);
                    System.out.println("Buku disimpan!");
                    break;

                case 2:
                    System.out.print("ID Anggota : ");
                    String idA = sc.nextLine();
                    System.out.print("Nama       : ");
                    String namaA = sc.nextLine();
                    System.out.print("Telepon    : ");
                    String telA = sc.nextLine();
                    System.out.print("Jurusan    : ");
                    String jurA = sc.nextLine();
                    anggota = new Anggota(idA, namaA, telA, jurA);
                    System.out.println("Anggota disimpan!");
                    break;

                case 3:
                    System.out.print("ID Petugas : ");
                    String idP = sc.nextLine();
                    System.out.print("Nama       : ");
                    String namaP = sc.nextLine();
                    System.out.print("Telepon    : ");
                    String telP = sc.nextLine();
                    System.out.print("Shift      : ");
                    String shift = sc.nextLine();
                    petugas = new Petugas(idP, namaP, telP, shift);
                    System.out.println("Petugas disimpan!");
                    break;

                case 4:
                    System.out.println("\n--- DATA BUKU ---");
                    if (buku != null) buku.tampilkanInfo();
                    else System.out.println("Belum ada buku.");
                    break;

                case 5:
                    System.out.println("\n--- DATA ANGGOTA ---");
                    if (anggota != null) anggota.tampilkanInfo();
                    else System.out.println("Belum ada anggota.");
                    break;

                case 6:
                    System.out.println("\n--- DATA PETUGAS ---");
                    if (petugas != null) petugas.tampilkanInfo();
                    else System.out.println("Belum ada petugas.");
                    break;

                case 7:
                    if (petugas == null || anggota == null || buku == null) {
                        System.out.println("Data belum lengkap!");
                        break;
                    }

                    System.out.print("ID Transaksi : ");
                    String idTpinjam = sc.nextLine();

                    transaksi.pinjamBuku(idTpinjam, petugas, anggota, buku);
                    break;

                case 8:
                    if (petugas == null || anggota == null || buku == null) {
                        System.out.println("Data belum lengkap!");
                        break;
                    }

                    System.out.print("ID Transaksi : ");
                    String idTkembali = sc.nextLine();

                    transaksi.kembaliBuku(idTkembali);
                    break;

                case 9:
                    transaksi.tampilRiwayat();
                    break;

                case 0:
                    System.out.println("Program selesai...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilih != 0);
    }
}
