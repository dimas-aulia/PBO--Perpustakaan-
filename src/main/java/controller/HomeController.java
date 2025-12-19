package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class HomeController {

    @FXML
    private StackPane contentArea;

    private void loadPage(String fxml) {
        try {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(
                    FXMLLoader.load(getClass().getResource("/fxml/" + fxml))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBuku() {
        loadPage("Buku.fxml");
    }

    public void openAnggota() {
        loadPage("Anggota.fxml");
    }

    public void openPetugas() {
        loadPage("Petugas.fxml");
    }

    public void openTransaksi() {
        loadPage("Transaksi.fxml");
    }
}
