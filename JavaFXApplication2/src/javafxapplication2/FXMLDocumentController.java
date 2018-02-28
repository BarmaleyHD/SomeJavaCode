package javafxapplication2;

import com.jfoenix.controls.*;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.Icon;

/**
 *
 * @author 719454
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lblLondon, lblRiga, lblParis, lblBerlin, lblMoscow, lblBrussel, lblCalgary;
    @FXML
    private Button btnLogin;

   // @FXML
   // private JX txtUsername;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField txtUsername;

    @FXML
    private void btnLoginAction(ActionEvent event) {
        lblRiga.setText("Done!");
    }
    
    @FXML
    private void iconCloseAction(ActionEvent event) {
        Stage stage = (Stage) closeIcon.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private Label closeIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Label> labels = new ArrayList<>(
                Arrays.asList(lblLondon, lblRiga, lblParis, lblBerlin, lblMoscow, lblBrussel, lblCalgary));
        lblRiga.setText(String.valueOf(lblRiga.getLayoutX()));
        moveLabel(lblRiga, 0, 75);
        moveLabel(lblParis, 50, 80);
    }

    private void moveLabel(Label lbl, int posX, int posY) {
        PathTransition pathTransition = new PathTransition();
        //lbl.setLayoutX(50);
        //lbl.setLayoutY(65);
        Path path = new Path();
        path.getElements().add(new MoveTo(posX, posY));
        path.getElements().add(new LineTo(50, 350));

        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setPath(path);
        pathTransition.setNode(lbl);
        pathTransition.setCycleCount(Timeline.INDEFINITE);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), lbl);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        pathTransition.play();
        fadeIn.play();
    }

}
