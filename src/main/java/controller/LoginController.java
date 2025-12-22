package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField passwordText;

    @FXML
    private void handleLogin() {

        // 1️⃣ VALIDASI INPUT KOSONG
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Username dan Password wajib diisi!");
            alert.show();
            return;
        }

        // 2️⃣ LOGIN STATIS (DUMMY) → MAHASISWA
        if (username.getText().equals("mahasiswa")
                && password.getText().equals("mahasiswa")) {

            bukaHalaman("/fxml/MahasiswaView.fxml", "Home Mahasiswa");

        }

        // 3️⃣ LOGIN STATIS (DUMMY) → PETUGAS
        else if (username.getText().equals("petugas")
                && password.getText().equals("petugas")) {

            bukaHalaman("/fxml/HomePage.fxml", "Home Petugas");

        }

        // 4️⃣ LOGIN GAGAL
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password salah!");
            alert.show();
        }
    }

    // 🔧 METHOD BANTUAN (AGAR KODE RAPI)
    private void bukaHalaman(String fxml, String title) {
        try {
            Stage stage = (Stage) username.getScene().getWindow();

            Scene scene = new Scene(
                    FXMLLoader.load(getClass().getResource(fxml))
            );

            // CSS GLOBAL
            scene.getStylesheets().add(
                    getClass().getResource("/css/style.css").toExternalForm()
            );

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void togglePassword() {
        if (password.isVisible()) {
            passwordText.setText(password.getText());
            password.setVisible(false);
            password.setManaged(false);
            passwordText.setVisible(true);
            passwordText.setManaged(true);
        } else {
            password.setText(passwordText.getText());
            passwordText.setVisible(false);
            passwordText.setManaged(false);
            password.setVisible(true);
            password.setManaged(true);
        }
    }


}
