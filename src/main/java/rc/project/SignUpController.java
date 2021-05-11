package rc.project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;


public class SignUpController implements Initializable {


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button signUp;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private Label registrationMessage;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField phoneTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setStyle("-fx-text-inner-color: #a0a2ab;");
        lastName.setStyle("-fx-text-inner-color: #a0a2ab;");
        passwordTextField.setStyle("-fx-text-inner-color: #a0a2ab;");
        confirmPassword.setStyle("-fx-text-inner-color: #a0a2ab;");
        emailTextField.setStyle("-fx-text-inner-color: #a0a2ab;");
        phoneTextField.setStyle("-fx-text-inner-color: #a0a2ab;");
        dateOfBirth.setStyle("-fx-text-inner-color: #a0a2ab;");
    }

    @FXML
    private int ageCounter() {
        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.of((dateOfBirth.getValue().getYear()),
                (dateOfBirth.getValue().getMonth()),
                (dateOfBirth.getValue().getDayOfMonth()));

        int ageInYears = Period.between(bday, today).getYears();
        System.out.println(ageInYears);
        return ageInYears;
        //ageField.setText(Integer.toString(ageInYears) + " Years");
    }

    @FXML
    public void signUpAction() {
        if (passwordTextField.getText().equals(confirmPassword.getText())) {
            registerUser();


        } else {
            confirmPasswordLabel.setText("Password does not match.");
        }

    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String phone = phoneTextField.getText();
        int age = ageCounter();

        String insertFields = "INSERT INTO users (firstname, lastname, email, password, phone, age) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + email + "','" + password + "','" + phone + "','" + age + "')";
        String insertRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
            registrationMessage.setText("User has been registered successfully!");

        } catch (Exception e){
            confirmPasswordLabel.setText("Email invalid!");
            e.printStackTrace();
            e.getCause();
        }

    }


}
