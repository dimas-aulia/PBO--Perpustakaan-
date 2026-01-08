package controller;

import database.AnggotaDatabase;
import database.BukuDatabase;
import database.TransaksiDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Anggota;
import modul.Buku;
import modul.Transaksi;

public class TransaksiController {
    @FXML private TextField txtIdUser, txtNama, txtIdBuku, txtJudul;
    @FXML private TableView<Transaksi> tableTransaksi;
    @FXML private TableColumn<Transaksi, String> colUser, colNama, colBuku, colJudul, colPinjam, colKembali;

    @FXML
    public void initialize() {
        TransaksiDatabase.createTable();

        // Mapping Kolom
        colUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        colPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));

        loadData();

        // Fitur Auto-search
        txtIdUser.textProperty().addListener((obs, oldVal, newVal) -> searchAnggota(newVal));
        txtIdBuku.textProperty().addListener((obs, oldVal, newVal) -> searchBuku(newVal));
    }

    private void searchAnggota(String id) {
        for (Anggota a : AnggotaDatabase.getAll()) {
            if (a.getIdUser().equalsIgnoreCase(id)) {
                txtNama.setText(a.getNama());
                return;
            }
        }
        txtNama.clear();
    }

    private void searchBuku(String id) {
        for (Buku b : BukuDatabase.getAll()) {
            if (b.getId().equalsIgnoreCase(id)) {
                txtJudul.setText(b.getNama());
                return;
            }
        }
        txtJudul.clear();
    }

    @FXML
    private void handlePinjam() {
        if (txtIdUser.getText().isEmpty() || txtIdBuku.getText().isEmpty()) return;

        boolean success = TransaksiDatabase.pinjamBuku(
                txtIdUser.getText(), txtNama.getText(),
                txtIdBuku.getText(), txtJudul.getText()
        );

        if (!success) {
            new Alert(Alert.AlertType.ERROR, "Gagal! User ini masih meminjam buku yang sama.").show();
        }
        loadData();
    }

    @FXML
    private void handleKembali() {
        Transaksi selected = tableTransaksi.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TransaksiDatabase.kembalikanBuku(selected.getIdTransaksi(), selected.getIdBuku());
            loadData();
        }
    }

    @FXML
    private void handleHapus() {
        Transaksi selected = tableTransaksi.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TransaksiDatabase.hapus(selected.getIdTransaksi());
            loadData();
        }
    }

    private void loadData() {
        tableTransaksi.setItems(TransaksiDatabase.getAll());
    }
}