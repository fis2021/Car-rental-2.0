package rc.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

public class App extends Application {
    public static final String CURRENCY = "ron";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();

        Scene scene_main = new Scene(content, 1080, 720);
       // primaryStage.();
        primaryStage.setScene(scene_main);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}