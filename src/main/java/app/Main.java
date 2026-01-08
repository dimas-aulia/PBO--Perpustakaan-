package app;

import database.TransaksiDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        TransaksiDatabase.createTable();

        Scene scene = new Scene(
                FXMLLoader.load(
                        getClass().getResource("/fxml/Login.fxml")
                )
        );

        stage.setTitle("Login Perpustakaan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
