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

    private String getPasswordValue() {
        if (password.isVisible()) {
            return password.getText();
        } else {
            return passwordText.getText();
        }
    }

    @FXML
    private void handleLogin() {

        String user = username.getText();
        String pass = getPasswordValue();

        if (user.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Username dan Password wajib diisi!");
            alert.show();
            return;
        }

        // LOGIN MAHASISWA
        if (user.equals("mahasiswa") && pass.equals("mahasiswa")) {
            bukaHalaman("/fxml/DaftarBuku.fxml", "Home Mahasiswa");
        }

        // LOGIN PETUGAS
        else if (user.equals("petugas") && pass.equals("petugas")) {
            bukaHalaman("/fxml/HomePage.fxml", "Home Petugas");
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password salah!");
            alert.show();
        }
    }

    private void bukaHalaman(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) username.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (Exception e) {
            System.out.println("GAGAL LOAD: " + fxml);
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
