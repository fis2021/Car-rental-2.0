package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;


public class SignUpController implements Initializable {

    @FXML
    private Button returnToLogin;

    @FXML
    private AnchorPane changeMe;

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

        dateOfBirth.setConverter(new StringConverter<LocalDate>() {
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
        if (!firstName.getText().isBlank()
                && !lastName.getText().isBlank()
                && dateOfBirth.getValue() != null
                && !emailTextField.getText().isBlank()
                && !passwordTextField.getText().isBlank()
                && !confirmPassword.getText().isBlank()) {
            if (passwordTextField.getText().equals(confirmPassword.getText())) {
                if (emailTextField.getText() != passwordTextField.getText()) {
                    if (ageCounter() >= 18) {
                        registerUser();
                    } else {
                        confirmPasswordLabel.setText("You must be at least 18 years old.");
                    }

                } else {
                    confirmPasswordLabel.setText("Email and password must be different for security issues.");
                }

                 } else {
                confirmPasswordLabel.setText("Password does not match.");
            }

            } else {
            confirmPasswordLabel.setText("All fields must be filled.");

        }


    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        System.out.println("DB Connected.");

        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String phone = phoneTextField.getText();
        int age = ageCounter();

        String insertFields = "INSERT INTO users (firstname, lastname, email, password, phone, age, role) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + email + "'," + " aes_encrypt(concat('" + password + "', '" + email + "'), 'key1234') " + ",'" + phone + "','" + age + "','user')";
        String insertRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
            confirmPasswordLabel.setText("");
            registrationMessage.setText("User has been registered successfully!");

        } catch (Exception e) {
            registrationMessage.setText("");
            confirmPasswordLabel.setText("Email invalid!");
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void returnToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();
        changeMe.getChildren().setAll(content);
    }


}
