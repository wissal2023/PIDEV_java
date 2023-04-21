
package controllers;

import entities.Consultation;
import entities.Ordonnance;
import entities.RendezVous;
import entities.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ConsultationService;
import services.OrdonnanceService;
import utils.MyConnection;



public class AfficherConsultationController implements Initializable {
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabConsult, tabOrd;
    //*************  consultation *********
    @FXML
    private TableView<Consultation> table_consult;
    @FXML
    private TableColumn<RendezVous, Integer> patient_name;    
    @FXML
    private TableColumn<Consultation, String> mal;    
    @FXML
    private TableColumn<Consultation, String> trait;    
    @FXML
    private TableColumn<Consultation, String> temp; 
    @FXML
    private TableColumn<Consultation, String> poids;
    @FXML
    private TableColumn<RendezVous, Date> date; 
            //*************  Ordonnance *********
    @FXML
    private TableView<Ordonnance> tab_Ord;
    @FXML
    private TableColumn<Ordonnance, String> code_ordonnance;
    @FXML
    private TableColumn<Ordonnance, String> medicaments;
    @FXML
    private TableColumn<Ordonnance, String> dosage;
    @FXML
    private TableColumn<Ordonnance, Integer> nombre_jours;
    @FXML
    private TableColumn<Ordonnance, Date> date_de_creation;
   
    
    private final Connection cnx;
    private PreparedStatement pste;
    
    public AfficherConsultationController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Consultation> listConsultation ;
        ConsultationService consultServ = new ConsultationService();
        listConsultation= consultServ.showConsultation();
        patient_name.setCellValueFactory(new PropertyValueFactory<>("idConslt"));
        poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
        mal.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        trait.setCellValueFactory(new PropertyValueFactory<>("traitement"));
        temp.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));//dateRDV 
        table_consult.getItems().setAll(listConsultation);
        
        List<Ordonnance> listOrdonnance ;
        OrdonnanceService ordnltServ = new OrdonnanceService();
        listOrdonnance= ordnltServ.showOrdonnance();        
        code_ordonnance.setCellValueFactory(new PropertyValueFactory<>("code_ordonnance"));    
        medicaments.setCellValueFactory(new PropertyValueFactory<>("medicaments"));
        dosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        nombre_jours.setCellValueFactory(new PropertyValueFactory<>("nombre_jours"));
        date_de_creation.setCellValueFactory(new PropertyValueFactory<>("date_de_creation"));
        tab_Ord.getItems().setAll(listOrdonnance);
        
        /*
        List<RendezVous> listRendezVous ;
        RendezVousService rdvServ = new RendezVousService();
        listRendezVous= rdvServ.showRendezVous();        
        date.setCellValueFactory(new PropertyValueFactory<>("date")); 
        
        List<User> listUser ;
        UserService userServ = new UserService();
        listUser= rdvServ.showUser();        
        name.setCellValueFactory(new PropertyValueFactory<>("nom")); 
        */
    }

    /*
    @FXML
    void AfficherConsultationController(ActionEvent event) {
       
        List<Consultation> listConsultation ;
        ConsultationService consultServ = new ConsultationService();
        listConsultation = consultServ.showConsultation();

        patient_name.setCellValueFactory(new PropertyValueFactory<>("patient_id"));    
        mal.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        trait.setCellValueFactory(new PropertyValueFactory<>("traitement"));
        temp.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        table_consult.getItems().setAll(listConsultation);

    }
    */
    
//---------------------------------------- buton ajouter -------------------------------------------
    
    @FXML
    private void AjouterConsultation(ActionEvent event) throws IOException {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterConsultation.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void AjouterOrdonnance(ActionEvent event) throws IOException {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterOrdonnance.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    

//----------------------------------------- buton modifier --------------------------------------------
    @FXML
    private void editerConsultation(ActionEvent event) throws IOException {
        Consultation selectedForEdit = table_consult.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierConsultation.fxml"));
        Parent parent = loader.load();
        ModifierConsultationController controller = (ModifierConsultationController) loader.getController();
        controller.inflateUI(selectedForEdit);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modifier Consultation");
        stage.setScene(new Scene(parent));
        stage.show();
        
    }
//----------------------------------------------- buton supprimer ---------------------------------------------
    @FXML
     private void supprimerConsultation(ActionEvent event) throws IOException, SQLException {
        Consultation selectedForDelete = table_consult.getSelectionModel().getSelectedItem();
        System.out.println("selected consultation id"+selectedForDelete.getIdConslt());
        ConsultationService service = new ConsultationService();
        service.deleteConsultation(selectedForDelete.getIdConslt());

        // Remove the selected consultation from the table view
        table_consult.getItems().remove(selectedForDelete);

        String title = "Succes de suppression";
        String message = "ARE YOU SURE YOU WANT TO DELETE THIS CONSULTATION";
        
    }
    
//--------------------------------------------------- buton telecharger --------------------------------------------
    @FXML
     public void download() throws IOException {
       
        FileChooser fileChooser = new FileChooser();// Create a new FileChooser object
        fileChooser.setTitle("Save List Consultations");// Set the title of the dialog box       
        fileChooser.setInitialFileName("List Consultations.xlsx");// Set the default file name
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");  // Set the file extension filter
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());// Get the file path from the user     
        createExcelFile(file.getAbsolutePath()); // Call the method to create the Excel file and pass the file path
    }

    private void createExcelFile(String filePath) throws IOException {
 
        Workbook workbook = new XSSFWorkbook(); // Create a new sheet 
        Sheet sheet = workbook.createSheet("Consultations"); // Create a new sheet
        Row headerRow = sheet.createRow(0); // Create a header row
            headerRow.createCell(0).setCellValue("Poids");
            headerRow.createCell(1).setCellValue("Taille");
            headerRow.createCell(2).setCellValue("IMC");
            headerRow.createCell(3).setCellValue("Température");
            headerRow.createCell(4).setCellValue("Prix");
            headerRow.createCell(5).setCellValue("Pression artérielle");
            headerRow.createCell(6).setCellValue("Fréquence cardiaque");
            headerRow.createCell(7).setCellValue("Taux de glycémie");
            headerRow.createCell(8).setCellValue("Maladie");
            headerRow.createCell(9).setCellValue("Traitement");
            headerRow.createCell(10).setCellValue("Observation");
            
            // Add data to the sheet
            int rowNum = 1;
            
            List<Consultation> listConsultation ;
            ConsultationService consultServ = new ConsultationService();
            listConsultation= consultServ.showConsultation();
            for (Consultation consultation : listConsultation) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(consultation.getPoids());
                row.createCell(1).setCellValue(consultation.getTaille());
                row.createCell(2).setCellValue(consultation.getImc());
                row.createCell(3).setCellValue(consultation.getTemperature());
                row.createCell(4).setCellValue(consultation.getPrix());
                row.createCell(5).setCellValue(consultation.getPression_arterielle());
                row.createCell(6).setCellValue(consultation.getFrequence_cardiaque());
                row.createCell(7).setCellValue(consultation.getTaux_glycemie());
                row.createCell(8).setCellValue(consultation.getMaladie());
                row.createCell(9).setCellValue(consultation.getTraitement());
                row.createCell(10).setCellValue(consultation.getObservation());
            }   // Auto-size the columns
            
            for (int i = 0; i < 11; i++) {
                sheet.autoSizeColumn(i);
            }  
            
        try (FileOutputStream fileOut = new FileOutputStream(filePath) // Save the workbook to the specified file path
        ) {
            workbook.write(fileOut);
            // Close the workbook
        }
    }
       
        
    /*  ************************First method******************************  
     
        
        
        ************************************************SECOND METHOD***************************************
        
        try {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Consultations to Excel File");
        fileChooser.setInitialFileName("List Consultations.xlsx");// Set the default file name
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet("Consultations");
                
                ObservableList<Consultation> consultations = table_consult.getItems();
                
                // Create header row
                XSSFRow headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("id");
                headerRow.createCell(1).setCellValue("Temperature");
                headerRow.createCell(2).setCellValue("Maladie");
                headerRow.createCell(3).setCellValue("Traitement");
                
                // Create data rows
                int rowIndex = 1;
                
                for (Consultation consultation : consultations) {
                    XSSFRow dataRow = sheet.createRow(rowIndex++);
                        dataRow.createCell(0).setCellValue("id");
                        dataRow.createCell(1).setCellValue("Temperature");
                        dataRow.createCell(2).setCellValue("Maladie");
                        dataRow.createCell(3).setCellValue("Traitement");
                        dataRow.createCell(4).setCellValue("Prix");
                }
                
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    workbook.write(fileOut);
                }
            }
}
        } catch (IOException e) {
            System.out.println("this is the error msg " +e.getMessage());
        }
        
   ****************************THIRD METHOD *****************************
         try {
            String query = " Select * from consultation";
            pste = cnx.prepareStatement(query);
            try (ResultSet rs = pste.executeQuery()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Consultations");
                XSSFRow headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("id");
                headerRow.createCell(1).setCellValue("Temperature");
                headerRow.createCell(2).setCellValue("Maladie");
                headerRow.createCell(3).setCellValue("Traitement");
            
                sheet.autoSizeColumn(1);
                sheet.setZoom(150);
                
                int indx = 1;
                while(rs.next()){
                    XSSFRow dataRow = sheet.createRow(indx);
                    dataRow.createCell(0).setCellValue(rs.getString("id"));
                    dataRow.createCell(1).setCellValue(rs.getString("Temperature"));
                    dataRow.createCell(2).setCellValue(rs.getString("Maladie"));
                    dataRow.createCell(3).setCellValue(rs.getString("Traitement"));
                    indx++;                
                }
                try (FileOutputStream fileOut = new FileOutputStream("listConsultation.xlsx")) {
                    workbook.write(fileOut);
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("telechargement");
                alert.setHeaderText(null);
                alert.setContentText("Consultation exporté avec succes");
                alert.showAndWait();
                
                pste.close();
            }
                
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }

  }        
*/
        
    


}
