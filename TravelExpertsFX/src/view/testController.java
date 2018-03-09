package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class testController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTest;

    @FXML
    void initialize() {
        assert lblTest != null : "fx:id=\"lblTest\" was not injected: check your FXML file 'test.fxml'.";
        
        lblTest.setText(MainController.selectedCustomer.getCustFirstName());
     
    }
}
