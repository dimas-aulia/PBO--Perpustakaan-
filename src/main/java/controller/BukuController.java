package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Buku;

import java.net.URL;
import java.util.ResourceBundle;

public class BukuController implements Initializable {

    // ===== INPUT =====
    @FXML private TextField id, nama, penulis, tahun, halaman;

    // ===== TABLE =====
    @FXML private TableView<Buku> tableBuku;
    @FXML private TableColumn<Buku, String> colId, colNama, colPenulis;
    @FXML private TableColumn<Buku, Integer> colTahun, colHalaman;

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));
        tampilBuku();
    }

    // ===== CRUD =====
    private void tampilBuku() {
        tableBuku.setItems(Buku.getAll());
    }

    @FXML
    private void handleSimpan() {
        Buku.insert(new Buku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText())
        ));
        tampilBuku();
    }

    @FXML
    private void handleHapus() {
        Buku.delete(id.getText());
        tampilBuku();
    }

    @FXML
    private void handleUbah() {
        Buku.update(new Buku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText())
        ));
        tampilBuku();
    }

    @FXML
    private void handleLihat() {
        tampilBuku();
    }
}
