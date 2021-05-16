package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderStatus implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from orders WHERE id = '"+ idUser +"' ");
            ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * from cars ");
            while (rs1.next()) {
                orderModel.setText(rs1.getString("carname"));

            }
            while (rs.next()) {
                orderPrice.setText(rs.getString("price"));
                orderFrom.setText(rs.getString("dateFrom"));
                orderTo.setText(rs.getString("dateTo"));
                orderStatus.setText(rs.getString("status"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
