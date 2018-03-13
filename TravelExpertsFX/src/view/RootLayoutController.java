package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Pages;

public class RootLayoutController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private ImageView logoImage;

    @FXML
    private Label Title;

    @FXML
    private ImageView btnClose;
    
    
    @FXML
    private HBox tabBar;

    @FXML
    private URL location;

    @FXML
    private Label lblMain;

    @FXML
    private Label lblRegister;

    @FXML
    private Label lblAccount;

    @FXML
    private Label lblPackage;

    @FXML
    private Label lblDetail;

    @FXML
    private Label lblSummary;

    @FXML
    private Label lblConfirmation;    

    @FXML
    private Label progressBar;
    
    @FXML
    void closeApp(MouseEvent event) {
    	Platform.exit();
    }

    @FXML
    void initialize() {
        assert logoImage != null : "fx:id=\"logoImage\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert Title != null : "fx:id=\"Title\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'RootLayout.fxml'.";    	
    	assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblMain != null : "fx:id=\"lblMain\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblRegister != null : "fx:id=\"lblRegister\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblAccount != null : "fx:id=\"lblAccount\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblPackage != null : "fx:id=\"lblPackage\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblDetail != null : "fx:id=\"lblDetail\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert lblSummary != null : "fx:id=\"lblSummary\" was not injected: check your FXlML file 'RootLayout.fxml'.";
        assert lblConfirmation != null : "fx:id=\"lblConfirmation\" was not injected: check your FXML file 'RootLayout.fxml'.";
        progressBar.setStyle(activeStyle());
        
        
    }    

    
    public void onPage(String p){
    	Label lb = (Label) Main.scene.lookup("#" + p);
    	Timeline timeline = new Timeline(
    	        new KeyFrame(Duration.ZERO, new KeyValue(progressBar.prefWidthProperty(), progressBar.getWidth())),
    	        new KeyFrame(Duration.millis(400), new KeyValue(progressBar.prefWidthProperty(), lb.getLayoutX()+lb.getWidth())));

    	timeline.play();
    }


    
    private String activeStyle() {
    	String style1 =  "    -fx-background-color: #66b3ff, linear-gradient(#66b3ff, #1a8cff);" + 
        		"        -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" + 
        		"    	 -fx-font-size: 5px;";
    	return style1;
    }
}
