package rc.project;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

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
}
