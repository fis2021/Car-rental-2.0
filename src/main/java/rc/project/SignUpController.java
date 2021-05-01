package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class SignUpController implements Initializable  {


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button signUp;

    @FXML
    private DatePicker dateOfBirth;


    @FXML
    private void signUpAction() {

        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.of( (dateOfBirth.getValue().getYear()),
                (dateOfBirth.getValue().getMonth()),
                (dateOfBirth.getValue().getDayOfMonth()) );

        int ageInYears = Period.between(bday, today).getYears();
        System.out.println(Integer.toString(ageInYears));
        //ageField.setText(Integer.toString(ageInYears) + " Years");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setStyle("-fx-text-inner-color: #a0a2ab;");
        lastName.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color: #a0a2ab;");
        email.setStyle("-fx-text-inner-color: #a0a2ab;");
    }
}
