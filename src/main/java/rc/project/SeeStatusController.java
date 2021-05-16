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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SeeStatusController implements Initializable {

    @FXML
    private GridPane gridStatus;

    private List<Order> orders = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < orders.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml/OrderStatusDealer.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                OrderStatusDealer orderStatusDealer = fxmlLoader.getController();
                orderStatusDealer.setData(orders.get(i));

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

    private List<Order> getData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        List<Order> orders = new ArrayList<>();
        Order order1;

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from orders");

            while (rs.next()) {
                order1 = new Order();

                order1.setPrice(Double.parseDouble(rs.getString("price")));
                order1.setDateFrom(rs.getString("dateFrom"));
                order1.setDateTo(rs.getString("dateTo"));
                order1.setStatus(rs.getString("status"));

                ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * from cars where id = '" + rs.getString("carId") +"'");
                while(rs1.next()){
                    order1.setName(rs1.getString("carname"));

                    ResultSet rs2 = connectDB.createStatement().executeQuery("SELECT * from users where id ='" + rs.getString("userId") +"'");
                    while(rs2.next()) {
                        order1.setNamePerson(rs2.getString("firstname") + " " + rs2.getString("lastname"));
                        order1.setAge(rs2.getString("age"));
                        order1.setPhone(rs2.getString("phone"));
                    }
                }

                orders.add(order1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }
}
