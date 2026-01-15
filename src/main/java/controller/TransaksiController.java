package controller;

import database.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.*;

import java.time.LocalDate;

public class TransaksiController {

    @FXML private TextField txtIdUser, txtNama, txtIdBuku, txtJudul;
    @FXML private DatePicker dpTransaksi;

    @FXML private TableView<Transaksi> tableTransaksi;
    @FXML private TableColumn<Transaksi, String> colUser, colNama, colBuku, colJudul;
    @FXML private TableColumn<Transaksi, LocalDate> colPinjam, colKembali;
    @FXML private TableColumn<Transaksi, Integer> colDenda;

    @FXML
    public void initialize() {

        TransaksiDatabase.createTable();
        dpTransaksi.setValue(LocalDate.now());

        // =====================
        // TABLE BINDING
        // =====================
        colUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        colPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        colDenda.setCellValueFactory(new PropertyValueFactory<>("denda"));

        loadData();

        // =====================
        // AUTO FILL DATA
        // =====================
        txtIdUser.textProperty().addListener((o, old, v) -> {
            AnggotaDatabase.getAll().stream()
                    .filter(a -> a.getIdUser().equals(v))
                    .findFirst()
                    .ifPresentOrElse(
                            a -> txtNama.setText(a.getNama()),
                            () -> txtNama.clear()
                    );
        });

        txtIdBuku.textProperty().addListener((o, old, v) -> {
            BukuDatabase.getAll().stream()
                    .filter(b -> b.getId().equals(v))
                    .findFirst()
                    .ifPresentOrElse(
                            b -> txtJudul.setText(b.getNama()),
                            () -> txtJudul.clear()
                    );
        });
    }

    // =====================
    // PINJAM BUKU (OOP)
    // =====================
    @FXML
    private void handlePinjam() {

        if (txtIdUser.getText().isEmpty() || txtIdBuku.getText().isEmpty()) return;

        // Ambil objek anggota
        Anggota anggota = AnggotaDatabase.getAll().stream()
                .filter(a -> a.getIdUser().equals(txtIdUser.getText()))
                .findFirst()
                .orElse(null);

        // Ambil objek buku
        Buku buku = BukuDatabase.getAll().stream()
                .filter(b -> b.getId().equals(txtIdBuku.getText()))
                .findFirst()
                .orElse(null);

        if (anggota == null || buku == null) {
            new Alert(Alert.AlertType.WARNING, "Data anggota atau buku tidak ditemukan!").show();
            return;
        }

        // Buat objek transaksi (AGREGASI)
        Transaksi t = new Transaksi(
                0,
                anggota,
                buku,
                dpTransaksi.getValue(),
                null,
                0
        );

        boolean sukses = TransaksiDatabase.pinjamBuku(t);

        if (!sukses) {
            new Alert(Alert.AlertType.WARNING,
                    "Gagal! Anggota masih meminjam buku yang sama.").show();
        }

        loadData();
    }

    // =====================
    // KEMBALIKAN BUKU
    // =====================
    @FXML
    private void handleKembali() {

        Transaksi t = tableTransaksi.getSelectionModel().getSelectedItem();

        if (t != null && t.getTanggalKembali() == null) {
            TransaksiDatabase.kembalikanBuku(
                    t.getIdTransaksi(),
                    t.getIdBuku(),
                    t.getTanggalPinjam(),
                    dpTransaksi.getValue()
            );
            loadData();
        }
    }

    // =====================
    // HAPUS TRANSAKSI
    // =====================
    @FXML
    private void handleHapus() {

        Transaksi selected = tableTransaksi.getSelectionModel().getSelectedItem();

        if (selected != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Hapus");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin ingin menghapus data transaksi ini?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                TransaksiDatabase.hapus(selected.getIdTransaksi());
                loadData();
            }

        } else {
            new Alert(Alert.AlertType.WARNING,
                    "Silakan pilih data di tabel terlebih dahulu!").show();
        }
    }

    private void loadData() {
        tableTransaksi.setItems(TransaksiDatabase.getAll());
    }
}
