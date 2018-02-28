
package javafxapplication2;

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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author 719454
 */
public class FXMLDocumentController implements Initializable {
    public int count = 0;
    
    @FXML
    private Label lblLondon, lblRiga, lblParis, lblBerlin, lblMoscow, lblBrussel, lblCalgary;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Label> labels = new ArrayList<>(
        Arrays.asList(lblLondon, lblRiga, lblParis, lblBerlin, lblMoscow, lblBrussel, lblCalgary));
       
           
        Timeline anim = new Timeline();
        anim.setCycleCount(50); // Animation will play 50 times
        
        int seconds = 1;
        for(int i = 0; i < labels.size(); i++){
            setTimer(i, labels.get(i));
        }
        }
        public void setTimer(int seconds, Label l) {
         Timer timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 moveLabel(l);
             }
         }, (seconds*1000));       
}
       
    
    private void moveLabel(Label lbl){
        System.out.println("foo" + count);
        PathTransition pathTransition = new PathTransition();
        //lbl.setLayoutX(25);
      //  lbl.setLayoutY(0);
        Path path = new Path();
        path.getElements().add(new MoveTo(25,-50));
        path.getElements().add(new LineTo(25, 350));
        
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setPath(path);
        pathTransition.setNode(lbl);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        
        
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), lbl);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        pathTransition.play();
        fadeIn.play();
        System.out.println("bar" + count);
        count++;
    }
    
}
