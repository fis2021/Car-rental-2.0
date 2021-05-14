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
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.BreakIterator;

public class Item {

    @FXML
    private Label itemText;

    @FXML
    private Label itemPrice;

    @FXML
    private ImageView itemPhoto;

    @FXML
    private Button itemDetails;

    @FXML
    private Button itemOrder;

    @FXML
    private AnchorPane itemAnchor;

    @FXML
    private Pane pane;

    @FXML
    private Label detailsLabel;

    private Car car;

    public void setData(Car car) {
        detailsLabel.setVisible(false);
        this.car = car;
        itemText.setText(car.getName());
        itemPrice.setText(car.getPrice() + " " + App.CURRENCY + " / day");
        detailsLabel.setText(car.getDetails());
        Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
        itemPhoto.setImage(image);
        pane.setStyle("-fx-background-color: radial-gradient(center 50% 50% , radius 60% ,#" + car.getColor1() +", #" + car.getColor2() +");");
    }

    private int ok=0;
    public void loadDetalii(ActionEvent event) throws IOException {
        if(ok==0) {
            this.itemPhoto.setVisible(false);
            this.itemOrder.setVisible(false);
            this.itemText.setVisible(false);
            this.itemPrice.setVisible(false);
            this.detailsLabel.setVisible(true);
            this.itemDetails.setText("Go back");
            ok=1;
        }
        else {
            this.itemPhoto.setVisible(true);
            this.itemDetails.setVisible(true);
            this.itemOrder.setVisible(true);
            this.itemText.setVisible(true);
            this.itemPrice.setVisible(true);
            this.detailsLabel.setVisible(false);
            this.itemDetails.setText("Details");
            ok=0;
        }

    }
}
