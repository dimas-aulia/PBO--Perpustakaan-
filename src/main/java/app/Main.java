package app;

import database.BukuDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BukuDatabase.createTable();
        BukuDatabase.seedData();

        Scene scene = new Scene(
                FXMLLoader.load(
                        getClass().getResource("/fxml/Login.fxml")
                )
        );

        // CSS global
        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm()
        );

        stage.setTitle("Login Perpustakaan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
