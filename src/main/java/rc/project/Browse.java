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
        car.setName("Bmw 1964");
        car.setPrice(40);
        car.setImgSrc("masini/bmw2.png");
        car.setDetails("masina 2");
        car.setColor1("9C917C");
        car.setColor2("4F483F");
        cars.add(car);

        car = new Car();
        car.setName("Bmw Oldtimer 1502");
        car.setPrice(60);
        car.setImgSrc("masini/bmw1.png");
        car.setDetails("masina 1");
        car.setColor1("8B9692");
        car.setColor2("46844F");
        cars.add(car);

        car = new Car();
        car.setName("Mercedes");
        car.setPrice(40);
        car.setImgSrc("masini/mercedes1.png");
        car.setDetails("masina 3");
        car.setColor1("8896A0");
        car.setColor2("31444A");
        cars.add(car);

        car = new Car();
        car.setName("Chevrolet");
        car.setPrice(40);
        car.setImgSrc("masini/chevrolet1.png");
        car.setDetails("masina 3");
        car.setColor1("FFB291");
        car.setColor2("964439");
        cars.add(car);

        car = new Car();
        car.setName("4");
        car.setPrice(40);
        car.setImgSrc("masini/vw-4309412_640.png");
        car.setDetails("masina 3");
        car.setColor1("019F8E");
        car.setColor2("2A52A4");
        cars.add(car);

        car = new Car();
        car.setName("Mercedes 2");
        car.setPrice(40);
        car.setImgSrc("masini/mercedes2.png");
        car.setDetails("masina 3");
        car.setColor1("F9DD78");
        car.setColor2("FD6A03");
        cars.add(car);

        car = new Car();
        car.setName("1");
        car.setPrice(40);
        car.setImgSrc("masini/volkswagen-3754571_640.png");
        car.setDetails("masina 3");
        car.setColor1("A5FE83");
        car.setColor2("02A08E");
        cars.add(car);

        car = new Car();
        car.setName("Audi");
        car.setPrice(40);
        car.setImgSrc("masini/audi1.png");
        car.setDetails("masina 3");
        car.setColor1("692D78");
        car.setColor2("ED80EF");
        cars.add(car);

        car = new Car();
        car.setName("3");
        car.setPrice(40);
        car.setImgSrc("masini/italy-3540815_640.png");
        car.setDetails("masina 3");
        car.setColor1("F9D648");
        car.setColor2("356A03");
        cars.add(car);

        car = new Car();
        car.setName("2");
        car.setPrice(40);
        car.setImgSrc("masini/packard-one-twenty-4818885_640.png");
        car.setDetails("masina 3");
        car.setColor1("F8D717");
        car.setColor2("EBB419");
        cars.add(car);

        car = new Car();
        car.setName("5");
        car.setPrice(40);
        car.setImgSrc("masini/jeep-3874584_640.png");
        car.setDetails("masina 3");
        car.setColor1("B03D2A");
        car.setColor2("FF6A59");
        cars.add(car);

        car = new Car();
        car.setName("6");
        car.setPrice(40);
        car.setImgSrc("masini/bmw-500-4344066_640.png");
        car.setDetails("masina 3");
        car.setColor1("004A53");
        car.setColor2("1BA8FE");
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