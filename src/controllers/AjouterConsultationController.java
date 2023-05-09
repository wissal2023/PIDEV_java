package controllers;

import entities.Consultation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ConsultationService;
import utils.MyConnection;

public class AjouterConsultationController implements Initializable {

    @FXML
    private Label lb_consltId;
    @FXML
    private TextField txt_cin, txt_nom, txt_tel, txt_poid, txt_taill, txt_imc, txt_temp, txt_press, txt_freq, txt_tx, txt_mal, txt_trait, txt_px;
    @FXML
    private TextArea txt_obsv;
    @FXML
    private ChoiceBox<String> txt_dateRDV, txt_hRDV;    
    @FXML
    private Button btn_valider;
      
    private final Connection cnx;
    private PreparedStatement pstmt; 
    
    int fiche_id;
    
    public AjouterConsultationController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }
@Override
public void initialize(URL url, ResourceBundle rb) {   
// Retrieve the Max value of the consultation id from the database
    try {
    String query = "SELECT MAX(id) as max_id FROM consultation";
    PreparedStatement preparedStatement = cnx.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
        int maxId = resultSet.getInt("max_id");
        lb_consltId.setText(String.valueOf(maxId + 1));
    }
} catch (SQLException ex) {
    ex.printStackTrace();
}
// Retrieve the dates for the appointments from the database
try {
    String query = "SELECT DISTINCT date FROM rendez_vous ORDER BY date ASC";//  WHERE etat like confirmé 
    PreparedStatement preparedStatement = cnx.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        String date = resultSet.getString("date");
        txt_dateRDV.getItems().add(date);
    }
} catch (SQLException ex) {
    ex.printStackTrace();
}
// Set up an event handler for the date RDV ChoiceBox component
txt_dateRDV.setOnAction(event -> {
    txt_hRDV.getItems().clear();
    String selectedDate = txt_dateRDV.getSelectionModel().getSelectedItem();
    if (selectedDate != null) {
        try {
            // Retrieve the available appointment times for the selected date from the database
            String query = "SELECT heure_debut FROM rendez_vous WHERE date =?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, selectedDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String time = resultSet.getString("heure_debut");
                txt_hRDV.getItems().add(time);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
});
// Set up an event handler for the patient data 
txt_hRDV.setOnAction(event -> {
    String selectedDate = txt_dateRDV.getSelectionModel().getSelectedItem();
    String selectedTime = txt_hRDV.getSelectionModel().getSelectedItem();
    if (selectedDate != null && selectedTime != null) {
        try {
            // Retrieve the patient name for the selected date and time from the database
            String query = "SELECT u.cin, u.nom, u.num_tel FROM user u JOIN rendez_vous rv ON u.id = rv.patient_id WHERE rv.date = ? AND rv.heure_debut = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, selectedDate);
            preparedStatement.setString(2, selectedTime);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String patientCin = resultSet.getString("cin");
                txt_cin.setText(patientCin);
                String patientName = resultSet.getString("nom");
                txt_nom.setText(patientName);
                String patientTel = resultSet.getString("num_tel");
                txt_tel.setText(patientTel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
});

//event to calculate the imc after entering the values of poid and taille
    txt_poid.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.isEmpty() && !txt_taill.getText().isEmpty()) {
        calculateIMC();
    }});
    txt_taill.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.isEmpty() && !txt_poid.getText().isEmpty()) {
        calculateIMC();
    } });

} 
public void setFicheId(int id){
    this.fiche_id = id;
}
private void calculateIMC() {
    try {
        float poid = Float.parseFloat(txt_poid.getText());
        float tail = Float.parseFloat(txt_taill.getText());
        if (poid <= 0 || tail <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Les valeurs de poids et taille doivent être positives et non nulles.");
            alert.showAndWait();
            return;
            }
            double imc = poid / (tail * tail);
            String imcString = String.format("%.2f", imc);
            imcString = imcString.replace(',', '.'); // replace comma with period
            txt_imc.setText(imcString);
        }catch (NumberFormatException e) {
            // Show an error message if the entered values are not numeric
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Les valeurs de poids et taille doivent être des nombres.");
            alert.showAndWait();
        }
}    
    
// ******************** buton valider ************ +  control de saisie *********
@FXML
private void Valider(ActionEvent event) throws Exception {
   try{
    if(txt_poid.getText().equals("") && (txt_taill.getText().equals("")) && (txt_imc.getText().equals(""))) {   
    }else if(txt_press.getText().isEmpty() || !txt_press.getText().matches("\\d+")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez entrez la pression arterielle du patient");
        alert.showAndWait();  
    }else if(txt_freq.getText().isEmpty() || !txt_freq.getText().matches("\\d+")){
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
    }else{     
        Consultation c = new Consultation(Float.parseFloat(txt_poid.getText()),Float.parseFloat(txt_taill.getText()),
        Float.parseFloat(txt_imc.getText()), Float.parseFloat(txt_temp.getText()),
        Float.parseFloat(txt_px.getText()), Float.parseFloat(txt_press.getText()),
        Float.parseFloat(txt_freq.getText()), Float.parseFloat(txt_tx.getText()),
        txt_mal.getText(), txt_trait.getText(),txt_obsv.getText());
        
        System.out.println("fiche_id controller"+fiche_id);
        c.setFiche_medicale_id(fiche_id);

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

   }catch(Exception ex){
       Alert alert = new Alert(Alert.AlertType.ERROR," Les informations sont Invalides ou incompletes, Veuillez les verifiers ",ButtonType.CLOSE);
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
