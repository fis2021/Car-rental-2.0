package rc.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dashboard.fxml"));
        Parent content = loader.load();
        primaryStage.setScene(new Scene(content, 1080, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}