package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderStatus {

    @FXML
    private Text orderModel;

    @FXML
    private Text orderPrice;

    @FXML
    private Text orderFrom;

    @FXML
    private Text orderTo;

    @FXML
    private Text orderStatus;

    private String idUser = LoginController.id;


    public void setData(Order order){

        orderModel.setText(order.getName());
        orderFrom.setText(order.getDateFrom());
        orderTo.setText(order.getDateTo());
        orderStatus.setText(order.getStatus());
        orderPrice.setText(String.valueOf(order.getPrice()));

    }
}
