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
import java.sql.SQLException;
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

    public static String id;

    private String role;

    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException {
        String nextFXML;
        loginMessage.setVisible(false);

        String username = usernameField.getText();
        String password = passwordField.getText();

       if(!username.isBlank() && !password.isBlank()){
           getUser();
           validateLogin();

           if(role.equals("admin")){
               nextFXML = "fxml/Dealer.fxml";
           } else nextFXML = "fxml/Dashboard.fxml";

           if(okay == 1) {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource(nextFXML));
               Parent content = loader.load();
               loginPane.getChildren().setAll(content);
               //System.out.println(id);
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
            //id = queryResult.getString("id");
            //ResultSet queryRow = statement.executeQuery(getRow);
           // int n = 0;
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    okay = 1;
                    loginMessage.setText("Congrats!");
                    loginMessage.setVisible(true);
                    //id =  queryRow.getString("id");
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
    public void getUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getRow = "SELECT * FROM users WHERE email = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getRow);
            while (queryResult.next()){
                System.out.println(queryResult.getString("id"));
                id = queryResult.getString("id");
                role = queryResult.getString("role");
                System.out.println(role);

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
