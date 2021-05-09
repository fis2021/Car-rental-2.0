package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Dashboard {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Button browseButton;

    @FXML
    private ImageView logo;

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
        loader.setLocation(getClass().getResource("fxml/deModificat.fxml"));
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

}
