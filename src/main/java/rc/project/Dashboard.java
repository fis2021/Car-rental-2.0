package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

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
    private GridPane gridPaneHome;

    private List<Car> cars = new ArrayList<>();

    private List<Car> getDataHome() {
        List<Car> cars = new ArrayList<>();
        Car car;

        car = new Car();
        car.setName("Mercedes");
        car.setPrice(40);
        car.setImgSrc("masini/mercedes1.png");
        car.setDetails("masina 3");
        car.setColor1("8896A0");
        car.setColor2("31444A");
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
        cars.addAll(getDataHome());
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < 3; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml/Item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                Item item = fxmlLoader.getController();
                item.setData(cars.get(i));

                gridPaneHome.add(anchorPane, column++, row);
                //width
                gridPaneHome.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneHome.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneHome.setMaxWidth(Region.USE_PREF_SIZE);

                //hight
                gridPaneHome.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneHome.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneHome.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(11));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
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
        loader.setLocation(getClass().getResource("fxml/Status.fxml"));
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
    public void loadContact(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/Contact.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }

    @FXML
    public void loadHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/Dashboard.fxml"));
        Parent content = loader.load();
        dashboardPane.getChildren().setAll(content);
    }

}
