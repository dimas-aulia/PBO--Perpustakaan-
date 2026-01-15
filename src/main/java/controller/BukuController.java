package controller;

import database.BukuDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Buku;

public class BukuController {

    @FXML private TextField id;
    @FXML private TextField nama;
    @FXML private TextField penulis;
    @FXML private TextField tahun;
    @FXML private TextField halaman;

    @FXML private TableView<Buku> tableBuku;
    @FXML private TableColumn<Buku, String> colId, colNama, colPenulis, colStatus;
    @FXML private TableColumn<Buku, Integer> colTahun, colHalaman;

    @FXML
    public void initialize() {
        BukuDatabase.createTable();
        //mencocokan fxid dan fxml
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();

        tableBuku.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) {
                id.setText(val.getId());
                nama.setText(val.getNama());
                penulis.setText(val.getPenulis());
                tahun.setText(String.valueOf(val.getTahun()));
                halaman.setText(String.valueOf(val.getHalaman()));
            }
        });
    }

    private void loadData() {
        tableBuku.setItems(BukuDatabase.getAll());
    }

    @FXML
    private void handleSimpan() {
        try {
            Buku b = new Buku(id.getText(), nama.getText(), penulis.getText(),
                    Integer.parseInt(tahun.getText()), Integer.parseInt(halaman.getText()), "TERSEDIA");
            BukuDatabase.insert(b);
            loadData();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Format angka salah atau ID duplikat!").show();
        }
    }

    @FXML
    private void handleUbah() {
        try {
            Buku b = new Buku(id.getText(), nama.getText(), penulis.getText(),
                    Integer.parseInt(tahun.getText()), Integer.parseInt(halaman.getText()), "TERSEDIA");
            BukuDatabase.update(b);
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHapus() {
        Buku selected = tableBuku.getSelectionModel().getSelectedItem();
        if (selected != null) {
            BukuDatabase.delete(selected.getId());
            loadData();
            clearFields();
        }
    }

    private void clearFields() {
        id.clear(); nama.clear(); penulis.clear(); tahun.clear(); halaman.clear();
    }
}