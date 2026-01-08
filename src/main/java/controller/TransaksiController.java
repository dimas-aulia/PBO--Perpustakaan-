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
        dpTransaksi.setValue(LocalDate.now()); // Default hari ini

        colUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        colPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        colDenda.setCellValueFactory(new PropertyValueFactory<>("denda"));

        loadData();

        // Auto-search anggota & buku
        txtIdUser.textProperty().addListener((o, old, v) -> {
            AnggotaDatabase.getAll().stream().filter(a -> a.getIdUser().equals(v)).findFirst()
                    .ifPresentOrElse(a -> txtNama.setText(a.getNama()), () -> txtNama.clear());
        });
        txtIdBuku.textProperty().addListener((o, old, v) -> {
            BukuDatabase.getAll().stream().filter(b -> b.getId().equals(v)).findFirst()
                    .ifPresentOrElse(b -> txtJudul.setText(b.getNama()), () -> txtJudul.clear());
        });
    }

    @FXML
    private void handlePinjam() {
        if (txtIdUser.getText().isEmpty() || txtIdBuku.getText().isEmpty()) return;
        boolean sukses = TransaksiDatabase.pinjamBuku(txtIdUser.getText(), txtNama.getText(),
                txtIdBuku.getText(), txtJudul.getText(), dpTransaksi.getValue());
        if (!sukses) {
            new Alert(Alert.AlertType.WARNING, "Gagal! User masih meminjam buku yang sama.").show();
        }
        loadData();
    }

    @FXML
    private void handleKembali() {
        Transaksi s = tableTransaksi.getSelectionModel().getSelectedItem();
        if (s != null && s.getTanggalKembali() == null) {
            TransaksiDatabase.kembalikanBuku(s.getIdTransaksi(), s.getIdBuku(), s.getTanggalPinjam(), dpTransaksi.getValue());
            loadData();
        }
    }

    @FXML
    private void handleHapus() {
        // Mendapatkan baris yang dipilih di tabel
        Transaksi selected = tableTransaksi.getSelectionModel().getSelectedItem();

        if (selected != null) {
            // Fitur Tambahan: Dialog Konfirmasi
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Hapus");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin ingin menghapus data transaksi ini?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                TransaksiDatabase.hapus(selected.getIdTransaksi());
                loadData(); // Refresh tabel setelah hapus
            }
        } else {
            // Peringatan jika belum ada data yang dipilih
            Alert warn = new Alert(Alert.AlertType.WARNING, "Silakan pilih data di tabel terlebih dahulu!");
            warn.show();
        }
    }

    private void loadData() { tableTransaksi.setItems(TransaksiDatabase.getAll()); }
}