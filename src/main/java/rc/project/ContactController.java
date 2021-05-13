package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField emailField;

    @FXML
    private TextField messageField;

    @FXML
    private Button getHelpButton;

    @FXML
    private Label messageBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setStyle("-fx-text-inner-color: #a0a2ab;");
        emailField.setStyle("-fx-text-inner-color: #a0a2ab;");
        messageField.setStyle("-fx-text-inner-color: #a0a2ab;");

    }

    @FXML
    void getHelpAction(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        System.out.println("DB Connected.");

        String fullname = name.getText();
        String email = emailField.getText();
        String message = messageField.getText();

        String insertFields = "INSERT INTO contact (fullname, email, message) VALUES ('";
        String insertValues = fullname + "','" + email + "','" + message + "')";
        String insertContact = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertContact);
            messageBox.setText("Message sent!");

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
