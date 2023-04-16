
package gui;

import entities.Consultation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ConsultationService;

public class AfficherConsultationController implements Initializable {

    @FXML
    private TableView<Consultation> table_consult;
    @FXML
    private TableColumn<Consultation, Integer> consult_id;    
    @FXML
    private TableColumn<Consultation, String> symp;    
    @FXML
    private TableColumn<Consultation, String> trait;    
    @FXML
    private TableColumn<Consultation, String> med;    
    @FXML
    private TableColumn<Consultation, Date> date_consultation; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Consultation> listConsultation ;
        ConsultationService consultServ = new ConsultationService();
        listConsultation= consultServ.showConsultation();
            consult_id.setCellValueFactory(new PropertyValueFactory<>("id"));    
            symp.setCellValueFactory(new PropertyValueFactory<>("maladie"));
            trait.setCellValueFactory(new PropertyValueFactory<>("traitement"));
            med.setCellValueFactory(new PropertyValueFactory<>("temperature"));
            date_consultation.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));
            table_consult.getItems().setAll(listConsultation);
    }

    @FXML
    private void ajouterConsultationAction(ActionEvent event)  {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterConsultation.fxml"));
        Parent parent = loader.load();
        AjouterConsultationController controller = (AjouterConsultationController) loader.getController();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Ajouter Consultation");
        stage.setScene(new Scene(parent));
        stage.show();
                 stage.setOnHiding((e) -> {
                try {
                    handleRefresh(new ActionEvent());
                    loadData();
                   
                    
                }catch (SQLException ex) {
                    System.out.println(ex);
                }
            });
        }catch(IOException ex ){
            
        }

    }
    
    @FXML
     private void supprimerConsultation(ActionEvent event) throws IOException, SQLException {
        Consultation selectedForDelete = table_consult.getSelectionModel().getSelectedItem();
        System.out.println("selected consultation id"+selectedForDelete.getId());
        //ConsultationService.deleteConsultation(selectedForDelete.getId());
        loadData();
        handleRefresh(new ActionEvent());
        loadData();
        String title = "succes delet ";
        String message = "consultation supprimé avec succes";
    }

     
    @FXML
    private void editerConsultation(ActionEvent event) throws IOException {
        Consultation selectedForEdit = table_consult.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierConsultation.fxml"));
            Parent parent = loader.load();

            ModifierConsultationController controller = (ModifierConsultationController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Modifier Consultation");
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding((e) -> {
                try {
                    handleRefresh(new ActionEvent());
                    loadData();
                   
                    
                }catch (SQLException ex) {
                    System.out.println(ex);
                }
            });
            
        }
        catch (IOException e) {
        }
        
    }
    
   //---------------------------- load list --------------------------------------    
    private void loadData() throws SQLException {
        ObservableList<Consultation> listConsultation = FXCollections.observableArrayList();
        ConsultationService consultServ = new ConsultationService();
        listConsultation.clear();
        listConsultation = (ObservableList<Consultation>) consultServ.showConsultation();
        consult_id.setCellValueFactory(new PropertyValueFactory<>("id"));    
        symp.setCellValueFactory(new PropertyValueFactory<>("symptome"));
        trait.setCellValueFactory(new PropertyValueFactory<>("traitement"));
        med.setCellValueFactory(new PropertyValueFactory<>("Medicament"));
        date_consultation.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));
        table_consult.getItems().setAll(listConsultation);
    }
    private void handleRefresh(ActionEvent event) throws SQLException {
        loadData();
        
    } 


    
    
         
    


}    
    

