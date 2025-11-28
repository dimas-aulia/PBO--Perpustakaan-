import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//tws
        Scanner sc = new Scanner(System.in);
        Transaksi transaksi = new Transaksi();

        ArrayList<Buku> daftarBuku = new ArrayList<>();
        ArrayList<Anggota> daftarAnggota = new ArrayList<>();
        ArrayList<Petugas> daftarPetugas = new ArrayList<>();

        int pilih;

        do {
            System.out.println("\n===== MENU PERPUSTAKAAN =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Tambah Petugas");
            System.out.println("4. Lihat Buku");
            System.out.println("5. Lihat Anggota");
            System.out.println("6. Lihat Petugas");
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

                    daftarBuku.add(new Buku(idB, judul));
                    System.out.println("Buku berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.print("ID Anggota : ");
                    String idA = sc.nextLine();
                    System.out.print("Nama       : ");
                    String namaA = sc.nextLine();
                    System.out.print("Telepon    : ");
                    String telA = sc.nextLine();
                    System.out.print("Jurusan    : ");
                    String jurusanA = sc.nextLine();

                    daftarAnggota.add(new Anggota(idA, namaA, telA, jurusanA));
                    System.out.println("Anggota berhasil ditambahkan!");
                    break;

                case 3:
                    System.out.print("ID Petugas : ");
                    String idP = sc.nextLine();
                    System.out.print("Nama       : ");
                    String namaP = sc.nextLine();
                    System.out.print("Telepon    : ");
                    String telP = sc.nextLine();
                    System.out.print("Shift      : ");
                    String shiftP = sc.nextLine();

                    daftarPetugas.add(new Petugas(idP, namaP, telP, shiftP));
                    System.out.println("Petugas berhasil ditambahkan!");
                    break;

                case 4:
                    System.out.println("\n--- Daftar Buku ---");
                    for (Buku b : daftarBuku) b.tampilkanInfo();
                    break;

                case 5:
                    System.out.println("\n--- Daftar Anggota ---");
                    for (Anggota a : daftarAnggota) a.tampilkanInfo();
                    break;

                case 6:
                    System.out.println("\n--- Daftar Petugas ---");
                    for (Petugas p : daftarPetugas) p.tampilkanInfo();
                    break;

                case 7:
                    System.out.println("\n--- PINJAM BUKU ---");

                    System.out.print("ID Transaksi : ");
                    String idT = sc.nextLine();

                    System.out.print("ID Anggota   : ");
                    String idAng = sc.nextLine();

                    Anggota foundA = null;
                    for (Anggota a : daftarAnggota) {
                        if (a.idUser.equals(idAng)) {
                            foundA = a;
                            break;
                        }
                    }

                    if (foundA == null) {
                        System.out.println("Anggota tidak ditemukan!");
                        break;
                    }

                    System.out.print("ID Buku : ");
                    String idBuku = sc.nextLine();

                    Buku foundB = null;
                    for (Buku b : daftarBuku) {
                        if (b.getIdBuku().equals(idBuku)) {
                            foundB = b;
                            break;
                        }
                    }

                    if (foundB == null) {
                        System.out.println("Buku tidak ditemukan!");
                        break;
                    }

                    transaksi.pinjamBuku(idT, foundA, foundB);
                    break;

                case 0:
                    System.out.println("Keluar program...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilih != 0);

    }
}
