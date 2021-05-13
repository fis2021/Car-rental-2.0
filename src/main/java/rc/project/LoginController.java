package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
// Login button
// Forget Password button
// Sign in JFXButton
// Remember Me JFXCheckbox

public class LoginController {
    @FXML
    private Text loginMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private AnchorPane loginPane;

    private int okay = 0;
    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException {
        loginMessage.setVisible(false);

        String username = usernameField.getText();
        String password = passwordField.getText();

       if(!username.isBlank() && !password.isBlank()){
           validateLogin();
           if(okay == 1) {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("fxml/Dashboard.fxml"));
               Parent content = loader.load();
               loginPane.getChildren().setAll(content);
           }
       } else {
           loginMessage.setText("Please enter the username and password.");
           loginMessage.setVisible(true);
       }
    }

    //@FXML
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            int n = 0;
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    okay = 1;
                    loginMessage.setText("Congrats!");
                    loginMessage.setVisible(true);
                } else {
                    loginMessage.setText("Invalid login. Please try again.");
                    loginMessage.setVisible(true);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void loadRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/SignUp.fxml"));
        Parent content = loader.load();
        loginPane.getChildren().setAll(content);
    }
}
