/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consultation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import services.ConsultationService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MBM info
 */
public class AjouterConsultationController implements Initializable {

    @FXML
    private TextField Txt_nom;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_poid;
    @FXML
    private TextField txt_taill;
    @FXML
    private TextField txt_imc;
    @FXML
    private TextField txt_temp;
    @FXML
    private TextField txt_press;
    @FXML
    private TextField txt_freq;
    @FXML
    private TextField txt_tx;
    @FXML
    private TextField txt_mal;
    @FXML
    private TextField txt_trait;
    @FXML
    private TextField txt_px;
    @FXML
    private TextField txt_obsv;

    private final Connection cnx;
    private PreparedStatement ste;
    
    public AjouterConsultationController() {
        MyConnection bd=MyConnection.getInstance();
        cnx=bd.getCnx();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Txt_nom.setText("John Doe");
        txt_tel.setText("23456789");
        txt_cin.setText("12345678");
    }  
    
    @FXML
    private void Valider(ActionEvent event) throws Exception {
       try{
        if(txt_poid.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le poids du patient");
            alert.showAndWait();  
        }else if(txt_poid.getText().length()<3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("la description doit comporter au moins 5 caractères");
            alert.showAndWait();  
        }else if(txt_taill.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la taille du patient");
            alert.showAndWait(); 
        }else if(txt_imc.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez l'imc du patient");
            alert.showAndWait(); 
        }else if(txt_temp.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la température du patient");
            alert.showAndWait();  
        }else if(txt_press.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la pression arterielle du patient");
            alert.showAndWait();  
        }else if(txt_freq.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la fréquence cardiaque du patient");
            alert.showAndWait();  
        }else if(txt_tx.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le taux de glycemie du patient");
            alert.showAndWait();  
        }else if(txt_mal.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez la maladie du patient");
            alert.showAndWait();    
        }else if(txt_trait.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le traitement du maladie de patient");
            alert.showAndWait();   
        }else if(txt_px.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez le prix de consultation");
            alert.showAndWait();     
        }else if(txt_obsv.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vous devez entrez votre observation sur maladie et/ou patient");
            alert.showAndWait();      
        }
        
        else{          
            
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
        }
       }
        catch(RuntimeException ex)
        {
             Alert alert = new Alert(Alert.AlertType.ERROR," Les informations sont Invalides ou incompletes Veuillez les verifiers ",ButtonType.CLOSE);
            alert.showAndWait();
        }
        String title = "succes ";
        String message = "Consultation ajouté avec succes";
    }
    
}
