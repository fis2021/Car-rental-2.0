package rc.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Dealer {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button editCars;

    @FXML
    private Button addCars;

    @FXML
    private Button seeOrder;

    @FXML
    private Button seeReviews;

    @FXML
    private Button login;

    @FXML
    private AnchorPane changeMeDealer;

    @FXML
    public void loadAddCars(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/AddCarsPage.fxml"));
        Parent content = loader.load();
        changeMeDealer.getChildren().setAll(content);
    }

    @FXML
    public void loadEditCars(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/EditCarsPage.fxml"));
        Parent content = loader.load();
        changeMeDealer.getChildren().setAll(content);
    }

    @FXML
    public void signOutOnAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/LoginMain.fxml"));
        Parent content = loader.load();
        anchorPane.getChildren().setAll(content);
    }

    @FXML
    public void loadOrdersDealer(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/SeeOrders.fxml"));
        Parent content = loader.load();
        changeMeDealer.getChildren().setAll(content);
    }

    @FXML
    public void loadContactDealer(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/ContactDealer.fxml"));
        Parent content = loader.load();
        changeMeDealer.getChildren().setAll(content);
    }



}
