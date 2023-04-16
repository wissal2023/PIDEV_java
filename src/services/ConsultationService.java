package services;

import entities.Consultation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utils.MyConnection;

public class ConsultationService {
    
    private final Connection cnx;
    private PreparedStatement  pStmt;
    
    public ConsultationService() {
           cnx = MyConnection.getInstance().getCnx();
    }
// --------------------------------ADD consultation ---------------------------------------------------
    public void ajouterConsultation(Consultation c){
        
        
        String request="INSERT INTO consultation(poids, taille, imc, temperature, prix, pression_arterielle, frequence_cardiaque, taux_glycemie, maladie, traitement, observation)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
        //System.out.println(c.getPoids());

       try {
            pStmt = cnx.prepareStatement(request);
            pStmt.setFloat(1, c.getPoids());
            pStmt.setFloat(2, c.getTaille());
            pStmt.setFloat(3, c.getImc());
            pStmt.setFloat(4, c.getTemperature());
            pStmt.setFloat(5, c.getPrix());
            pStmt.setFloat(6, c.getPression_arterielle());
            pStmt.setFloat(7, c.getFrequence_cardiaque());
            pStmt.setFloat(8, c.getTaux_glycemie());
            pStmt.setString(9, c.getMaladie());
            pStmt.setString(10, c.getTraitement());
            pStmt.setString(11, c.getObservation());
            pStmt.executeUpdate();
            pStmt.close();
            pStmt.close();
            System.out.println ("succes"); 
       
       }catch (SQLException e) {
            System.out.println (e);
        }
        
    }
    
// --------------------------------editer---------------------------------------------------
    public void editerConsultation(Consultation c){
        
        //System.out.println(p.getDescription());
         String request = "UPDATE Consultation SET poids=?, taille=?, imc=?, temperature=?, prix=?,"
                 + " pression_arterielle=?, frequence_cardiaque=?, taux_glycemie=?, maladie=?, traitement=?,"
                 + " oObservation=? WHERE id=?";
    
    try {
        pStmt = cnx.prepareStatement(request);
        pStmt.setFloat(1, c.getPoids());
        pStmt.setFloat(2, c.getTaille());
        pStmt.setFloat(3, c.getImc());
        pStmt.setFloat(4, c.getTemperature());
        pStmt.setFloat(5, c.getPrix());
        pStmt.setInt(6, c.getId());
        pStmt.executeUpdate();
        System.out.println("Consultation modifiée avec succès");
    } catch (SQLException ex) {
        System.out.println(ex);
        System.out.println ("Erreur lors de la modification de la consultation");
    }
        
    }
// --------------------------------afficher---------------------------------------------------
    public List<Consultation> showConsultation() {
    List<Consultation> consultations = new ArrayList<>();
    String request = "SELECT * FROM consultation";

    try {
        pStmt = cnx.prepareStatement(request);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            Consultation c = new Consultation();
            c.setId(rs.getInt("id"));
            c.setPoids(rs.getFloat("poids"));
            c.setTaille(rs.getFloat("taille"));
            c.setImc(rs.getFloat("imc"));
            c.setTemperature(rs.getFloat("temperature"));
            c.setPrix(rs.getFloat("prix"));
            c.setPression_arterielle(rs.getFloat("pression_arterielle"));
            c.setFrequence_cardiaque(rs.getFloat("frequence_cardiaque"));
            c.setTaux_glycemie(rs.getFloat("taux_glycemie"));
            c.setMaladie(rs.getString("maladie"));
            c.setTraitement(rs.getString("traitement"));
            c.setObservation(rs.getString("observation"));
            consultations.add(c);
        }
       // pStmt.close();
        //rs.close();
    } catch (SQLException e) {
        System.out.println(e);
    }

    return consultations;
}

    
// --------------------------------supprimer consultation---------------------------------------------------
    public void deleteConsultation(int idConsult) {  

        String request = "DELETE FROM consultation WHERE id =" + idConsult;

        try {
            pStmt = cnx.prepareStatement(request);
            pStmt.executeUpdate();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supprimer consultation");
            alert1.setContentText("La consultation " + idConsult + " a été supprimée avec succès");
            Optional<ButtonType> answer1 = alert1.showAndWait();
            System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erreur lors de la suppression de la consultation");
        }
}


    
    
}
