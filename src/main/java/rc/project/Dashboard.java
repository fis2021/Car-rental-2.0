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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        List<Car> cars = new ArrayList<>();
        Car car;
        int k = 0;

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from cars");
            while (rs.next() && k < 3) {


                    car = new Car();
                    car.setName(rs.getString("carname"));
                    car.setPrice(rs.getInt("carprice"));
                    car.setImgSrc(rs.getString("carimg"));
                    car.setDetails(rs.getString("cardetails"));
                    car.setColor1(rs.getString("color1"));
                    car.setColor2(rs.getString("color2"));
                    cars.add(car);

                    k++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        } catch (IOException e) {
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
        loader.setLocation(getClass().getResource("fxml/Account.fxml"));
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

    @FXML
    void signOutOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();
        dashboardPane.getChildren().setAll(content);
    }

}
