/**
 * Sample Skeleton for 'Main.fxml' Controller Class
 */

package view;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.DBHelper;
import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agent;
import model.Customer;

public class MainController {
	
	public static Customer selectedCustomer;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listView"
    private ListView<Customer> listView; // Value injected by FXMLLoader
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Button btnRegister;

    @FXML
    private Button btnNext;
    
    @FXML
    void OnNext(ActionEvent event) throws IOException {
            
            AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("test.fxml"));
			Scene scene2 = new Scene(root2,400,400);
			scene2.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			Main.pstage.setScene(scene2);
			Main.pstage.setTitle("Easy Booking");
			Main.pstage.getIcons().add(new Image("/travel.png"));
			Main.pstage.show();

//    	Stage test = new Stage(); // Create new stage
//    	
//    	// Close the current view
//    	
//    	// New settings
//    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("test.fxml"));
//		Scene scene = new Scene(root,400,400);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		test.setScene(scene);
//		test.setTitle("Easy Booking");
//		test.getIcons().add(new Image("/travel.png"));
//		test.show();
    }
    
    void initialize() {}
    
    private Connection conn;
    
    public void start() throws ClassNotFoundException, SQLException {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Main.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'Main.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'Main.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'Main.fxml'.";
        
        conn = new DBHelper().getConnection();
        buildList();  
        
    	txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            try {
            	conn = new DBHelper().getConnection();
				filterList(newValue);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    	
    	listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	selectedCustomer = listView.getSelectionModel().getSelectedItem();            	    	    	
            }
        });
    	
        
    }
    
  //load data from database to comboBox and tableView
    private void buildList() throws SQLException {
    	try {
    	ObservableList<Customer> data = FXCollections.observableArrayList();
    	CallableStatement cStmt = conn.prepareCall("{call getAllCustomer()}");
    	ResultSet rs = cStmt.executeQuery();
    	while(rs.next())
    	{
    		data.add(new Customer(rs.getInt("CustomerId"),rs.getString("CustFirstName")
    				,rs.getString("CustLastName"),rs.getString("CustAddress")
    				,rs.getString("CustCity"),rs.getString("CustProv")
    				,rs.getString("CustPostal"),rs.getString("CustCountry"),
    				rs.getString("CustHomePhone"),rs.getString("CustBusPhone"),
    				rs.getString("CustEmail"),rs.getInt("AgentId")));
    	}   	
    	listView.setItems(data);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}	
    	conn.close();
    }
    
    private void filterList(String name) throws SQLException {
    	try {
    	ObservableList<Customer> data = FXCollections.observableArrayList();
    	CallableStatement cStmt = conn.prepareCall("{call getCustomerByName(?)}");
        cStmt.setString(1, name);
    	ResultSet rs = cStmt.executeQuery();
    	while(rs.next())
    	{
    		data.add(new Customer(rs.getInt("CustomerId"),rs.getString("CustFirstName")
    				,rs.getString("CustLastName"),rs.getString("CustAddress")
    				,rs.getString("CustCity"),rs.getString("CustProv")
    				,rs.getString("CustPostal"),rs.getString("CustCountry"),
    				rs.getString("CustHomePhone"),rs.getString("CustBusPhone"),
    				rs.getString("CustEmail"),rs.getInt("AgentId")));
    	}   	
    	listView.setItems(data);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}	
    	conn.close();
    }
    
    
}
