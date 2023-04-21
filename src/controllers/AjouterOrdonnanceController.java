
package controllers;

import entities.Consultation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ConsultationService;
import utils.MyConnection;

public class AjouterOrdonnanceController implements Initializable {
    
    @FXML
    private Button btn_valider;
    @FXML
    private Button BT_reset;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_tel;
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
    private TextField txt_date;
    @FXML
    private ChoiceBox<Consultation> ch_trai;
    
    private final Connection cnx;
    private PreparedStatement ste;
    
    public AjouterOrdonnanceController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void Valider(ActionEvent event) throws Exception {
       try{
        if(txt_desc.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le poids du patient");
            alert.showAndWait(); 
        }else if(txt_med.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la taille du patient");
            alert.showAndWait(); 
        }else if(txt_dos.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez l'imc du patient");
            alert.showAndWait(); 
        }else if(txt_nbr.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la température du patient");
            alert.showAndWait();  
        }else if(txt_date.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la pression arterielle du patient");
            alert.showAndWait();  
        }else if(ch_trai.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la fréquence cardiaque du patient");
            alert.showAndWait();        
        }else{     
            Consultation c = new Consultation(Float.parseFloat(txt_poid.getText()),Float.parseFloat(txt_taill.getText()),
                    Float.parseFloat(txt_imc.getText()), Float.parseFloat(txt_temp.getText()),
                    Float.parseFloat(txt_px.getText()), Float.parseFloat(txt_press.getText()),
                    Float.parseFloat(txt_freq.getText()), Float.parseFloat(txt_tx.getText()),
                    txt_mal.getText(), txt_trait.getText(),txt_obsv.getText());
            
            ConsultationService consultService = new ConsultationService();
            consultService.ajouterConsultation(c);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AJOUT AVEC SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Consultation ajouté avec succès");
            alert.showAndWait();
            
            // close the current window
            Stage stage = (Stage) btn_valider.getScene().getWindow();
            stage.close();
             
        }
       }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR," Les informations sont Invalides ou incompletes Veuillez les verifiers ",ButtonType.CLOSE);
            alert.showAndWait();
        }
       
        String title = "succes ";
        String message = "Consultation ajouté avec succes";
        
        // open the "afficherConsultation" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficherConsultation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.show();
    }
        // ************************** buton reset *********************
    @FXML
    private void Reset (ActionEvent event)  {
    
        txt_poid.setText("");
        txt_taill.setText("");
        txt_imc.setText("");
        txt_temp.setText("");
        txt_press.setText("");
        txt_freq.setText("");
        txt_tx.setText("");
        txt_mal.setText("");
        txt_trait.setText("");
        txt_px.setText("");
        txt_obsv.setText("");
    }
    
    
}
