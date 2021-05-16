package rc.project;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class IndividualContactDealer {

    @FXML
    private Text contactName;

    @FXML
    private Text contactMessage;

    @FXML
    private Text contactPhone;

    public void setData(Contact contact){

        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhone());
        contactMessage.setText(contact.getMessage());
    }
}
