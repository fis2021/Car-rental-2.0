package rc.project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
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
    private void ageCounter(){
        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.of( (dateOfBirth.getValue().getYear()),
                (dateOfBirth.getValue().getMonth()),
                (dateOfBirth.getValue().getDayOfMonth()) );

        int ageInYears = Period.between(bday, today).getYears();
        System.out.println(Integer.toString(ageInYears));
        //ageField.setText(Integer.toString(ageInYears) + " Years");
    }

    @FXML
    public void signUpAction() {



//        Thread datebaseThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = new User(firstName.getText(),lastName.getText(), password.getText(), email.getText());
//
//                Transaction transaction = null;
//
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//                    // start a transaction
//                    transaction = session.beginTransaction();
//                    // save the student objects
//                    session.save(user);
//                    // commit transaction
//                    transaction.commit();
//                } catch (Exception e) {
//                    if (transaction != null) {
//                        transaction.rollback();
//                    }
//
//                    System.out.println(e.getMessage());
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        datebaseThread.start();
//
//        System.out.println("Merge");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setStyle("-fx-text-inner-color: #a0a2ab;");
        lastName.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color: #a0a2ab;");
        email.setStyle("-fx-text-inner-color: #a0a2ab;");
    }

}
