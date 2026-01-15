package app;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    //membuat koneksi database dan bisa dipakai clas clas dtabase lain
    private static final String URL = "jdbc:sqlite:perpustakaan.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("Gagal koneksi: " + e.getMessage());
            return null;
        }
    }
}
