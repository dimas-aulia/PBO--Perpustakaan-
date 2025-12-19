package controller;

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

    // ===== DATA =====
    private final ObservableList<Petugas> listPetugas =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        shift.getItems().addAll("Pagi", "Siang", "Malam");

        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));

        tablePetugas.setItems(listPetugas);
    }

    // ===== BUTTON ACTION =====
    @FXML
    private void handleSimpan() {
        listPetugas.add(new Petugas(
                idUser.getText(),
                nama.getText(),
                telepon.getText(),
                shift.getValue()
        ));
        clearForm();
    }

    @FXML
    private void handleHapus() {
        listPetugas.removeIf(p -> p.getIdUser().equals(idUser.getText()));
        clearForm();
    }

    @FXML
    private void handleUbah() {
        for (Petugas p : listPetugas) {
            if (p.getIdUser().equals(idUser.getText())) {
                p.setNama(nama.getText());
                p.setTelepon(telepon.getText());
                p.setShift(shift.getValue());
                tablePetugas.refresh();
                break;
            }
        }
        clearForm();
    }

    @FXML
    private void handleLihat() {
        tablePetugas.refresh();
    }

    private void clearForm() {
        idUser.clear();
        nama.clear();
        telepon.clear();
        shift.setValue(null);
    }
}

