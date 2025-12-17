package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField username;
    @FXML private PasswordField password;

    @FXML
    private void handleLogin() {

        // VALIDASI INPUT KOSONG
        if (username.getText().isEmpty() ||
                password.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Username dan Password wajib diisi!");
            alert.show();
            return;
        }

        // LOGIN STATIS
        if (username.getText().equals("mahasiswa") &&
                password.getText().equals("mahasiswa")) {

            try {
                Stage stage = (Stage) username.getScene().getWindow();
                Scene scene = new Scene(
                        FXMLLoader.load(
                                getClass().getResource("/fxml/FormPetugas.fxml")
                        )
                );
                stage.setScene(scene);
                stage.setTitle("Manajemen Petugas Perpustakaan");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password salah!");
            alert.show();
        }
    }
}
