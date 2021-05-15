package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Account implements Initializable {

    private String idAccount = LoginController.id;
    @FXML
    private Text name;
    @FXML
    private Button signOut;
    @FXML
    private Text email;
    @FXML
    private Text phone;
    @FXML
    private Button changePassword;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    private Text invalidChange;
    private int ok = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from users WHERE id = '" + idAccount + "'");
            while(rs.next()) {
                name.setText(rs.getString("firstname") + " " + rs.getString("lastname"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println(idAccount);
    }

    @FXML
    public void changePasswordAction(ActionEvent e) throws IOException {
        if (newPassword.getText().equals(confirmNewPassword.getText())) {
            validatePassword();
            if (ok == 1) {
                if (newPassword.getText().equals(currentPassword.getText())) {
                    invalidChange.setText("New password cannot be the same as old password!");
                } else updatePassword();
            }

        } else {
            invalidChange.setText("New password does not match!");
        }

    }

    private void validatePassword() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE id  = '" + idAccount + "' AND password = '" + currentPassword.getText() + "'";


        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    ok = 1;
                } else {
                    invalidChange.setText("Incorrect current password!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    private void updatePassword() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updatePassword = "UPDATE users\n" + "set password = '" + newPassword.getText() + "'\n" + "where id =" + idAccount;

        try {
            Statement statement = connectDB.createStatement();
            int queryResult = statement.executeUpdate(updatePassword);

            if (queryResult != 0) {
                invalidChange.setStyle("-fx-text-fill: #a0a2ab;");
                invalidChange.setText("Password changed!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

