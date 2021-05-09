package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Browse implements Initializable {

    @FXML
    private HBox hBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    private List<Car> cars = new ArrayList<>();
    private Image image;

    private List<Car> getData() {
        List<Car> cars = new ArrayList<>();
        Car car;

        car = new Car();

        car.setName("Bmw Oldtimer 1502");
        car.setPrice(60);
        car.setImgSrc("masini/bmw1.png");
        car.setDetails("masina 1");
        cars.add(car);

        car = new Car();
        car.setName("Bmw 1964");
        car.setPrice(40);
        car.setImgSrc("masini/bmw2.png");
        car.setDetails("masina 2");
        cars.add(car);

        return cars;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cars.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < cars.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml/Item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                Item item = fxmlLoader.getController();
                item.setData(cars.get(i));

                if(column == 3) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                //width
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);

                //hight
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(11));
            }
        } catch(IOException e){
                e.printStackTrace();
        }
    }
}