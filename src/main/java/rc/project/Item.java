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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
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

    private double price;

    private String role = "user";

    private String idUser = LoginController.id;

    public void setData(Car car) {
        detailsLabel.setVisible(false);
        this.car = car;
        itemText.setText(car.getName());
        itemPrice.setText(car.getPrice() + " " + App.CURRENCY + " / day");
        detailsLabel.setText(car.getDetails());
        Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
        itemPhoto.setImage(image);
        pane.setStyle("-fx-background-color: radial-gradient(center 50% 50% , radius 60% ,#" + car.getColor1() + ", #" + car.getColor2() + ");");
    }

    private int i = 1, k = 1, ok = 1;

    public void loadDetalii(ActionEvent event) throws IOException, SQLException {
        if (k == 1 && ok == 1) {
            this.itemPhoto.setVisible(false);
            this.itemOrder.setVisible(false);
            this.itemText.setVisible(false);
            this.itemPrice.setVisible(false);
            this.detailsLabel.setVisible(true);
            this.itemDetails.setText("Go back");
            k++;
        } else if (k == 2 && ok == 1) {
            this.itemPhoto.setVisible(true);
            this.itemDetails.setVisible(true);
            this.itemOrder.setVisible(true);
            this.itemText.setVisible(true);
            this.itemPrice.setVisible(true);
            this.detailsLabel.setVisible(false);
            this.itemDetails.setText("Details");
            k = 1;
        }
        if ((i > 1) && (dateFrom.getValue() != null) && (dateTo.getValue() != null)) {
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

            String carId = "";
            String status = "pending";
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from cars WHERE carimg = '" + car.getImgSrc() + "'");
            while (rs.next()) {
                carId = rs.getString("id");
            }

            String insertFields = "INSERT INTO orders (userId, carId, status, dateFrom, dateTo, price ) VALUES ('";
            String insertValues = idUser + "','" + carId + "','" + status + "','" + dateTo.getValue() + "','" + dateFrom.getValue() + "','" + price + "')";
            String insertOrder = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertOrder);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if ((i > 1) && ((dateFrom.getValue() == null) || (dateTo.getValue() == null))) {
            this.warningLabel.setVisible(true);
            this.warningLabel.setText("Please complete the dates");
        } else {
            this.warningLabel.setVisible(false);
        }
    }

    private void checkRole() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE id = '" + LoginController.id + "' AND role = 'admin'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    role = "admin";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void loadOrders(ActionEvent event) throws IOException {
        if (i == 0) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String cname = car.getName();

            String getId = "SELECT * FROM cars WHERE carimg = '" + car.getImgSrc() + "' ";
            String deleteCars = "DELETE FROM cars WHERE carimg = '" + car.getImgSrc() + "' ";

            try {

                Statement statement = connectDB.createStatement();
                ResultSet rs =  statement.executeQuery(getId);

                while(rs.next()){
                    connectDB.createStatement().executeUpdate("DELETE FROM orders WHERE carId ='" + rs.getString("id") + "'");
                    if (connectDB.createStatement().executeUpdate(deleteCars) != 0) {
                        System.out.println("Car " + cname + " removed");
                    }
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getCause();
            }
        } else if (i == 1) {
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
            if ((dateFrom.getValue() != null) && (dateTo.getValue() != null)) {

                //calculate price
                price = car.getPrice() * Period.between(dateFrom.getValue(), dateTo.getValue()).getDays();
                this.priceText.setText(String.valueOf(price));

                this.warningLabel.setVisible(false);
                this.totalPrice.setVisible(true);
                this.priceText.setVisible(true);
                this.itemOrder.setText("Go back");
                this.itemDetails.setText("Confirm");
                i++;
            } else if ((dateFrom.getValue() == null) || (dateTo.getValue() == null)) {
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

        checkRole();
        if (role.equals("admin")) {
            i = 0;
            this.itemOrder.setText("Remove car");

//            DatabaseConnection connectNow = new DatabaseConnection();
//            Connection connectDB = connectNow.getConnection();
//            String cname = car.getName();
//
//            String deleteCars = "DELETE FROM cars WHERE carimg = '" + car.getImgSrc() + "' ";
//
//            try {
//
//                Statement statement = connectDB.createStatement();
//                int queryResult = statement.executeUpdate(deleteCars);
//
//                if(queryResult != 0){
//                    System.out.println("Car " + cname + " removed");
//                }
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }


            System.out.println("sunt admin");
        }


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
