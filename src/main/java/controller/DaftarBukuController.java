package controller;

import database.DaftarBukuDatabase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import modul.Buku;

import java.net.URL;
import java.util.ResourceBundle;

public class DaftarBukuController implements Initializable {

    @FXML
    private HBox bukuContainer;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tampilkan semua buku saat startup
        renderBuku(DaftarBukuDatabase.getAll());
    }

    @FXML
    private void handleSearch() {
        String query = txtSearch.getText();

        if (query == null || query.trim().isEmpty()) {
            renderBuku(DaftarBukuDatabase.getAll());
            return;
        }

        ObservableList<Buku> hasilPencarian = DaftarBukuDatabase.search(query);

        if (hasilPencarian.isEmpty()) {
            // ALERT: Jika buku tidak ditemukan
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Buku dengan judul '" + query + "' tidak terdaftar dalam database.");
            alert.showAndWait();

            // Kembalikan ke daftar awal setelah alert ditutup
            renderBuku(DaftarBukuDatabase.getAll());
        } else {
            renderBuku(hasilPencarian);
        }
    }

    private void renderBuku(ObservableList<Buku> daftarBuku) {
        bukuContainer.getChildren().clear();

        for (Buku buku : daftarBuku) {
            // 1. Gambar Buku
            ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/buku.jpg")));
            img.setFitWidth(150);
            img.setFitHeight(200);
            img.setPreserveRatio(true);

            // 2. KODE BUKU (Dikembalikan)
            Label lblKode = new Label("KODE: " + buku.getId());
            lblKode.setStyle("-fx-font-weight: bold; -fx-text-fill: #00796B; -fx-font-size: 11px;");

            // 3. TEXT STATIS "Judul Buku"
            Label lblStatik = new Label("Judul Buku");
            lblStatik.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6; -fx-text-transform: uppercase;");

            // 4. JUDUL BUKU (Besar & Wrap)
            Text txtJudul = new Text(buku.getNama());
            txtJudul.setWrappingWidth(240);
            txtJudul.setTextAlignment(TextAlignment.CENTER);
            txtJudul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #2C3E50;");

            // 5. PENULIS
            Label lblPenulis = new Label("Penulis: " + buku.getPenulis());
            lblPenulis.setStyle("-fx-font-size: 13px; -fx-text-fill: #7F8C8D; -fx-font-style: italic;");

            // === STATUS ===
            Text txtStatus = new Text("Status: " + buku.getStatus());

            if ("Dipinjam".equalsIgnoreCase(buku.getStatus())) {
                txtStatus.setStyle("-fx-font-size: 12px; -fx-fill: red; -fx-font-weight: bold;");
            } else {
                txtStatus.setStyle("-fx-font-size: 12px; -fx-fill: green; -fx-font-weight: bold;");
            }


            // --- KONTINER KARTU ---
            VBox card = new VBox(10, img, lblKode, lblStatik, txtJudul, lblPenulis,txtStatus);

            card.setAlignment(Pos.TOP_CENTER);
            card.setPrefWidth(280);
            card.setPadding(new javafx.geometry.Insets(20));
            card.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 15;
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);
                -fx-border-color: #ecf0f1;
                -fx-border-width: 1;
                -fx-border-radius: 15;
            """);

            bukuContainer.getChildren().add(card);
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) bukuContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}