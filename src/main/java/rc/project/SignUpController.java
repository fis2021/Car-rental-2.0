package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable  {

//    @FXML
//    private JFXButton signIn;

//    @FXML
//    private JFXButton login;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;



    @FXML
    void loginAction(ActionEvent event) {
        try {
//            signIn.getScene().getWindow().hide();
            Stage stage = new Stage();
            URL fxmlURL = getClass().getResource("rc.project/LoginMain.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent root1 = fxmlLoader.load();
            stage.setScene(new Scene(root1));
            //stage.setAlwaysOnTop(true);
            stage.show();
            stage.setResizable(false);
        } catch (Exception exception) {
            System.out.println(" Error login: " + exception);
            exception.printStackTrace();
        }
    }

    @FXML
    void signInAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setStyle("-fx-text-inner-color: #a0a2ab;");
        lastName.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color: #a0a2ab;");
        username.setStyle("-fx-text-inner-color: #a0a2ab;");
    }
}
