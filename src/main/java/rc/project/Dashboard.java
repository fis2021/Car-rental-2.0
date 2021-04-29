package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Dashboard  {

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Button login;

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane display;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private AnchorPane changeMe;

    @FXML
    public void loadLogin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("LoginMain.fxml")));
        changeMe.getChildren().setAll(pane);
    }

    @FXML
    public void loadBrowse(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("../resources/Browse.fxml")));
        changeMe.getChildren().setAll(pane);
    }

    @FXML
        public void loadStatus(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("../resources/Status.fxml")));
            changeMe.getChildren().setAll(pane);
     }
}
