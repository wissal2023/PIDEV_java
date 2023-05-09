
package controllers;

import entities.FicheMedicale;
import entities.UserC;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.FicheMedicaleService;

public class CardFichController implements Initializable {

    @FXML
    private Button BT_consul;
    @FXML
    private Circle circleImg;
    @FXML
    private Label id_Lb, nameLb, lb_allerg, lb_patho, lb_dateCons ;
    @FXML
    private Node card;
    
    private FicheMedicale ficheMedicale; 
    //UserC currentUser;
    
    FicheMedicaleService fichService;
    int fiche_id = 0;

@Override
public void initialize(URL url, ResourceBundle rb) {
    
}  
void setFicheMedicale(FicheMedicale ficheMedicale) {    
    this.ficheMedicale = ficheMedicale;
    this.fiche_id = ficheMedicale.getId();
    circleImg.setStroke(Color.SEAGREEN);
    Image im = new Image("/utils/patient9.jpg",false);          
    circleImg.setFill(new ImagePattern(im)); 
    id_Lb.setText(Integer.toString(ficheMedicale.getId()));// afficher id fiche medicale
    nameLb.setText(ficheMedicale.getDescription());
    lb_allerg.setText(ficheMedicale.getAllergies());
    lb_patho.setText(ficheMedicale.getPathologie());
    lb_dateCons.setText(ficheMedicale.getDate_de_creation().toString());
}
//----------------------editer buton from the context menu ----------------------------------   
@FXML
void editerFich(ActionEvent event) throws SQLException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierFichMed.fxml"));
        Parent parent = loader.load();
        ModifierFichMedController controller = (ModifierFichMedController) loader.getController();
        controller.inflateUI(this.ficheMedicale);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modifier Fiche Medicale");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.setOnHiding((e) -> {
            try {
                FicheMedicaleService fichService = new FicheMedicaleService();
                FicheMedicale fichModif = fichService.findRdvById(this.ficheMedicale.getId());
                System.out.println(fichModif);
               // setData(fichModif);
            }catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        }catch (IOException e) {
            e.printStackTrace();
        }
}   /*
public void setData(FicheMedicale fich){
        this.ficheMedicale = fich;
        UserC patient = null;
        UserC medecin = null;
        System.out.println(ficheMedicale.getId());
        int maxLength = 20; // set the maximum length of the text
        //circleImg.setStroke(Color.SEAGREEN);

        if(currentUser.getRoles().equals("[\"ROLE_MEDECIN\"]")){
           fichService = new FicheMedicaleService();
            try {
                 patient = fichService.findPatient(fich.getIdPatient());
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            
        }
        //circleImg.setFill(new ImagePattern(im));
        nameLb.setText(rdv.getFullName().substring(0, 1).toUpperCase() +rdv.getFullName().substring(1));
        dateLb.setText(rdv.getDate().toString());
        heureLb.setText(rdv.getHeureDebut().toString().substring(0, 5)+"-" + rdv.getHeureFin().toString().substring(0, 5));
        symptomesLb.setText(rdv.getSymptomes().substring(0, Math.min(rdv.getSymptomes().length(), maxLength)));
        if (rdv.getSymptomes().length() > maxLength) {
        Tooltip tooltip = new Tooltip(rdv.getSymptomes()); // create a tooltip to show the full text
        symptomesLb.setTooltip(tooltip); // set the tooltip on the label
        System.out.println(rendezVous.getEtat());
        }
    }
}
        */

    
 /*   
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierFichMed.fxml"));
        Parent parent = loader.load();
        ModifierFichMedController controller = loader.getController();        
        // Pass the selected fiche medicale to the ModifierFichMedController
        //controller.setFicheMedicale(getFicheMedicale());        
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modifier Fiche Medicale");
        stage.setScene(new Scene(parent));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
*/

//----------------------supprimer buton from the context menu ----------------------------------    
@FXML
void delFich(ActionEvent event) throws SQLException {  
    FicheMedicale ficheMedicale = getFicheMedicale();
    if (ficheMedicale != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette fiche medicale ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FicheMedicaleService fichService = new FicheMedicaleService();
            fichService.deleteFicheMedicale(ficheMedicale.getId());            
        }
    }    
}
private FicheMedicale getFicheMedicale() {
    if (ficheMedicale != null) {
        return ficheMedicale;
    } else {
        return null;
    }
}
 //---------------------- buton Consultation----------------------------------    
@FXML
private void AjouterConsultation(ActionEvent event) throws IOException {   
    Stage afficherConsultationStage = (Stage) BT_consul.getScene().getWindow();   
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterConsultation.fxml"));
    Parent root = loader.load();
    AjouterConsultationController controller = (AjouterConsultationController)loader.getController();
    Stage stage = new Stage(StageStyle.DECORATED);
    stage.setTitle("Ajouter Consultation");
    stage.setScene(new Scene(root));
    stage.show();
    System.out.println("fiche_id:"+this.fiche_id);
    System.out.println("con"+controller);
    controller.setFicheId(this.fiche_id);

} 
    

    
}
