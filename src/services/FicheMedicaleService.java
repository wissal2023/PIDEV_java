package services;

import entities.Consultation;
import entities.FicheMedicale;
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


public class FicheMedicaleService {
    
private final Connection cnx;
private PreparedStatement  pStmt;
public FicheMedicaleService() {
       cnx = MyConnection.getInstance().getCnx();
}
//--------------------------------afficher fiche medicale---------------------------------------------------
public List<FicheMedicale> showFicheMedicale() {
   
    List<FicheMedicale> fiches = new ArrayList<>();
    try {
        String sql = "SELECT * FROM fiche_medicale";
        pStmt = cnx.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            FicheMedicale fiche = new FicheMedicale(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getString("allergies"),
                rs.getString("pathologie"),
                rs.getTimestamp("date_de_creation"),
                rs.getTimestamp("date_de_modification")
            );
            fiches.add(fiche);
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return fiches;
}
// --------------------------------ADD Fiche Medicale  ---------------------------------------------------
public void ajouterFicheMed(FicheMedicale fiche) {
   
    Date dateCreation = new Date(fiche.getDate_de_creation().getTime());
    try {
        String sql = "INSERT INTO fiche_medicale (description, allergies, pathologie, date_de_creation) "
                + "VALUES (?, ?, ?, ?)";
        pStmt = cnx.prepareStatement(sql);
        pStmt.setString(1, fiche.getDescription());
        pStmt.setString(2, fiche.getAllergies());
        pStmt.setString(3, fiche.getPathologie());
         pStmt.setDate(4, dateCreation);
        pStmt.executeUpdate();
        System.out.println("Fiche médicale ajoutée avec succès.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
// --------------------------------editer---------------------------------------------------
public void editerFicheMed(FicheMedicale fiche) {    
    try {
        String sql = "UPDATE fiche_medicale SET description = ?, allergies = ?, pathologie = ?, "
        + "date_de_modification = NOW() WHERE id = ?";
        pStmt = cnx.prepareStatement(sql);
        pStmt.setString(1, fiche.getDescription());
        pStmt.setString(2, fiche.getAllergies());
        pStmt.setString(3, fiche.getPathologie());
        pStmt.setInt(4, fiche.getId());
        pStmt.executeUpdate();
        System.out.println("Fiche médicale modifiée avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification de la Fiche Medicale:" + ex.getMessage());
    }
}
//--------------------------------supprimer fiche_medicale---------------------------------------------------
public void deleteFicheMedicale(int id) {
    try {
        String sql = "DELETE FROM fiche_medicale WHERE id = ?";
        pStmt = cnx.prepareStatement(sql);
        pStmt.setInt(1, id);
        pStmt.executeUpdate();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Supprimer Fiche Medicale ");
        alert1.setContentText("Le Fiche Medicale " + id + " a été supprimée avec succès");
        Optional<ButtonType> answer1 = alert1.showAndWait();
        System.out.println("Fiche médicale supprimée avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression du Fiche Medicale:" + ex.getMessage());
    }
} 
 //--------------------------------find fich medicale by id ---------------------------------------------------
/*
public List<Consultation> getConsultationsByFicheMedicaleId(int fichMedId) {
    List<Consultation> consultations = new ArrayList<>();
    try {
        String query = "SELECT id, fiche_medicale_id FROM Consultation WHERE fiche_medicale_id =?" + fichMedId;
        pStmt = cnx.prepareStatement(query);
        pStmt.setInt(1, fichMedId);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setIdConslt(rs.getInt("idConslt"));
            consultation.setFiche_medicale_id(rs.getInt("fiche_medicale_id"));
            consultations.add(consultation);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return consultations;
}

*/
public FicheMedicale findRdvById(int fichId) throws SQLException{
        String request = "SELECT * FROM fich_medicale where id="+fichId+"";
        FicheMedicale fich = null;
         try {
         pStmt = cnx.prepareStatement(request);
         ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            
            System.out.println("resultSet"+rs.getInt("id"));
            fich = new FicheMedicale(fichId,rs.getString("description"),rs.getString("allergies"),rs.getString("pathologie"),rs.getDate("date_de_creation"),rs.getDate("date_de_modification"));

        }
        } catch (SQLException ex) {
            System.out.println(ex);
                    
        }
         return fich;
    }



/*

public FicheMedicale findfichById(int idFich) throws SQLException{   
    String request = "SELECT * FROM fiche_medicale where fichMed_id=" +idFich+"";
    FicheMedicale fich = null;
     try {
       PreparedStatement preparedStatement = cnx.prepareStatement(request);
     ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        System.out.println("resultSet"+resultSet.getInt("fichMed_id"));
        fich = new FicheMedicale(idFich,resultSet.getString("allergies"),resultSet.getString("pathologie"),resultSet.getDate("date_de_creation"));
       }
    }catch (SQLException ex) {
        System.out.println(ex);
    }
     return fich;
}   

*/
}
