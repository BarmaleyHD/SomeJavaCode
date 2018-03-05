package application;

import java.sql.Connection;
import java.sql.SQLException;

import database.DBConnection;
import javafx.fxml.FXML;
import tables.AgentDB;

public class MainWindowController {
	
	DBConnection objDbClass;
	
	@FXML
	void initialize() throws SQLException{
	    objDbClass = new DBConnection();
	    Object logger;
		Connection con = objDbClass.getConnection();
		buildData();
	}

	private void buildData() {
		AgentDB.getAgents();
		
	}
}
