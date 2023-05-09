package services;

import entities.Consultation;
import entities.Ordonnance;
import entities.UserC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utils.MyConnection;

public class OrdonnanceService {
private final Connection cnx;
private PreparedStatement  pStmt;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
public OrdonnanceService() {
    cnx = MyConnection.getInstance().getCnx();
}    
// --------------------------------afficher---------------------------------------------------
public List<Ordonnance> showOrdonnance() {
    List<Ordonnance> ordonnances = new ArrayList<>();
    String req = "SELECT * FROM ordonnance";
    try {
        pStmt = cnx.prepareStatement(req);
        ResultSet result = pStmt.executeQuery();
        while(result.next()) {
            Ordonnance o = new Ordonnance();
            o.setId(result.getInt("id"));
            o.setDescription(result.getString("description"));
            o.setCode_ordonnance(result.getString("code_ordonnance"));
            o.setMedicaments(result.getString("medicaments"));
            o.setDosage(result.getInt("dosage"));
            o.setNombre_jours(result.getInt("nombre_jours"));
            o.setDate_de_creation(result.getDate("date_de_creation"));
            o.setDate_de_modification(result.getDate("date_de_modification"));
            ordonnances.add(o);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ordonnances;
}
// --------------------------------ADD ordonnance ---------------------------------------------------
public void ajouterOrdonnance(Ordonnance o, Consultation consultation) {
    String ordonnanceReq = "INSERT INTO ordonnance (description, code_ordonnance, medicaments, dosage, nombre_jours, date_de_creation, date_de_modification) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String consultationReq = "UPDATE consultation SET ordonnance_id = ? WHERE id = ?";
    
    try {
        // insert the ordonnance into the ordonnance table
        pStmt = cnx.prepareStatement(ordonnanceReq, Statement.RETURN_GENERATED_KEYS);
        pStmt.setString(1, o.getDescription());
        pStmt.setString(2, o.getCode_ordonnance());
        pStmt.setString(3, o.getMedicaments());
        pStmt.setInt(4, o.getDosage());
        pStmt.setInt(5, o.getNombre_jours());
        pStmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
        pStmt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
        pStmt.executeUpdate();        
        // get the id of the newly inserted ordonnance
        int ordonnanceId = 0;
        ResultSet rs = pStmt.getGeneratedKeys();
        if (rs.next()) {
            ordonnanceId = rs.getInt(1);
        }        
        // update the consultation with the ordonnance_id
        if (consultation != null) {
            pStmt = cnx.prepareStatement(consultationReq);
            pStmt.setInt(1, ordonnanceId);
            pStmt.setInt(2, consultation.getIdConslt());
            pStmt.executeUpdate();
        }
        
       System.out.println("Ordonnance ajoutées avec succès.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

// --------------------------------editer---------------------------------------------------
public void editerOrdonnance(Ordonnance o) {
    String req = "UPDATE ordonnance SET description = ?, code_ordonnance = ?, medicaments = ?, dosage = ?, nombre_jours = ?,date_de_creation=?, date_de_modification = NOW() WHERE id = ?";
    try {
        pStmt = cnx.prepareStatement(req);
        pStmt.setString(1, o.getDescription());
        pStmt.setString(2, o.getCode_ordonnance());
        pStmt.setString(3, o.getMedicaments());
        pStmt.setInt(4, o.getDosage());
        pStmt.setInt(5, o.getNombre_jours());
        pStmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
        pStmt.setInt(7, o.getId());
        pStmt.executeUpdate();
        System.out.println("Ordonnance modifiée avec succès.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
// --------------------------------supprimer consultation---------------------------------------------------
public void deleteOrdonnance(int idOrdonnance) {
    String req = "DELETE FROM ordonnance WHERE id = ?";
    try {
        pStmt = cnx.prepareStatement(req);
        pStmt.setInt(1, idOrdonnance);
        pStmt.executeUpdate();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Supprimer ordonnance");
        alert1.setContentText("L'ordonnance " + idOrdonnance + " a été supprimée avec succès");
        Optional<ButtonType> answer1 = alert1.showAndWait();
        System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erreur lors de la suppression de l'ordonnance");
        }
}

public UserC findPatient(int patientId) throws SQLException{
        String request = "SELECT u.* " +
                       "FROM User u " +
                       "JOIN rendez_vous rv ON u.id = rv.patient_id " +
                       "WHERE rv.patient_id ="+ patientId ;
        
        UserC user = null;
         try {
         pStmt = cnx.prepareStatement(request);
         ResultSet resultSet = pStmt.executeQuery();
        while (resultSet.next()) {
            
            System.out.println("resultSet"+resultSet.getInt("id"));
            user = new UserC(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("cin"),resultSet.getString("image"));

        }
        } catch (SQLException ex) {
            System.out.println(ex);
                    
        }
           System.out.println(user);
         return user;
    }
       



}

