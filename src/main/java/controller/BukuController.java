package controller;

import database.BukuDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Buku;

import java.net.URL;
import java.util.ResourceBundle;

public class BukuController implements Initializable {

    @FXML private TextField id, nama, penulis, tahun, halaman,status;
    @FXML private TableView<Buku> tableBuku;
    @FXML private TableColumn<Buku, String> colId, colNama, colPenulis,colStatus;
    @FXML private TableColumn<Buku, Integer> colTahun, colHalaman;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BukuDatabase.createTable();
        BukuDatabase.seedData();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 🔹 Listener klik baris
        tableBuku.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldData, newData) -> {
                    if (newData != null) {
                        id.setText(newData.getId());
                        nama.setText(newData.getNama());
                        penulis.setText(newData.getPenulis());
                        tahun.setText(String.valueOf(newData.getTahun()));
                        halaman.setText(String.valueOf(newData.getHalaman()));
                        status.setText(newData.getPenulis());
                    }
                }
        );

        tampilBuku();
    }


    private void tampilBuku() {
        tableBuku.setItems(BukuDatabase.getAll());
    }

    @FXML
    private void handleSimpan() {
        BukuDatabase.insert(new Buku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText()),
                status.getText()
        ));
        tampilBuku();
    }

    private void clearForm() {
        id.clear();
        nama.clear();
        penulis.clear();
        tahun.clear();
        halaman.clear();
        status.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void handleHapus() {
        Buku selected = tableBuku.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Peringatan", "Pilih data pada tabel terlebih dahulu!");
            return;
        }

        BukuDatabase.delete(selected.getId());
        tampilBuku();
        clearForm();
    }


    @FXML
    private void handleUbah() {
        BukuDatabase.update(new Buku(
                id.getText(),
                nama.getText(),
                penulis.getText(),
                Integer.parseInt(tahun.getText()),
                Integer.parseInt(halaman.getText()),
                status.getText()
        ));
        tampilBuku();
    }

    @FXML
    private void handleLihat() {
        tampilBuku();
    }
}