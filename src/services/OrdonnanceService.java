package services;

import entities.Ordonnance;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utils.MyConnection;


public class OrdonnanceService {
        
    private final Connection cnx;
    private PreparedStatement  pStmt;
    
    public OrdonnanceService() {
           cnx = MyConnection.getInstance().getCnx();
    }
// --------------------------------ADD ordonnance ---------------------------------------------------
    public void ajouterOrdonnance(Ordonnance ord){
      
        String request="INSERT INTO ordonnance (nombre_jours, medicaments, code_ordonnance, dosage,qrCodeFilename, description, date_de_creation, date_de_modification)"
                +"VALUES(?,?,?,?,?,?,?,?) ";
        Date dateCreation = new Date(ord.getDate_de_creation().getTime());
        Date dateModification = new Date(ord.getDate_de_modification().getTime());
        //System.out.println(c.getPoids());

       try {
            pStmt = cnx.prepareStatement(request);
            pStmt.setInt(1, ord.getNombre_jours());
            pStmt.setString(2, ord.getMedicaments());
            pStmt.setString(3, ord.getCode_ordonnance());
            pStmt.setString(4, ord.getDosage());
            //pStmt.setString(5, ord.getQrCodeFilename());
            pStmt.setString(6, ord.getDescription());
            pStmt.setDate(7, dateCreation);
            pStmt.setDate(8, dateModification);
            pStmt.executeUpdate();
            pStmt.close();
            System.out.println ("succes"); 
       
       }catch (SQLException e) {
            System.out.println (e);
        }
        
    }
    
// --------------------------------editer---------------------------------------------------
    public void editerOrdonnance(Ordonnance ord){
        
         String request = "UPDATE ordonnance SET nombre_jours=?, medicaments=?, code_ordonnance=?,"
                 + " dosage=?, qrCodeFilename=?, description=?, date_de_creation=?, date_de_modification=? WHERE id=?";
        Date dateCreation = new Date(ord.getDate_de_creation().getTime());
        Date dateModification = new Date(ord.getDate_de_modification().getTime());
        
    
    try {
        pStmt = cnx.prepareStatement(request);
        pStmt.setInt(1, ord.getNombre_jours());
        pStmt.setString(2, ord.getMedicaments());
        pStmt.setString(3, ord.getCode_ordonnance());
        pStmt.setString(4, ord.getDosage());
        //pStmt.setString(5, ord.getQrCodeFilename());
        pStmt.setString(6, ord.getDescription());
        pStmt.setDate(7, dateCreation);
        pStmt.setDate(8, dateModification);
        pStmt.setInt(9, ord.getId());
        pStmt.executeUpdate();
        System.out.println("ordonnance modifiée avec succès");
    } catch (SQLException ex) {
        System.out.println(ex);
        System.out.println ("Erreur lors de la modification de la ordonnance");
    }
        
    }

// --------------------------------afficher---------------------------------------------------
    public List<Ordonnance> showOrdonnance() {
    List<Ordonnance> ordonnances = new ArrayList<>();
    String request = "SELECT * FROM ordonnance";

    try {
        pStmt = cnx.prepareStatement(request);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            Ordonnance ord = new Ordonnance();
            ord.setId(rs.getInt("id"));
            ord.setNombre_jours(rs.getInt("nombre_jours"));
            ord.setMedicaments(rs.getString("medicaments"));
            ord.setCode_ordonnance(rs.getString("code_ordonnance"));
            ord.setDosage(rs.getString("dosage"));
        //    ord.setQrCodeFilename(rs.getString("qrCodeFilename"));
            ord.setDescription(rs.getString("description"));
            ord.setDate_de_creation(rs.getDate("date_de_creation"));
            ord.setDate_de_modification(rs.getDate("date_de_modification"));
            ordonnances.add(ord);
        }
        pStmt.close();
        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return ordonnances;
}

// --------------------------------supprimer consultation---------------------------------------------------
    public void deleteOrdonnance(int idOrd) {  

        String request = "DELETE FROM ordonnance WHERE id =" + idOrd;

        try {
            pStmt = cnx.prepareStatement(request);
            pStmt.executeUpdate();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supprimer ordonnance");
            alert1.setContentText("L'ordonnance " + idOrd + " a été supprimée avec succès");
            Optional<ButtonType> answer1 = alert1.showAndWait();
            System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erreur lors de la suppression de l'ordonnance");
        }
}
    





}

