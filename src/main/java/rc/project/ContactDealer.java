package rc.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContactDealer implements Initializable {

    @FXML
    private GridPane gridStatus;

    private List<Contact> contacts = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contacts.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < contacts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml/IndividualContactDealer.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                IndividualContactDealer individualContactDealer = fxmlLoader.getController();
                individualContactDealer.setData(contacts.get(i));

                gridStatus.add(anchorPane, column, row++);
                //width
                gridStatus.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridStatus.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridStatus.setMaxWidth(Region.USE_PREF_SIZE);

                //hight
                gridStatus.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridStatus.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridStatus.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private List<Contact> getData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        List<Contact> contacts = new ArrayList<>();
        Contact contact1;

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * from contact");

            while (rs.next()) {
                contact1 = new Contact();

                contact1.setName(rs.getString("fullname"));
//                contact1.setPhone();
                contact1.setMessage(rs.getString("message"));

                ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * from users where email = '" + rs.getString("email") +"'");
                while(rs1.next()){
                    contact1.setPhone(rs1.getString("phone"));


                }

                contacts.add(contact1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }
}
