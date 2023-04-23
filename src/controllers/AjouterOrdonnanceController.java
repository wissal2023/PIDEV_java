
package controllers;

import entities.Consultation;
import entities.Ordonnance;
import entities.RendezVous;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OrdonnanceService;
import services.RendezVousService;
import utils.MyConnection;

public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_mal;
    @FXML
    private TextField txt_trait;
    @FXML
    private TextField dateRDV;
    @FXML
    private TextField hDebut;
    @FXML
    private TextField txt_codeOrd;
    
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_med;
    @FXML
    private TextField txt_dos;
    @FXML
    private TextField txt_nbr;
    
    @FXML
    private Button btn_valider;
    @FXML
    private Button BT_reset;
   
    private Consultation selectedConsultation;
    private final Connection cnx;
    private PreparedStatement ste;
    
    public AjouterOrdonnanceController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        if (selectedConsultation != null) {
            txt_id.setText(String.valueOf(selectedConsultation.getIdConslt()));
            txt_mal.setText(selectedConsultation.getMaladie());
            txt_trait.setText(selectedConsultation.getTraitement());
        }
        
//UUID.randomUUID() generates a unique identifier,substring is used to take only the first 10 characters
        String codeOrdonnance = UUID.randomUUID().toString().substring(0, 10);
        txt_codeOrd.setText(codeOrdonnance);
   
    }    
    
    @FXML
    private void Valider(ActionEvent event) throws Exception {
       try{
        if(txt_desc.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("you need to check the inserted value, it must be caracters only");
            alert.showAndWait(); 
        }else if(txt_med.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le medicament poue ce patient, you need to check the inserted value, it must be caracters only");
            alert.showAndWait(); 
        }else if(txt_dos.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le dosage du medicament, you need to check the inserted value it must be numbers only");
            alert.showAndWait(); 
        }else if(txt_nbr.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le nombre de jours de medicament a prendre por le patient, you need to check the inserted value it must be numbers only");
            alert.showAndWait();  
        }else{     
            Ordonnance ord = new Ordonnance(txt_desc.getText(), txt_med.getText(), Integer.parseInt(txt_dos.getText()), Integer.parseInt(txt_nbr.getText()));
            
            OrdonnanceService ordService = new OrdonnanceService();
            ordService.ajouterOrdonnance(ord);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AJOUT ORDONNANCE AVEC SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("ordonnance ajouté au consultation avec succès");
            alert.showAndWait();        
            Stage stage = (Stage) btn_valider.getScene().getWindow();
            stage.close();
             
        }
       }
        catch(Exception ex){
            ex.printStackTrace();
        }
       
        String title = "succes ";
        String message = "Ordonnance ajouté avec succes";
    }
    
        // ************************** buton reset *********************
    @FXML
    private void Reset (ActionEvent event)  {
        
        txt_desc.setText("");
        txt_med.setText("");
        txt_dos.setText("");
        txt_nbr.setText("");
    }     

   public void setSelectedConsultation(Consultation consultation) {
    
       //selectedConsultation = consultation;
       txt_id.setText(Integer.toString(consultation.getIdConslt()));
        txt_mal.setText(consultation.getMaladie());
        txt_trait.setText(consultation.getTraitement());
       
    }
}
