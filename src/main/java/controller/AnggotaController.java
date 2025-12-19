package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AnggotaController {

    @FXML
    private void handleSimpan() {
        alert("Simpan");
    }

    @FXML
    private void handleHapus() {
        alert("Hapus");
    }

    @FXML
    private void handleUbah() {
        alert("Ubah");
    }

    @FXML
    private void handleLihat() {
        alert("Lihat");
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("Klik " + msg);
        a.show();
    }
}
