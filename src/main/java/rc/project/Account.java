package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Account implements Initializable {
    private String idAccount = LoginController.id;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text name;

    @FXML
    private Button signOut;

    @FXML
    private Text email;

    @FXML
    private Text phone;

    @FXML
    void signOutOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();
        anchorPane.getChildren().setAll(content);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(idAccount);
    }
}
