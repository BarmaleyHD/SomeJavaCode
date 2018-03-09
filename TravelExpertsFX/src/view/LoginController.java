package view;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.DBHelper;
import application.LoginManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class LoginController {
	LoginManager loginManager;	

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
    	Thread t = new Thread(new Runnable() {
    	    public void run() {
    	    	while(true) {
    	    		try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    	        if (DBHelper.checkConnection()) {
    	        	indStatus.setFill(Color.GREEN);
    	        } else {
    	        	indStatus.setFill(Color.RED);
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
    	loginManager = new LoginManager();
    	loginManager.authenticated("new");
    }
}
