package rc.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
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

    @FXML
    public void loginButtonAction(){
        loginMessage.setVisible(false);

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setVisible(true);
            loginMessage.setText("Please type in a username!");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setVisible(true);
            loginMessage.setText("Password cannot be empty");
            return;
        }
    }

    @FXML
    public void loadRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SignUp.fxml")));
        loginPane.getChildren().setAll(pane);
    }
}
