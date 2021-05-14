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

    private List<Car> getData() {
        List<Car> cars = new ArrayList<>();
        Car car;

        car = new Car();
        car.setName("BMW 1500");
        car.setPrice(170);
        car.setImgSrc("masini/bmw2.png");
        car.setDetails("- Made in 1962\n- 5 seats");
        car.setColor1("9C917C");
        car.setColor2("4F483F");
        cars.add(car);

        car = new Car();
        car.setName("BMW Oldtimer 1502");
        car.setPrice(150);
        car.setImgSrc("masini/bmw1.png");
        car.setDetails("- Made in 1983\n- 5 seats");
        car.setColor1("8B9692");
        car.setColor2("46844F");
        cars.add(car);

        car = new Car();
        car.setName("Mercedes Benz 300se");
        car.setPrice(180);
        car.setImgSrc("masini/mercedes1.png");
        car.setDetails("- Made in 1978\n- 2 seats");
        car.setColor1("8896A0");
        car.setColor2("31444A");
        cars.add(car);

        car = new Car();
        car.setName("Chevrolet Bel Air");
        car.setPrice(210);
        car.setImgSrc("masini/chevrolet1.png");
        car.setDetails("- Series 2400 C\n- Made in 1978\n- 5 seats");
        car.setColor1("FFB291");
        car.setColor2("964439");
        cars.add(car);

        car = new Car();
        car.setName("Volkswagen Bus T1");
        car.setPrice(230);
        car.setImgSrc("masini/vw-4309412_640.png");
        car.setDetails("- Designed for the perfect\n  camping experience\n- 9 seats");
        car.setColor1("019F8E");
        car.setColor2("2A52A4");
        cars.add(car);

        car = new Car();
        car.setName("Mercedes Benz Dash-8");
        car.setPrice(145);
        car.setImgSrc("masini/mercedes-benz-3168571_1280(1).png");
        car.setDetails("- Made in 1989\n- 2 seats");
        car.setColor1("F9DD78");
        car.setColor2("FD6A03");
        cars.add(car);

        car = new Car();
        car.setName("Volkswagen Karmann Ghia");
        car.setPrice(170);
        car.setImgSrc("masini/volkswagen-3754571_640.png");
        car.setDetails("-Made in 1969\n- Isolated or convertible\n- 2 seats");
        car.setColor1("A5FE83");
        car.setColor2("02A08E");
        cars.add(car);

        car = new Car();
        car.setName("Audi Austin 7");
        car.setPrice(120);
        car.setImgSrc("masini/audi1.png");
        car.setDetails("- Manufactured in 1934\n- 1 seat");
        car.setColor1("692D78");
        car.setColor2("ED80EF");
        cars.add(car);

        car = new Car();
        car.setName("Opel Blitz");
        car.setPrice(195);
        car.setImgSrc("masini/opel-blitz-5301880_1280.png");
        car.setDetails("- Made in 1969\n- 6 seats");
        car.setColor1("F9D648");
        car.setColor2("356A03");
        cars.add(car);

        car = new Car();
        car.setName("Packard One-Twenty");
        car.setPrice(180);
        car.setImgSrc("masini/packard-one-twenty-4818885_640.png");
        car.setDetails("- Ony manufactured during \n  the 1930's and 1940's\n- 5 seats");
        car.setColor1("F8D717");
        car.setColor2("EBB419");
        cars.add(car);

        car = new Car();
        car.setName("Jeep All Terrain Vehicle");
        car.setPrice(190);
        car.setImgSrc("masini/jeep-3874584_640.png");
        car.setDetails("- Made for the military\n- Isolated or convertible\n- 3 seats");
        car.setColor1("B03D2A");
        car.setColor2("FF6A59");
        cars.add(car);

        car = new Car();
        car.setName("BMW 500 Motorcyle");
        car.setPrice(120);
        car.setImgSrc("masini/bmw-500-4344066_640.png");
        car.setDetails("- Vintage motorcycle with isolated sidecar.\n- Made in 1965\n- For 2 persons only.");
        car.setColor1("004A53");
        car.setColor2("1BA8FE");
        cars.add(car);

        return cars;
    }

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