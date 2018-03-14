package view;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import database.AgentDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Agent;

public class AgentMenuController {
	
	private Agent currentAgent;
	
	public void setAgent(Agent agent) {
		this.currentAgent = agent;
	}
	@FXML
	private Label lblUserName;
	@FXML
	private Label lblUserStatus;
	@FXML
	private Label lblUserEmail;
	@FXML
	private JFXButton btnUserSettings;
	@FXML
	private JFXButton btnLogout;
	
	 @FXML
	 void setTextAction(ActionEvent event) throws ClassNotFoundException, SQLException {		 
			lblUserStatus.setText(AgentDB.getAgent(1).getAgtLastName());
	 }
	
	public void initialize() {
		lblUserName.setText(AgentDB.getAgent(1).getAgtFirstName() + " " + AgentDB.getAgent(1).getAgtLastName());
	}
	
	
	

}
