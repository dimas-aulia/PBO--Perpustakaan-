package controller;

import database.AnggotaDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Anggota;

public class AnggotaController {


    @FXML private TextField idUser;
    @FXML private TextField nama;
    @FXML private TextField telepon;
    @FXML private TextField jurusan;
    @FXML private TextField kelas;


    @FXML private TableView<Anggota> tableAnggota;
    @FXML private TableColumn<Anggota, String> colIdUser;
    @FXML private TableColumn<Anggota, String> colNama;
    @FXML private TableColumn<Anggota, String> colTelepon;
    @FXML private TableColumn<Anggota, String> colJurusan;
    @FXML private TableColumn<Anggota, String> colKelas;

    @FXML
    public void initialize() {
        //Menghubungkan kolom tabel dengan properti Buku
        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colJurusan.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        colKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));

        loadData();

        tableAnggota.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idUser.setText(newVal.getIdUser());
                nama.setText(newVal.getNama());
                telepon.setText(newVal.getTelepon());
                jurusan.setText(newVal.getJurusan());
                kelas.setText(newVal.getKelas());
            }
        });
    }

    private void loadData() {
        tableAnggota.setItems(AnggotaDatabase.getAll());
    }

    @FXML
    private void handleSimpan() {
        if (idUser.getText().isEmpty() || nama.getText().isEmpty()) {
            showAlert("Input Kosong", "ID dan Nama wajib diisi!");
            return;
        }

        Anggota a = new Anggota(idUser.getText(), nama.getText(), telepon.getText(), jurusan.getText(), kelas.getText());
        AnggotaDatabase.insert(a);
        loadData();
        clearFields();
    }

    @FXML
    private void handleUbah() {
        if (idUser.getText().isEmpty()) return;

        Anggota a = new Anggota(idUser.getText(), nama.getText(), telepon.getText(), jurusan.getText(), kelas.getText());
        AnggotaDatabase.update(a);
        loadData();
        clearFields();
    }

    @FXML
    private void handleHapus() {
        Anggota selected = tableAnggota.getSelectionModel().getSelectedItem();
        if (selected != null) {
            AnggotaDatabase.delete(selected.getIdUser());
            loadData();
            clearFields();
        } else {
            showAlert("Peringatan", "Pilih data di tabel yang ingin dihapus!");
        }
    }

    private void clearFields() {
        idUser.clear();
        nama.clear();
        telepon.clear();
        jurusan.clear();
        kelas.clear();
        idUser.setEditable(true);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}