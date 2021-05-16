package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCarsPage {

    @FXML
    private TextField nameTextF;

    @FXML
    private TextField priceTextF;

    @FXML
    private TextField imgNameTextF;

    @FXML
    private TextField color2TextF;

    @FXML
    private TextField color1TextF;

    @FXML
    private TextArea detailsTextA;

    @FXML
    private Button addCarButton;

    @FXML
    private Text addText;


    @FXML
    void addCarButtonAction(ActionEvent event) {
        if (!nameTextF.getText().isEmpty()
                && !priceTextF.getText().isEmpty()
                && !imgNameTextF.getText().isEmpty()
                && !detailsTextA.getText().isEmpty()
                && !color1TextF.getText().isEmpty()
                && !color2TextF.getText().isEmpty()) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            System.out.println("DB Connected.");

            String insertFields = "INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('";
            ;
            String insertValues = nameTextF.getText() + "','" + priceTextF.getText() + "','" + imgNameTextF.getText() + "', '" + detailsTextA.getText()
                    + "', '" + color1TextF.getText() + "','" + color2TextF.getText() + "')";
            String insertRegister = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                if (statement.executeUpdate(insertRegister) != 0) {
                    addText.setFill(Color.WHITE);
                    addText.setText("Success!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
                addText.setFill(Color.RED);
                addText.setText("Car already exists.");
            }
        } else {
            addText.setFill(Color.RED);
            addText.setText("All fields must be filled.");
        }
    }

}
