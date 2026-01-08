package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

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

    // Method untuk Logout
    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            // 1. Tentukan lokasi file Login.fxml
            URL loginUrl = getClass().getResource("/fxml/Login.fxml");

            if (loginUrl == null) {
                throw new RuntimeException("File Login.fxml tidak ditemukan di /fxml/");
            }

            // 2. Load root baru
            Parent root = FXMLLoader.load(loginUrl);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 3. Pindah ke Scene Login
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.centerOnScreen();
            stage.show();

        } catch (Exception e) {
            System.err.println("Error saat pindah ke halaman login: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
