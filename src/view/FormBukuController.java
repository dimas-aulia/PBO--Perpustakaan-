package view;

import controller.BukuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Buku;

public class FormBukuController {

    @FXML private TextField id, nama, penulis, tahun, halaman;
    @FXML private TableView<Buku> tableBuku;
    @FXML private TableColumn<Buku, String> colId, colNama, colPenulis;
    @FXML private TableColumn<Buku, Integer> colTahun, colHalaman;

    BukuController controller = new BukuController();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));
        controller.tampilBuku(tableBuku);
    }

    @FXML
    private void handleSimpan() {
        controller.simpanBuku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText())
        );
        controller.tampilBuku(tableBuku);
    }

    @FXML
    private void handleHapus() {
        controller.hapusBuku(id.getText());
        controller.tampilBuku(tableBuku);
    }

    @FXML
    private void handleUbah() {
        controller.updateBuku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText())
        );
        controller.tampilBuku(tableBuku);
    }

    @FXML
    private void handleLihat() {
        controller.tampilBuku(tableBuku);
    }
}
