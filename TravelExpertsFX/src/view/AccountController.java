/**
 * Sample Skeleton for 'Account.fxml' Controller Class
 */

package view;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import application.DBHelper;
import application.Mail;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import model.Agent;
import model.Customer;
import model.Product;
import model.Province;
import model.Validator;

public class AccountController {
	
    private Customer c;
    private Connection conn;
    private BorderPane rootLayout;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtFirstName"
    private TextField txtFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="txtLastName"
    private TextField txtLastName; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtCity"
    private TextField txtCity; // Value injected by FXMLLoader

    @FXML // fx:id="cbProv"
    private ComboBox<Province> cbProv; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader
    
    @FXML
    private Button btnPrevious;

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader
    
    @FXML
    private Button btnCancel;

    @FXML // fx:id="txtPostal"
    private TextField txtPostal; // Value injected by FXMLLoader

    @FXML // fx:id="txtHomePhone"
    private TextField txtHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtWorkPhone"
    private TextField txtWorkPhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmail"
    private TextField txtEmail; // Value injected by FXMLLoader

    @FXML // fx:id="cbAgent"
    private ComboBox<Agent> cbAgent; // Value injected by FXMLLoader    

    @FXML
    private Text erPostal;
    
    @FXML
    private Text erEmail;

    @FXML
    void btnEdit_onClick(ActionEvent event) {
    	Editable(true);
    }
    
    @FXML
    void btnCancel_onClick(ActionEvent event) throws SQLException, ClassNotFoundException {
    	Editable(false);
    	initField();
    }
    
    @FXML
    void btnSave_onClick(ActionEvent event) throws ClassNotFoundException, SQLException { 	  	
    	if(validateInput()) {    		
        	Editable(false);
        	Customer editCustomer = new Customer(c.getCustomerId(),txtFirstName.getText(),txtLastName.getText(),
        			txtAddress.getText(),txtCity.getText(),c.getCustProv(),txtPostal.getText(),c.getCustCountry(),
        			txtHomePhone.getText(),txtWorkPhone.getText(),txtEmail.getText(),c.getAgentId());
        	Update(editCustomer);
        	MainController.selectedCustomer.Copy(editCustomer);
    	}
    }
  

    @FXML
    void btnPrevious_onClick(ActionEvent event) throws IOException {
    	this.rootLayout = Main.rootLayout;
    	AnchorPane accountPage = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
		rootLayout.setCenter(accountPage);

    }

    @FXML
    void btnNext_onClick(ActionEvent event) throws IOException {
    	//next Scene - Package   
    	this.rootLayout = Main.rootLayout;
    	AnchorPane packagePage = (AnchorPane)FXMLLoader.load(getClass().getResource("packages.fxml"));
		rootLayout.setCenter(packagePage);

    }


    @FXML
    void cbAgent_onSelectChange(ActionEvent event) {
    	Agent selectAgent = cbAgent.getSelectionModel().getSelectedItem();
    	if(selectAgent != null)
    	{    		
	    	c.setAgentId(selectAgent.getAgentId());	
	    	System.out.println(c);
    	}
    }

    @FXML
    void cbProv_onSelectChange(ActionEvent event) {
    	Province selectProv = cbProv.getSelectionModel().getSelectedItem();
    	if(selectProv != null)
    	{    		
	    	c.setCustProv(selectProv.getProv());	
	    	System.out.println(c);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws ClassNotFoundException, SQLException {
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'Account.fxml'.";
        assert cbProv != null : "fx:id=\"cbProv\" was not injected: check your FXML file 'Account.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Account.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'Account.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtPostal != null : "fx:id=\"txtPostal\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtHomePhone != null : "fx:id=\"txtHomePhone\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtWorkPhone != null : "fx:id=\"txtWorkPhone\" was not injected: check your FXML file 'Account.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Account.fxml'.";
        assert cbAgent != null : "fx:id=\"cbAgent\" was not injected: check your FXML file 'Account.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Account.fxml'.";
        assert erPostal != null : "fx:id=\"erPostal\" was not injected: check your FXML file 'Account.fxml'.";
        assert erEmail != null : "fx:id=\"erEmail\" was not injected: check your FXML file 'Account.fxml'.";

        Main.rootController.onPage("lblAccount");
		
        txtPostal.textProperty().addListener((observable, oldValue, newValue) -> {            
            if( Validator.isValidPostal(txtPostal.getText().trim()) ){  
            	erPostal.setText("");
            }else {
    			erPostal.setText("Invalid");
    	   }
        });
        
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Validator.isValidEmail(txtEmail.getText().trim())){  
            	erEmail.setText("");
            }else {
            	erEmail.setText("Invalid");
    	   }
        });
        //dispaly customer info
        initField();

        
    }
    
    private void initField() throws SQLException, ClassNotFoundException {
    	conn = DBHelper.getConnection();
    	c = new Customer(MainController.selectedCustomer);
        txtFirstName.setText(c.getCustFirstName());
        txtLastName.setText(c.getCustLastName());
        txtAddress.setText(c.getCustAddress());
        txtCity.setText(c.getCustCity());
        txtPostal.setText(c.getCustPostal());
        txtHomePhone.setText(c.getCustHomePhone());
        txtWorkPhone.setText(c.getCustBusPhone());
        txtEmail.setText(c.getCustEmail());
        
        cbProv.setItems(setProv());
        cbProv.getSelectionModel().select(findProvince(setProv(),c.getCustProv()));
        buildAgentCombo();
        Editable(false);
    	
    }
    
    //load agent from database into combobox
    private void buildAgentCombo() throws SQLException {
    	try {
    	ObservableList<Agent> data = FXCollections.observableArrayList();
    	CallableStatement cstmt = conn.prepareCall("{call getAllAgent()}");
    	ResultSet rs = cstmt.executeQuery();
    	while(rs.next())
    	{
    		data.add(new Agent(rs.getInt("AgentId"),rs.getString("AgtFirstName")
    				,rs.getString("AgtMiddleInitial"),rs.getString("AgtLastName")
    				,rs.getString("AgtBusPhone"),rs.getString("AgtEmail")
    				,rs.getString("AgtPosition"),rs.getInt("AgencyId")));
    	}
    	cbAgent.setItems(data);
    	cbAgent.getSelectionModel().select(findAgent(data,c.getAgentId()));    	
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}	
    	conn.close();
    	
    	
    }
    
    //update the customer table on save
    private void Update(Customer editCustomer) throws ClassNotFoundException, SQLException {
    	conn = DBHelper.getConnection();
    	CallableStatement cstmt = conn.prepareCall("{call updateCustomer(?,?,?,?,?,?,?,?,?,?,?,?)}");
    	cstmt.setString(1, editCustomer.getCustFirstName());
    	cstmt.setString(2, editCustomer.getCustLastName());
    	cstmt.setString(3, editCustomer.getCustAddress());
    	cstmt.setString(4, editCustomer.getCustCity());
    	cstmt.setString(5, editCustomer.getCustProv());
    	cstmt.setString(6, editCustomer.getCustPostal());
    	cstmt.setString(7, editCustomer.getCustCountry());
    	cstmt.setString(8, editCustomer.getCustHomePhone());
    	cstmt.setString(9, editCustomer.getCustBusPhone());
    	cstmt.setString(10, editCustomer.getCustEmail());
    	cstmt.setInt(11, editCustomer.getAgentId());
    	cstmt.setInt(12, editCustomer.getCustomerId());
    	cstmt.executeQuery();
    	
    }
    
    //set editing mode for all textFields, Edit button and Save button
    private void Editable(Boolean b) {
    	txtFirstName.setEditable(b);
    	txtLastName.setEditable(b);
    	txtAddress.setEditable(b);
    	txtCity.setEditable(b);    	
    	txtPostal.setEditable(b);
    	txtHomePhone.setEditable(b);
    	txtWorkPhone.setEditable(b);
    	txtEmail.setEditable(b);
    	cbProv.setDisable(!b);
    	cbAgent.setDisable(!b);
    	btnCancel.setVisible(b);
    	btnSave.setVisible(b);
    	btnEdit.setDisable(b);
    	
    }
    
    // setup province list for the combobox
    private ObservableList<Province> setProv(){
    	
    	ObservableList<Province> prov = FXCollections.observableArrayList();
        prov.add(new Province("AB","Alberta"));
        prov.add(new Province("BC","British Columbia"));
        prov.add(new Province("MB","Manitoba"));
        prov.add(new Province("MB","Manitoba"));
        prov.add(new Province("NB","New Brunswick"));
        prov.add(new Province("NL","Newfoundland and Labrador"));
        prov.add(new Province("NS","Nova Scotia"));
        prov.add(new Province("NT","Northwest Territories"));
        prov.add(new Province("NU","Nunavut"));
        prov.add(new Province("ON","Ontario"));
        prov.add(new Province("PE","Prince Edward Island"));
        prov.add(new Province("QC","Quebec"));
        prov.add(new Province("SK","Saskatchewan"));
        prov.add(new Province("YT","Yukon"));
    	return prov;    	
    }
    
    //find the province object in the list by customer's prov
    private Province findProvince(ObservableList<Province> prov, String cp) {
        for(Province p : prov) {
            if(p.getProv().equals(cp)) {
                return p;
            }
        }
        return null;
    }
    
    //find the agent object in the list by customer's agentId
    private Agent findAgent(ObservableList<Agent> ag, int ca) {
        for(Agent a : ag) {
            if(a.getAgentId() == ca) {
                return a;
            }
        }
        return null;
    }
    
    //validate input
    private boolean validateInput() {
      	boolean valid = true;
      	String regexPostal="^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$";
      	String regexEmail= "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
      	//String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
      	
      	if(!Validator.IsTxTEmpty(txtFirstName, "First Name"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtLastName, "Last Name"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtAddress, "Address"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtCity, "City"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtPostal, "Postal"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtHomePhone, "Phone"))
      	{
      		valid = false;
      	}
      	if(!Validator.IsTxTEmpty(txtEmail, "Email"))
      	{
      		valid = false;
      	}
      	else if(!Validator.IsTxTValid(txtPostal, "Postal", regexPostal)) {
      		valid = false;
      	}
      	else if(!Validator.IsTxTValid(txtEmail, "Email", regexEmail)) {
      		valid = false;
      	}

      		
      	return valid;
      	
      }
}
