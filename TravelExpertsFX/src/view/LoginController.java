package view;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.DBHelper;
import application.Main;
//import application.SceneManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import model.Agent;
//import database.*;

public class LoginController {
	//SceneManager sceneManeger;	
	boolean connected = false;
	boolean loginPage = true;
	Thread t;
	Agent curAgent;

	@FXML
    private Pane pnlLogo;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblExit;

    @FXML
    private Label lblInfo;
    
    @FXML
    private Ellipse indStatus;

    @FXML
    void close(MouseEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void mouseOver(MouseEvent event) {
    	Object object = event.getSource();
    	if(object instanceof Label) {
    		Label lbl  = (Label)event.getSource();
        	lbl.setStyle("-fx-background-color: darkgrey;");
    	}
    	if(object instanceof Button) {
    		Button btn  = (Button)event.getSource();
    		btn.setStyle("-fx-background-color: darkgrey;");
    	}
    	
    }
    
    @FXML
    void mouseLeave(MouseEvent event) {
    	Object object = event.getSource();
    	if(object instanceof Label) {
    		Label lbl  = (Label)event.getSource();
        	lbl.setStyle("-fx-background-color: transparent;");
    	}
    	if(object instanceof Button) {
    		Button btn  = (Button)event.getSource();
    		btn.setStyle("-fx-background-color: transparent;");
    	}
    }

    @FXML
    void getInfo(MouseEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Travel Experts");
    	alert.setContentText("If you can't login contact administrator on example@example.com");
    	alert.showAndWait();
    }
    
    public void initialize(){
    	start();
    	t = new Thread(new Runnable() {
    	    public void run() {
    	    	while(loginPage) {    	    		
    	        if (DBHelper.checkConnection()) {
    	        	indStatus.setFill(Color.GREEN);
    	        	connected = true;
    	        	System.out.println("ON");
    	        } else {
    	        	indStatus.setFill(Color.RED);
    	        	connected = false;
    	        	System.out.println("OFF");
    	        }
    	        try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	    	}
    	    }
    	});

    	t.start();
    }
    
    public void start() {
    	FadeTransition ft = new FadeTransition(Duration.millis(3000), pnlLogo);
    	ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play();
    } 
    
    @FXML
    void ButtonLoginAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Main.mainPage();
//    	if(connected) {
//    		curAgent = AgentDB.getAgent(1);
//    		sceneManeger = new SceneManager();
//    		sceneManeger.authenticated(curAgent);
//    		loginPage = false;
//    		
//    	} else {
//    		Alert alert = new Alert(AlertType.INFORMATION);
//        	alert.setTitle("No connection to databsase");
//        	alert.setHeaderText("No connection");
//        	alert.setContentText("You can't login because database isn't connected. If you can't resolve this problem contact example@example.com");
//        	alert.showAndWait();
//    	}
//    	
    }
}
