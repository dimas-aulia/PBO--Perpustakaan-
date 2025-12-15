package controller;

import javafx.scene.control.TableView;
import model.Buku;

public class BukuController {

    public void simpanBuku(String id, String nama, String penulis, int tahun, int halaman) {
        Buku.insert(new Buku(id, nama, penulis, tahun, halaman));
    }

    public void hapusBuku(String id) {
        Buku.delete(id);
    }

    public void updateBuku(String id, String nama, String penulis, int tahun, int halaman) {
        Buku.update(new Buku(id, nama, penulis, tahun, halaman));
    }

    public void tampilBuku(TableView<Buku> table) {
        table.setItems(Buku.getAll());
    }
}
