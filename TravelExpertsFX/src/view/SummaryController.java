/*
 * Sample Skeleton for 'Summary.fxml' Controller Class
 */

package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SummaryController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtContact"
    private TextField txtContact; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtCardNo"
    private TextField txtCardNo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCardType"
    private TextField txtCardType; // Value injected by FXMLLoader

    @FXML // fx:id="txtSVCCode"
    private TextField txtSVCCode; // Value injected by FXMLLoader

    @FXML // fx:id="txtExpDate"
    private TextField txtExpDate; // Value injected by FXMLLoader

    @FXML // fx:id="btnBook"
    private Button btnBook; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgName"
    private TextField txtPkgName; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtTotalCost"
    private TextField txtTotalCost; // Value injected by FXMLLoader
    
    Connection conn;

    @FXML
    void btnBookOnClick(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws ClassNotFoundException, SQLException {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtContact != null : "fx:id=\"txtContact\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtCardNo != null : "fx:id=\"txtCardNo\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtCardType != null : "fx:id=\"txtCardType\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtSVCCode != null : "fx:id=\"txtSVCCode\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtExpDate != null : "fx:id=\"txtExpDate\" was not injected: check your FXML file 'Summary.fxml'.";
        assert btnBook != null : "fx:id=\"btnBook\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtPkgName != null : "fx:id=\"txtPkgName\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Summary.fxml'.";
        assert txtTotalCost != null : "fx:id=\"txtTotalCost\" was not injected: check your FXML file 'Summary.fxml'.";
        Main.rootController.onPage("lblSummary");
        //conn = DBHelper.getConnection();
        //bookingDetailsInfo();
        //paymentInfo();
    }

	private void paymentInfo() {
		// TODO Auto-generated method stub
		txtCardNo.setText(txtCardNo.getText());
		txtCardType.setText(txtCardType.getText());
		txtSVCCode.setText(txtSVCCode.getText());
		txtExpDate.setText(txtExpDate.getText());
	}

	private void bookingDetailsInfo() {
		// TODO Auto-generated method stub
/*		ObservableList<Triptype> data = FXCollections.observableArrayList();
		//stmt is a statement object for the purpose of sending SQL statements to the database
		Statement stmt = conn.createStatement();
		//executes the given SQL  statements which returns one ResultSet object
		ResultSet rs = stmt.executeQuery("Select * from triptypes");
		while (rs.next()){
			data.add(new Triptype(rs.getString(1), rs.getString(2)));
		}

		cbTripType.setItems(data);
		
		conn.close();*/
		
	}
}
