package Controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setTitle("2 color game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}