package view;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Package;

public class packagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Slider sliderPrice;

    @FXML
    private TextField tfPackageValue;

    @FXML
    private TextField tfPackageName;

    @FXML
    private DatePicker dpPackageDate;
    
    @FXML
    private DatePicker dpPackageEndDate;

    @FXML
    private Button btnPickPackage;

    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private TableView<Package> tvPackages;

    @FXML
    private TableColumn<Package, Integer> clmID;

    @FXML
    private TableColumn<Package, String> clmName;

    @FXML
    private TableColumn<Package, Date> clmStart;

    @FXML
    private TableColumn<Package, Date> clmEnd;

    @FXML
    private TableColumn<Package, String> clmDescription;

    @FXML
    private TableColumn<Package, BigDecimal> clmBase;

    @FXML
    private TableColumn<Package, BigDecimal> clmCommission;
    
    private Package pack;
    private ObservableList<Package> packages;
    public static Package selectedPackage;
    private BorderPane rootLayout;
    
    @FXML
    void btnPickPackageOnClick(ActionEvent event) throws IOException {
    	btnPickPackage.setText(selectedPackage.getPkgName());
    	this.rootLayout = Main.rootLayout;
    	AnchorPane BookingDetail = (AnchorPane)FXMLLoader.load(getClass().getResource("BookingDetail.fxml"));
		rootLayout.setCenter(BookingDetail);
		//Main.rootController.onPage("lblDetail");
    }

    @FXML
    void btnResetOnClick(ActionEvent event) throws ClassNotFoundException, SQLException {
    	tfPackageName.setText("");
    	tfPackageValue.setText("10000");
    	LocalDate defaultDate = LocalDate.now();
    	dpPackageDate.setValue(defaultDate);
    	dpPackageEndDate.setValue(defaultDate.plusDays(7));
    	updatePackageTable("CALL getAllPackages()");
    }

    @FXML
    void btnBackOnclick(ActionEvent event) throws IOException {
    	this.rootLayout = Main.rootLayout;
    	AnchorPane accountPage = (AnchorPane)FXMLLoader.load(getClass().getResource("Account.fxml"));
		rootLayout.setCenter(accountPage);
		//Main.rootController.onPage("lblAccount");
    }
    
	@FXML
    void initialize() throws ClassNotFoundException, SQLException {
		assert sliderPrice != null : "fx:id=\"sliderPrice\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfPackageValue != null : "fx:id=\"tfPackageValue\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfPackageName != null : "fx:id=\"tfPackageName\" was not injected: check your FXML file 'packages.fxml'.";
        assert dpPackageDate != null : "fx:id=\"dpPackageDate\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnPickPackage != null : "fx:id=\"btnPickPackage\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'packages.fxml'.";
        assert tvPackages != null : "fx:id=\"tvPackages\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmID != null : "fx:id=\"clmID\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmName != null : "fx:id=\"clmName\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmStart != null : "fx:id=\"clmStart\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmEnd != null : "fx:id=\"clmEnd\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmDescription != null : "fx:id=\"clmDescription\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmBase != null : "fx:id=\"clmBase\" was not injected: check your FXML file 'packages.fxml'.";
        assert clmCommission != null : "fx:id=\"clmCommission\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'packages.fxml'.";
        assert dpPackageEndDate != null : "fx:id=\"dpPackageEndDate\" was not injected: check your FXML file 'packages.fxml'.";
        Main.rootController.onPage("lblPackage");
        
        tfPackageValue.setText("10000");
        LocalDate defaultDate = LocalDate.now();
        dpPackageDate.setValue(defaultDate);
        dpPackageEndDate.setValue(defaultDate.plusDays(7));       
        updatePackageTable("CALL getAllPackages()");      
        
        tfPackageName.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double value;
            if (tfPackageValue.getText() == null || tfPackageValue.getText().trim().isEmpty()){
        		value = 0;
        	} else {
        		value = Double.parseDouble(tfPackageValue.getText());
        	}
            
            try {
            	updatePackageTable("CALL getPackageByFilters(\""+tfPackageName.getText()+"\", "+value+", \""+dpPackageDate.getValue()+"\", \""+dpPackageEndDate.getValue()+"\")");            	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        tfPackageValue.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
            	
            	if (!newValue.matches("^-?\\d+$")) {
                    tfPackageValue.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        tfPackageValue.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            sliderPrice.setValue(Double.parseDouble(tfPackageValue.getText()));
            double value;
            if (tfPackageValue.getText() == null || tfPackageValue.getText().trim().isEmpty()){
        		value = 0;
        	} else {
        		value = Double.parseDouble(tfPackageValue.getText());
        	}
            
            try {
            	updatePackageTable("CALL getPackageByFilters(\""+tfPackageName.getText()+"\", "+value+", \""+dpPackageDate.getValue()+"\", \""+dpPackageEndDate.getValue()+"\")");              	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        });
        
        sliderPrice.valueProperty().addListener((observable, oldValue, newValue) -> {
        	if (!sliderPrice.isValueChanging()) {
        		tfPackageValue.setText((String)Double.toString(sliderPrice.getValue()).subSequence(0, Double.toString(sliderPrice.getValue()).length()-2));
        	}
        });
        
        dpPackageDate.valueProperty().addListener((observable, oldValue, newValue) -> {
        	if(dpPackageEndDate.getValue().isBefore(dpPackageDate.getValue())) {
        		dpPackageEndDate.setValue(newValue.plusDays(7));
        	}
        	//dpPackageEndDate.
        	
        	double value = Double.parseDouble(tfPackageValue.getText());
        	try {
        		updatePackageTable("CALL getPackageByFilters(\""+tfPackageName.getText()+"\", "+value+", \""+dpPackageDate.getValue()+"\", \""+dpPackageEndDate.getValue()+"\")");              	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        dpPackageEndDate.valueProperty().addListener((observable, oldValue, newValue) -> {       	
        	double value = Double.parseDouble(tfPackageValue.getText());
        	try {
        		updatePackageTable("CALL getPackageByFilters(\""+tfPackageName.getText()+"\", "+value+", \""+dpPackageDate.getValue()+"\", \""+dpPackageEndDate.getValue()+"\")");              	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        tvPackages.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        	selectedPackage = tvPackages.getSelectionModel().getSelectedItem();
        	btnPickPackage.setDisable(false);
        });
    }
	
	
	void updatePackageTable(String query) throws SQLException, ClassNotFoundException {
		packages = FXCollections.observableArrayList();
    	
    	Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
        	pack = new Package();
        	pack.setPackageId(rs.getInt("PackageId"));
        	pack.setPkgName(rs.getString("PkgName"));
        	pack.setPkgStartDate(rs.getDate("PkgStartDate"));
        	pack.setPkgEndDate(rs.getDate("PkgEndDate"));
        	pack.setPkgDesc(rs.getString("PkgDesc"));
        	pack.setPkgBasePrice(rs.getBigDecimal("PkgBasePrice"));
        	pack.setPkgAgencyCommission(rs.getBigDecimal("PkgAgencyCommission"));
        	
        	packages.add(pack);
        }
        
        clmID.setCellValueFactory(new PropertyValueFactory<Package,Integer>("packageId"));
        clmName.setCellValueFactory(new PropertyValueFactory<Package,String>("pkgName"));
        clmStart.setCellValueFactory(new PropertyValueFactory<Package,Date>("pkgStartDate"));
        clmEnd.setCellValueFactory(new PropertyValueFactory<Package,Date>("pkgEndDate"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<Package,String>("pkgDesc"));
        clmBase.setCellValueFactory(new PropertyValueFactory<Package,BigDecimal>("pkgBasePrice"));
        clmCommission.setCellValueFactory(new PropertyValueFactory<Package,BigDecimal>("pkgAgencyCommission"));
        
        tvPackages.setItems(packages);
        btnPickPackage.setDisable(true);
        //tvPackages.getColumns().addAll(clmID, clmName, clmStart, clmEnd, clmDescription, clmBase, clmCommission);
	}
}
