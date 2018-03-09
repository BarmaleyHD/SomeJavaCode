package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

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
    try {
    	AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
		Scene scene2 = new Scene(root2,400,400);
		scene2.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Main.pstage.setScene(scene2);
		Main.pstage.setTitle("Easy Booking");
		Main.pstage.show();
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
