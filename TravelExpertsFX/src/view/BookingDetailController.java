/**
 * Sample Skeleton for 'BankingDetailScene.fxml' Controller Class
 */

package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.DBHelper;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Details;
import model.TripTypes;

public class BookingDetailController {
	public static Details det; //global variable
	public static TripTypes tripType; //variable to be used when combobox item is selected
	private BorderPane rootLayout;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="sbCust"
    private GridPane sbCust; // Value injected by FXMLLoader

    @FXML // fx:id="txtDeptCity"
    private TextField txtDeptCity; // Value injected by FXMLLoader

    @FXML // fx:id="cbTripType"
    private ComboBox<TripTypes> cbTripType; // Value injected by FXMLLoader

    @FXML // fx:id="txtCust"
    private TextField txtCust; // Value injected by FXMLLoader

    @FXML // fx:id="btnConfirm"
    private Button btnConfirm; // Value injected by FXMLLoader
    
    private Connection conn;

    @FXML
    void btnConfirmOnClick(ActionEvent event) throws IOException {
    	//loading up the values into the Details object
    	//the triptype is getting from combobox selection and the no.ofcustomer textbox 
    	//and the departure city textbox is getting from the field
    	det = new Details(tripType, Integer.parseInt(txtCust.getText()), txtDeptCity.getText());
    	//System.out.println(det);
    	if(Integer.parseInt(txtCust.getText())>10){
    		System.out.println("The amount of customer selected exceeds limit");
    	}
    	
    	this.rootLayout = Main.rootLayout;
    	AnchorPane Summary = (AnchorPane)FXMLLoader.load(getClass().getResource("Summary.fxml"));
		rootLayout.setCenter(Summary);

    }
    
    //when the item is selected from combobox, it is loading to tripType variable
    @FXML
    void cbSelectOnClick(ActionEvent event) {

    	tripType = cbTripType.getSelectionModel().getSelectedItem();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws ClassNotFoundException, SQLException {
        assert sbCust != null : "fx:id=\"sbCust\" was not injected: check your FXML file 'BankingDetailScene.fxml'.";
        assert txtDeptCity != null : "fx:id=\"txtDeptCity\" was not injected: check your FXML file 'BankingDetailScene.fxml'.";
        assert cbTripType != null : "fx:id=\"cbTripType\" was not injected: check your FXML file 'BankingDetailScene.fxml'.";
        assert txtCust != null : "fx:id=\"txtCust\" was not injected: check your FXML file 'BankingDetailScene.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'BankingDetailScene.fxml'.";
        Main.rootController.onPage("lblDetail");
        conn = DBHelper.getConnection();
        buildComboBox();
        
        //ensure that the NoOfCustomer textbox forces field to be numeric only
        txtCust.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                	txtCust.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        
    }

	private void buildComboBox() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<TripTypes> data = FXCollections.observableArrayList();
		//stmt is a statement object for the purpose of sending SQL statements to the database
		Statement stmt = conn.createStatement();
		//executes the given SQL  statements which returns one ResultSet object
		ResultSet rs = stmt.executeQuery("Select * from triptypes");
		while (rs.next()){
			data.add(new TripTypes(rs.getString(1), rs.getString(2)));
		}

		cbTripType.setItems(data);
		
		conn.close();
	}
}


