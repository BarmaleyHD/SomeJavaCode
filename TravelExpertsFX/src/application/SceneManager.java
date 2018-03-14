package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Agent;
import view.AgentMenuController;
import view.MainController;
import view.RootLayoutController;

/** Manages control flow for logins */
public class SceneManager {
	
	private Agent currentAgent;
	private String sessionID = "Agent";

  public SceneManager() {
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
 * @throws SQLException 
 * @throws ClassNotFoundException 
   */ 
  public void authenticated(Agent agent) throws ClassNotFoundException, SQLException {
	  this.currentAgent = agent;
	  showMainView(sessionID);
  }

  private void showMainView(String sessionID) {
	  BorderPane root = new BorderPane();
	  
	  FXMLLoader loader = new FXMLLoader();
	  FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/view/AgentMenu.fxml"));
	  //FXMLLoader editorLoader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));	
	  FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); 
	  FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("/view/RootLayout.fxml"));
      
	  
	  AgentMenuController menuController = menuLoader.getController();
	  MainController mainC = mainLoader.getController();
	  RootLayoutController rlc = rootLoader.getController();
	  rlc.onPage("lblMain");
	  //RegisterController editorController = editorLoader.getController();  
	  
	  try {
		root.setLeft(menuLoader.load());    	
		root.setRight(mainLoader.load());
        //root.setRight(editorLoader.load());
        //menuController.setAgent(AgentDB.getAgent(1));
        // Close old stage
        Main.pstage.close();
        
        Stage stage = new Stage();
        stage.setMaximized(true);
        Scene scene2 = new Scene(root, 800, 600);
        scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene2);
        stage.setTitle("Easy Booking");		
        stage.show();
		
    } catch (IOException ex) {
      Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
