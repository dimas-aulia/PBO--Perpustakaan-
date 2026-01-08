package controller;
// testting
import database.AnggotaDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Anggota;

import java.net.URL;
import java.util.ResourceBundle;

public class AnggotaController implements Initializable {

    @FXML private TextField idUser, nama, telepon, jurusan,kelas;
    @FXML private TableView<Anggota> tableAnggota;
    @FXML private TableColumn<Anggota, String> colIdUser, colNama, colTelepon, colJurusan, colKelas ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnggotaDatabase.createTable();
        AnggotaDatabase.seedData();

        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colJurusan.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        colKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));


        tableAnggota.getSelectionModel().selectedItemProperty().addListener(
                (obs, o, a) -> {
                    if (a != null) {
                        idUser.setText(a.getIdUser());
                        nama.setText(a.getNama());
                        telepon.setText(a.getTelepon());
                        jurusan.setText(a.getJurusan());
                        kelas.setText(a.getKelas());

                    }
                }
        );

        tampilAnggota();
    }

    private void tampilAnggota() {
        tableAnggota.setItems(AnggotaDatabase.getAll());
    }

    @FXML
    private void handleSimpan() {
        AnggotaDatabase.insert(new Anggota(
                idUser.getText(),
                nama.getText(),
                telepon.getText(),
                jurusan.getText(),
                kelas.getText()
        ));

        tampilAnggota();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        Anggota a = tableAnggota.getSelectionModel().getSelectedItem();
        if (a != null) {
            AnggotaDatabase.delete(a.getIdUser());
            tampilAnggota();
            clearForm();
        }
    }

    @FXML
    private void handleUbah() {
        AnggotaDatabase.update(new Anggota(
                idUser.getText(),
                nama.getText(),
                telepon.getText(),
                jurusan.getText(),
                kelas.getText()
        ));

        tampilAnggota();
        clearForm();
    }

    private void clearForm() {
        idUser.clear();
        nama.clear();
        telepon.clear();
        jurusan.clear();
        kelas.clear();

    }
}
