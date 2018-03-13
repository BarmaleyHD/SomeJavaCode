package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.MainController;
import view.RootLayoutController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;



public class Main extends Application {
	
	public static Stage pstage;
	public static BorderPane rootLayout;
	public static RootLayoutController rootController;
	public static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		pstage = primaryStage;
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			//Scene scene = new Scene(root,400,400);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			pstage.setScene(scene);
			pstage.initStyle(StageStyle.UNDECORATED);
			pstage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//Login();
			
	}
	
	public static void mainPage() {
		try {
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Easy Booking");
//			primaryStage.getIcons().add(new Image("/travel.png"));
			//primaryStage.setFullScreen(true);
			//primaryStage.show();
			//pstage = primaryStage;
			//pstage.setTitle("Easy Booking");
			//pstage.getIcons().add(new Image("/travel.png"));
			pstage.setMaximized(true);			
	        initRootLayout();
	        showMain();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            rootController = (RootLayoutController) loader.getController();
            // Show the scene containing the root layout.
            scene = new Scene(rootLayout,600,500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            pstage.initStyle(StageStyle.UNDECORATED);
            pstage.setScene(scene);
            pstage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }     
        
    }
    
    /**
     * Shows the main overview inside the root layout.
     */
    public void showMain() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Main.fxml"));
            AnchorPane mainPage = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainPage);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
