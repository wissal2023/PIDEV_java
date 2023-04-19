
package gui;

import entities.Ordonnance;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.OrdonnanceService;
import utils.MyConnection;


public class AfficherOrdonnanceController implements Initializable {

    @FXML
    private Tab tabOrd;

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

    @FXML
    private Button BT_AjoutOrd;

    @FXML
    private Button BT_ModifOrd;

    @FXML
    private Button BT_DelOrd;

    @FXML
    private Button BT_print;

    @FXML
    private TextField search_ord;
    
    private final Connection cnx;
    private PreparedStatement pste;
    
    public AfficherOrdonnanceController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Ordonnance> listOrdonnance ;
        OrdonnanceService consultServ = new OrdonnanceService();
        listOrdonnance= consultServ.showOrdonnance();

        
            code_ordonnance.setCellValueFactory(new PropertyValueFactory<>("code_ordonnance"));    
            medicaments.setCellValueFactory(new PropertyValueFactory<>("medicaments"));
            dosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
            nombre_jours.setCellValueFactory(new PropertyValueFactory<>("nombre_jours"));
            date_de_creation.setCellValueFactory(new PropertyValueFactory<>("date_de_creation"));
            
            tab_Ord.getItems().setAll(listOrdonnance);
    }    
    //---------------------------- load list --------------------------------------    
    private void loadData() throws SQLException {
        ObservableList<Ordonnance> listOrdonnance = FXCollections.observableArrayList();
        OrdonnanceService ordnServ = new OrdonnanceService();
        listOrdonnance.clear();
        listOrdonnance = (ObservableList<Ordonnance>) ordnServ.showOrdonnance();
        code_ordonnance.setCellValueFactory(new PropertyValueFactory<>("code_ordonnance"));    
        medicaments.setCellValueFactory(new PropertyValueFactory<>("medicaments"));
        dosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        nombre_jours.setCellValueFactory(new PropertyValueFactory<>("nombre_jours"));
        date_de_creation.setCellValueFactory(new PropertyValueFactory<>("date_de_creation"));
        
        tab_Ord.getItems().setAll(listOrdonnance);
    }
    private void handleRefresh(ActionEvent event) throws SQLException {
        loadData();
        
    } 
    
}
