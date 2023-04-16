package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AfficherDashboardController implements Initializable {

    //@FXML
    //private ImageView imgDoc;
    @FXML
    private TextField Txt_nom;
    @FXML
    private TextField Txt_special;
    @FXML
    private TextField Txt_adress;
    @FXML
    private TextField Txt_mail;
    @FXML
    private TextField Txt_tel;
    
    @FXML
    private BarChart<String, Number> barChart;    
    @FXML
    private TextField nb_Med;
    @FXML
    private TextField nb_Tot_Consult;   
    @FXML
    private TextField Rev;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Image image = new Image(getClass().getResourceAsStream("Users/MBM info/Desktop/imgDoc.jpg"));
        //imgDoc.setImage(image);
        Txt_nom.setText("John Doe");
        Txt_special.setText("Cardiology");
        Txt_adress.setText("123 Main St");
        Txt_mail.setText("john.doe@example.com");
        Txt_tel.setText("+216 23435456");
        nb_Med.setText("3");
        nb_Tot_Consult.setText("4");
        Rev.setText("150");
    }
    
    @FXML
    private void afficherConsultation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherConsultation.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}

