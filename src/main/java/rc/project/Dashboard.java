package rc.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Button browseButton;

    @FXML
    private Button logo;

    @FXML
    private AnchorPane display;

    @FXML
    private AnchorPane changeMe;

    @FXML
    private AnchorPane stangaJos;

    @FXML
    private Label stangaLabel;

    public Label getStangaLabel() {
        return stangaLabel;
    }

    public void setStangaLabel(Label stangaLabel) {
        this.stangaLabel = stangaLabel;
    }

    @FXML
    public void loadBrowse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/Browse.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }

    @FXML
    public void loadStatus(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/HomePage.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }

    @FXML
    public void loadAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }

    @FXML
    public void loadHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/HomePage.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }
}
