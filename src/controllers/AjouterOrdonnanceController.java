
package controllers;

import entities.Consultation;
import entities.Ordonnance;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OrdonnanceService;
import utils.MyConnection;

public class AjouterOrdonnanceController implements Initializable {
@FXML
private TextField txt_poi, txt_tail, txt_temp, txt_mal,txt_trait, txt_codeOrd, txt_desc, txt_med, txt_dos, txt_nbr;
@FXML
private DatePicker  txt_date;
@FXML
private Tab tabOrd;
@FXML
private Button btn_valider;
   
private Consultation selectedConsultation;
int ord_id;
private final Connection cnx;
private PreparedStatement ste;

public AjouterOrdonnanceController() {
    MyConnection bd = MyConnection.getInstance();
    cnx=bd.getCnx();
}  
@Override
public void initialize(URL url, ResourceBundle rb) { 
    
    txt_date.setValue(LocalDate.now());
    // generates a unique identifier,substring is used to take only the first 10 characters   
    String codeOrdonnance = UUID.randomUUID().toString().substring(0, 10);    
    txt_codeOrd.setText(codeOrdonnance);     
}
 
public void setOrdId(int id){
    this.ord_id = id;
}
public void inflateUI(Consultation cons) {
    // set the values of poids, taille, and temperature, maladie, traitement in the appropriate fields
    txt_poi.setText(Float.toString(cons.getPoids()));
    txt_tail.setText(Float.toString(cons.getTaille()));
    txt_temp.setText(Float.toString(cons.getTemperature()));
    txt_mal.setText(cons.getMaladie());
    txt_trait.setText(cons.getTraitement());
}
@FXML
private void Valider(ActionEvent event) throws IOException, ParseException{ 
    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //Date date = dateFormat.parse(txt_date.getValue().toString());
    LocalDate localDate = txt_date.getValue();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(localDate.toString());

    if (txt_desc.getText().isEmpty() || !txt_desc.getText().matches("[a-zA-Z]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("lettres only");
        alert.setContentText("Description should only contain letters!");
        alert.showAndWait();
    }else if (txt_med.getText().isEmpty() || !txt_med.getText().matches("[a-zA-Z]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("lettres only");
        alert.setContentText("Medicament should only contain letters!");
        alert.showAndWait();
    }else if (txt_dos.getText().isEmpty() || !txt_dos.getText().matches("[0-9]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("chiffres only");
        alert.setContentText("Dose should only contain numbers!");
        alert.showAndWait();
    }else if (txt_nbr.getText().isEmpty() || !txt_nbr.getText().matches("[0-9]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("chiffres only");
        alert.setContentText("Nombre should only contain numbers!");
        alert.showAndWait(); 
    }else{        
        Ordonnance ord = new Ordonnance(txt_codeOrd.getText(), txt_desc.getText(), txt_med.getText(), Integer.parseInt(txt_dos.getText()), Integer.parseInt(txt_nbr.getText()), date);       
        System.out.println("consultation controller:"+ selectedConsultation);
        //ord.setConsultation(selectedConsultation);
        
        OrdonnanceService ordService = new OrdonnanceService();
        ordService.ajouterOrdonnance(ord, selectedConsultation);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AJOUT ORDONNANCE AVEC SUCCES");
        alert.setHeaderText(null);
        alert.setContentText("ordonnance ajoutée à la consultation avec succès");
        alert.showAndWait();
        Stage stage = (Stage) btn_valider.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherConsultation.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
        
}
}    
// ************************** buton reset *********************
@FXML
private void Reset (ActionEvent event)  {

    txt_desc.setText("");
    txt_med.setText("");
    txt_dos.setText("");
    txt_nbr.setText("");
}    
   
}
