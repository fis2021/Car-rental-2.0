package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Item implements Initializable {

    @FXML
    private Label itemText;

    @FXML
    private Label itemPrice;

    @FXML
    private ImageView itemPhoto;

    @FXML
    private Button itemDetails;

    @FXML
    private Button itemOrder;

    @FXML
    private AnchorPane itemAnchor;

    @FXML
    private Pane pane;

    @FXML
    private Label detailsLabel;

    @FXML
    private VBox ordersVbox;

    @FXML
    private Label totalPrice;

    @FXML
    private Label priceText;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Label warningLabel;

    private Car car;

    private String idUser = LoginController.id;

    public void setData(Car car) {
        detailsLabel.setVisible(false);
        this.car = car;
        itemText.setText(car.getName());
        itemPrice.setText(car.getPrice() + " " + App.CURRENCY + " / day");
        detailsLabel.setText(car.getDetails());
        Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
        itemPhoto.setImage(image);
        pane.setStyle("-fx-background-color: radial-gradient(center 50% 50% , radius 60% ,#" + car.getColor1() +", #" + car.getColor2() +");");
    }

    private int i = 1, k = 1, ok = 1;
    public void loadDetalii(ActionEvent event) throws IOException {
        if(k==1 && ok == 1) {
            this.itemPhoto.setVisible(false);
            this.itemOrder.setVisible(false);
            this.itemText.setVisible(false);
            this.itemPrice.setVisible(false);
            this.detailsLabel.setVisible(true);
            this.itemDetails.setText("Go back");
            k++;
        }
        else if (k==2 && ok == 1) {
            this.itemPhoto.setVisible(true);
            this.itemDetails.setVisible(true);
            this.itemOrder.setVisible(true);
            this.itemText.setVisible(true);
            this.itemPrice.setVisible(true);
            this.detailsLabel.setVisible(false);
            this.itemDetails.setText("Details");
            k = 1;
        }
        if((i > 1) && (dateFrom.getValue() != null) && (dateTo.getValue() != null)) {
            System.out.println("Confirm");
            this.ordersVbox.setVisible(false);
            this.itemPhoto.setVisible(true);
            this.itemDetails.setVisible(true);
            this.itemOrder.setVisible(true);
            this.itemText.setVisible(true);
            this.itemPrice.setVisible(true);
            this.totalPrice.setVisible(false);
            this.priceText.setVisible(false);
            this.warningLabel.setVisible(false);
            this.itemOrder.setText("Order");
            this.itemDetails.setText("Details");
            ok = 1;
            i = 1;

            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            System.out.println("DB Connected.");

            String carId = "1";
            String status = "pending";

            String insertFields = "INSERT INTO orders (userId, carId, status, dateFrom, dateTo ) VALUES ('";
            String insertValues = idUser + "','" + carId + "','" + status + "','" + dateTo.getValue() + "','" + dateFrom.getValue() + "')";
            String insertOrder = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertOrder);

            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if ((i > 1) && ((dateFrom.getValue() == null) || (dateTo.getValue() == null))) {
            this.warningLabel.setVisible(true);
            this.warningLabel.setText("Please complete the dates");
        } else {
            this.warningLabel.setVisible(false);
        }
    }

    public void loadOrders(ActionEvent event) throws IOException {
        if (i == 1) {
            ok = 0;
            this.itemPhoto.setVisible(false);
            this.itemText.setVisible(false);
            this.itemPrice.setVisible(false);
            this.ordersVbox.setVisible(true);
            this.warningLabel.setVisible(false);
            this.itemOrder.setText("Show price");
            this.itemDetails.setText("Confirm");
            i++;
        } else if (i == 2) {
            this.itemPhoto.setVisible(false);
            this.itemText.setVisible(false);
            this.itemPrice.setVisible(false);
            this.ordersVbox.setVisible(true);
            this.warningLabel.setVisible(false);
            if((dateFrom.getValue() != null) && (dateTo.getValue() != null)) {
                this.warningLabel.setVisible(false);
                this.totalPrice.setVisible(true);
                this.priceText.setVisible(true);
                this.itemOrder.setText("Go back");
                this.itemDetails.setText("Confirm");
                i++;
            } else if((dateFrom.getValue() == null) || (dateTo.getValue() == null)){
                this.warningLabel.setVisible(true);
                this.warningLabel.setText("Please complete the dates");
            } else {
                this.warningLabel.setVisible(false);
            }
        } else if (i == 3) {
            this.ordersVbox.setVisible(false);
            this.itemPhoto.setVisible(true);
            this.itemDetails.setVisible(true);
            this.itemOrder.setVisible(true);
            this.itemText.setVisible(true);
            this.itemPrice.setVisible(true);
            this.totalPrice.setVisible(false);
            this.priceText.setVisible(false);
            this.warningLabel.setVisible(false);
            this.itemOrder.setText("Order");
            this.itemDetails.setText("Details");
            ok = 1;
            i = 1;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateTo.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
        dateFrom.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }
}
