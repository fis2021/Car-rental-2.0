package rc.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private Car car;

    public void setData(Car car) {
        this.car = car;
        itemText.setText(car.getName());
        itemPrice.setText(car.getPrice() + " " + App.CURRENCY + " / day");
        Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
        itemPhoto.setImage(image);
    }

}
