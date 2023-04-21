
package controllers;

import entities.Consultation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class CardController implements Initializable {

    @FXML
    private Circle circleImg;

    @FXML
    private Button btAjoutConslt;

    @FXML
    private Button btAffichFich;

    @FXML
    private Label lb_nomP;

    @FXML
    private Label lb_dateRDV;

    @FXML
    private Label lb_hRDV;

    @FXML
    private Label dateLb1;

    @FXML
    private Label lb_Symp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        circleImg.setStroke(Color.SEAGREEN);
        Image im = new Image("/utils/patient9.jpg",false);
        circleImg.setFill(new ImagePattern(im));
    }    
    
    public void setData (Consultation con){
    
    }
}
