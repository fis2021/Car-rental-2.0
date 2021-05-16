package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusDealer {

    @FXML
    private Text orderModel;

    @FXML
    private Text orderPrice;

    @FXML
    private Text orderFrom;

    @FXML
    private Text orderTo;

    @FXML
    private Text ordersName;

    @FXML
    private Text ordersAge;

    @FXML
    private Text ordersPhone;

    @FXML
    private Text responseText;

    @FXML
    private Button denyButton;

    @FXML
    private Button acceptButton;


    private String idUser = LoginController.id;


    public void setData(Order order){

        orderModel.setText(order.getName());
        orderFrom.setText(order.getDateFrom());
        orderTo.setText(order.getDateTo());
        orderPrice.setText(String.valueOf(order.getPrice()));
        ordersName.setText(order.getNamePerson());
        ordersAge.setText(order.getAge());
        ordersPhone.setText(order.getPhone());

    }

    @FXML
    void acceptAction(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from users where phone = '" + ordersPhone.getText() +  "' ");
            while (rs.next()){
                ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * from cars where carname = '" + orderModel.getText() +  "' ");
                while (rs1.next()){
                    int result =  connectDB.createStatement().executeUpdate("UPDATE orders SET status = 'ACCEPTED' where userId = '"
                            + rs.getString("id") +"' and carId = '"+ rs1.getString("id") +"'");
                    if(result != 0){
                        acceptButton.setVisible(false);
                        denyButton.setVisible(false);
                        responseText.setText("ACCEPTED");
                        responseText.setVisible(true);
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void denyAction(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from users where phone = '" + ordersPhone.getText() +  "' ");
            while (rs.next()){
                ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * from cars where carname = '" + orderModel.getText() +  "' ");
                while (rs1.next()){
                    int result =  connectDB.createStatement().executeUpdate("UPDATE orders SET status = 'DENIED' where userId = '"
                            + rs.getString("id") +"' and carId = '"+ rs1.getString("id") +"'");
                    if(result != 0){
                        acceptButton.setVisible(false);
                        denyButton.setVisible(false);
                        responseText.setText("DENIED");
                        responseText.setVisible(true);
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
