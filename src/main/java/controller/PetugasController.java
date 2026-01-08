package controller;

import database.PetugasDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Petugas;

import java.net.URL;
import java.util.ResourceBundle;

public class PetugasController implements Initializable {

    // ===== FORM =====
    @FXML private TextField idUser;
    @FXML private TextField nama;
    @FXML private TextField telepon;
    @FXML private ComboBox<String> shift;

    // ===== TABLE =====
    @FXML private TableView<Petugas> tablePetugas;
    @FXML private TableColumn<Petugas, String> colIdUser;
    @FXML private TableColumn<Petugas, String> colNama;
    @FXML private TableColumn<Petugas, String> colTelepon;
    @FXML private TableColumn<Petugas, String> colShift;

    private ObservableList<Petugas> listPetugas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PetugasDatabase.createTable();
        // ComboBox Shift
        shift.getItems().addAll("Pagi", "Siang", "Malam");

        // Mapping kolom tabel
        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));

        // Load data dari database
        loadData();

        // Klik tabel → isi form
        tablePetugas.setOnMouseClicked(e -> {
            Petugas p = tablePetugas.getSelectionModel().getSelectedItem();
            if (p != null) {
                idUser.setText(p.getIdUser());
                nama.setText(p.getNama());
                telepon.setText(p.getTelepon());
                shift.setValue(p.getShift());
            }
        });
    }

    // ===== LOAD DATA =====
    private void loadData() {
        listPetugas = FXCollections.observableArrayList(PetugasDatabase.getAll());
        tablePetugas.setItems(listPetugas);
    }

    // ===== SIMPAN =====
    @FXML
    private void handleSimpan() {
        if (validasi()) {
            Petugas p = new Petugas(
                    idUser.getText(),
                    nama.getText(),
                    telepon.getText(),
                    shift.getValue()
            );

            PetugasDatabase.insert(p);
            listPetugas.add(p);

            alert("Sukses", "Data petugas berhasil disimpan");
            clearForm();
        }
    }

    // ===== UBAH =====
    @FXML
    private void handleUbah() {
        if (validasi()) {
            Petugas p = new Petugas(
                    idUser.getText(),
                    nama.getText(),
                    telepon.getText(),
                    shift.getValue()
            );

            PetugasDatabase.update(p);
            loadData();

            alert("Sukses", "Data petugas berhasil diubah");
            clearForm();
        }
    }

    // ===== HAPUS =====
    @FXML
    private void handleHapus() {
        if (idUser.getText().isEmpty()) {
            alert("Peringatan", "Pilih data terlebih dahulu!");
            return;
        }

        PetugasDatabase.delete(idUser.getText());
        loadData();

        alert("Sukses", "Data petugas berhasil dihapus");
        clearForm();
    }

    // ===== CLEAR FORM =====
    private void clearForm() {
        idUser.clear();
        nama.clear();
        telepon.clear();
        shift.setValue(null);
        idUser.setDisable(false);
        tablePetugas.getSelectionModel().clearSelection();
    }

    // ===== VALIDASI =====
    private boolean validasi() {
        if (idUser.getText().isEmpty() ||
                nama.getText().isEmpty() ||
                telepon.getText().isEmpty() ||
                shift.getValue() == null) {

            alert("Peringatan", "Semua field wajib diisi!");
            return false;
        }
        return true;
    }

    // ===== ALERT =====
    private void alert(String judul, String pesan) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(judul);
        a.setHeaderText(null);
        a.setContentText(pesan);
        a.showAndWait();
    }
}
