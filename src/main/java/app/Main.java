package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modul.Buku;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Buku.createTable();
        Buku.seedData();


        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/FormBuku.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("CRUD Buku");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}