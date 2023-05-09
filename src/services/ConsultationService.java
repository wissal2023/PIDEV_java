package services;

import entities.Consultation;
import entities.Ordonnance;
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
// --------------------------------afficher---------------------------------------------------
public List<Consultation> showConsultation() {
    List<Consultation> consultations = new ArrayList<>();
    String query = "SELECT * FROM consultation";
    try {
        pStmt = cnx.prepareStatement(query);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            int consultId = rs.getInt("id");
            int ficheMedId = rs.getInt("id");
            int ordonnanceId = rs.getInt("id");
            Float poids = rs.getFloat("poids");
            Float taille = rs.getFloat("taille");
            Float imc = rs.getFloat("imc");
            Float temperature = rs.getFloat("temperature");
            Float prix = rs.getFloat("prix");
            Float pressionArterielle = rs.getFloat("pression_arterielle");
            Float frequenceCardiaque = rs.getFloat("frequence_cardiaque");
            Float tauxGlycemie = rs.getFloat("taux_glycemie");
            String maladie = rs.getString("maladie");
            String traitement = rs.getString("traitement");
            String observation = rs.getString("observation");            
            consultations.add(new Consultation(consultId, ficheMedId, ordonnanceId, poids, taille, imc, temperature, prix, pressionArterielle, frequenceCardiaque, tauxGlycemie, maladie, traitement, observation));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return consultations;
}
//--------------------------------ADD consultation ---------------------------------------------------
public void ajouterConsultation(Consultation c) {
    String query = "INSERT INTO consultation (fiche_medicale_id, poids, taille, imc, temperature, prix, pression_arterielle,"
    + " frequence_cardiaque, taux_glycemie, maladie, traitement, observation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        pStmt = cnx.prepareStatement(query);
        pStmt.setInt(1, c.getFiche_medicale_id());
        pStmt.setFloat(2, c.getPoids());
        pStmt.setFloat(3, c.getTaille());
        pStmt.setFloat(4, c.getImc());
        pStmt.setFloat(5, c.getTemperature());
        pStmt.setFloat(6, c.getPrix());
        pStmt.setFloat(7, c.getPression_arterielle());
        pStmt.setFloat(8, c.getFrequence_cardiaque());
        pStmt.setFloat(9, c.getTaux_glycemie());
        pStmt.setString(10, c.getMaladie());
        pStmt.setString(11, c.getTraitement());
        pStmt.setString(12, c.getObservation());
        pStmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
// --------------------------------Modifier---------------------------------------------------
public void editerConsultation(Consultation c) {
    try {
        String query = "UPDATE consultation SET fiche_medicale_id=?, ordonnance_id=?, poids=?, taille=?, imc=?, temperature=?, prix=?, pression_arterielle=?, frequence_cardiaque=?, taux_glycemie=?, maladie=?, traitement=?, observation=? WHERE id=?";
        pStmt = cnx.prepareStatement(query);
        pStmt.setInt(1, c.getFiche_medicale_id());
        pStmt.setInt(2, c.getOrdonnance_id());
        pStmt.setFloat(3, c.getPoids());
        pStmt.setFloat(4, c.getTaille());
        pStmt.setFloat(5, c.getImc());
        pStmt.setFloat(6, c.getTemperature());
        pStmt.setFloat(7, c.getPrix());
        pStmt.setFloat(8, c.getPression_arterielle());
        pStmt.setFloat(9, c.getFrequence_cardiaque());
        pStmt.setFloat(10, c.getTaux_glycemie());
        pStmt.setString(11, c.getMaladie());
        pStmt.setString(12, c.getTraitement());
        pStmt.setString(13, c.getObservation());
        pStmt.setInt(14, c.getIdConslt());
        pStmt.executeUpdate();
        System.out.println("Consultation updated successfully");
    } catch (SQLException ex) {
        System.out.println("Error while updating consultation: " + ex.getMessage());
    }
}  
// --------------------------------supprimer consultation---------------------------------------------------
public void deleteConsultation(int idConsult) {  
    try {
        String query = "DELETE FROM consultation WHERE id = ?";
        pStmt = cnx.prepareStatement(query);
        pStmt.setInt(1, idConsult);
        pStmt.executeUpdate();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Supprimer consultation");
        alert1.setContentText("La consultation " + idConsult + " a été supprimée avec succès");
        Optional<ButtonType> answer1 = alert1.showAndWait();
        System.out.println("Consultation supprimer avec succès");
    } catch (SQLException e) {
        System.out.println("Error deleting consultation: " + e.getMessage());
    } 
}
// -----------------get the selected consultation to add an ordonnance---------------------------------------------------
public Consultation getConsultationById(int idConslt) {
    
    ConsultationService consultServ = new ConsultationService();    
    Consultation consultation = consultServ.getConsultationById(idConslt);    
        return consultation;
}
public void updateConsultation(Consultation selectedConsultation, Ordonnance newOrdonnance) {
    
    List<Ordonnance> ordonnances = selectedConsultation.getOrdonnances();
    ordonnances.add(newOrdonnance);
    selectedConsultation.setOrdonnances(ordonnances);    
}
/*
public List<Consultation> getConsultationsWithRdvByFicheMedicaleId(int fichMedId) {
    List<Consultation> consultations = new ArrayList<>();
    try {
        String query = "SELECT c.*, r.* FROM Consultation c " +
                       "LEFT JOIN RendezVous r ON c.id = r.consultation_id " +
                       "WHERE c.fiche_medicale_id = ?";
        pStmt = cnx.prepareStatement(query);
        pStmt.setInt(1, fichMedId);
        ResultSet rs = pStmt.executeQuery();

        // Create a map to store consultations by ID
        Map<Integer, Consultation> consultationMap = new HashMap<>();
        while (rs.next()) {
            // Check if the consultation already exists in the map
            int consultationId = rs.getInt("id");
            Consultation consultation = consultationMap.get(consultationId);
            if (consultation == null) {
                // Create a new consultation object if it doesn't exist in the map
                consultation = new Consultation();
                consultation.setIdConslt(consultationId);
                consultation.setFiche_medicale_id(rs.getInt("fiche_medicale_id"));
                consultation.setPoids(rs.getFloat("poids"));
                consultation.setTaille(rs.getFloat("taille"));
                consultation.setImc(rs.getFloat("imc"));
                consultation.setTemperature(rs.getFloat("temperature"));
                consultation.setPrix(rs.getFloat("prix"));
                consultation.setPression_arterielle(rs.getFloat("pression_arterielle"));
                consultation.setFrequence_cardiaque(rs.getFloat("frequence_cardiaque"));
                consultation.setTaux_glycemie(rs.getFloat("taux_glycemie"));
                consultation.setMaladie(rs.getString("maladie"));
                consultation.setTraitement(rs.getString("traitement"));
                consultation.setObservation(rs.getString("observation"));
                consultation.setOrdonnances(new ArrayList<Ordonnance>());
                consultationMap.put(consultationId, consultation);
            }

            // Check if the rendezvous exists
            int rdvId = rs.getInt("id");
            if (rdvId != 0) {
                RendezVousC rdv = new RendezVousC();
                rdv.setIdRdv(rdvId);
                rdv.setIdPlanning(rs.getInt("planning_id"));
                rdv.setIdPatient(rs.getInt("patient_id "));
                rdv.setSymptomes(rs.getString("symptomes"));
                rdv.setEtat(rs.getString("etat"));
                rdv.setDate(rs.getDate("date"));
                rdv.setHeureDebut(rs.getTime("heureDebut"));
                rdv.setHeureFin(rs.getTime("heureFin"));
                consultation.setRendezVousC(rdv);
            }
        }
        // Add all consultations from the map to the list
        consultations.addAll(consultationMap.values());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return consultations;
}
*/



/*
private void updateRendezVous(RendezVousC rdv, int consultId) {
    String request = "INSERT INTO rendez_vous(date,heure_debut,heure_fin,symptomes,etat,date_de_creation,planning_id,consultation_id) "
            + "VALUES(?,?,?,?,?,?,?,?) ";
    try (PreparedStatement pStmt = cnx.prepareStatement(request)) {
        pStmt.setDate(1, new java.sql.Date(rdv.getDate().getTime()));
        pStmt.setTime(2, new java.sql.Time(rdv.getHeureDebut().getTime()));
        pStmt.setTime(3, new java.sql.Time(rdv.getHeureFin().getTime()));
        pStmt.setString(4, rdv.getSymptomes());
        pStmt.setString(5, "en attente");
        pStmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
        pStmt.setInt(7, rdv.getIdPlanning());
        pStmt.setInt(8, consultId); // set the consultation_id column with the provided consultId
        pStmt.executeUpdate();
        System.out.println("success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

*/

   

    

    
    
}
