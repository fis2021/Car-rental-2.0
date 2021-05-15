package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Status implements Initializable {
    @FXML
    private GridPane gridStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 15; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml/OrderStatus.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                gridStatus.add(anchorPane, column, row++);
                //width
                gridStatus.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridStatus.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridStatus.setMaxWidth(Region.USE_PREF_SIZE);

                //hight
                gridStatus.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridStatus.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridStatus.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
