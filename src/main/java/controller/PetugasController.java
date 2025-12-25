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

    @FXML private TextField idUser;
    @FXML private TextField nama;
    @FXML private TextField telepon;
    @FXML private ComboBox<String> shift;

    @FXML private TableView<Petugas> tablePetugas;
    @FXML private TableColumn<Petugas, String> colIdUser;
    @FXML private TableColumn<Petugas, String> colNama;
    @FXML private TableColumn<Petugas, String> colTelepon;
    @FXML private TableColumn<Petugas, String> colShift;

    private final ObservableList<Petugas> listPetugas =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        shift.getItems().addAll("Pagi", "Siang", "Malam");

        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));

        listPetugas.addAll(
                new Petugas("P001", "Andi", "0811111111", "Pagi"),
                new Petugas("P002", "Budi", "0822222222", "Siang"),
                new Petugas("P003", "Citra", "0833333333", "Malam"),
                new Petugas("P004", "Dewi", "0844444444", "Pagi"),
                new Petugas("P005", "Eko", "0855555555", "Siang"),
                new Petugas("P006", "Fajar", "0866666666", "Malam"),
                new Petugas("P007", "Gita", "0877777777", "Pagi"),
                new Petugas("P008", "Hadi", "0888888888", "Siang"),
                new Petugas("P009", "Indah", "0899999999", "Malam"),
                new Petugas("P010", "Joko", "0810000000", "Pagi")
        );

        tablePetugas.setItems(listPetugas);

        // klik tabel → isi form
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

    private void clearForm() {
        idUser.clear();
        nama.clear();
        telepon.clear();
        shift.setValue(null);
    }
}
