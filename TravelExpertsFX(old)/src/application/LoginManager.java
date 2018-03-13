package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainController;
import view.RegisterViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

/** Manages control flow for logins */
public class LoginManager {

  public LoginManager() {
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
 * @throws SQLException 
 * @throws ClassNotFoundException 
   */ 
  public void authenticated(String sessionID) throws ClassNotFoundException, SQLException {
    showMainView(sessionID);
  }

  private void showMainView(String sessionID) {
	  BorderPane root = new BorderPane();
	  FXMLLoader listLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
	  try {
		root.setCenter(listLoader.load());    
		MainController listController = listLoader.getController();
		
		FXMLLoader editorLoader = new FXMLLoader(getClass().getResource("/view/RegisterView.fxml"));
        root.setRight(editorLoader.load());
        RegisterViewController editorController = editorLoader.getController();
        Scene scene2 = new Scene(root, 800, 600);
        Main.pstage.setScene(scene2);
        Main.pstage.setTitle("Easy Booking");		
		Main.pstage.show();
		
		
    	//AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
		//Scene scene2 = new Scene(root2,400,400);
		//scene2.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		//Main.pstage.initStyle(StageStyle.DECORATED);	
		
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
