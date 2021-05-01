package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
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

    private List<Car> getData() {
        List<Car> cars = new ArrayList<>();
        Car car;

        for(int i=0; i<9; i++) {
            car = new Car();
            car.setName("Ford Mondeo");
            car.setPrice(50);
            car.setImgSrc("/masini/ford-mondeo.png");
            cars.add(car);
        }

        return cars;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cars.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < cars.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));

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

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch(IOException e){
                e.printStackTrace();
        }
    }
}