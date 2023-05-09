package entities;

import java.util.Date;

public class RendezVousC {
    private int idRdv, idPlanning, idPatient, consultation_id;
    private String symptomes, etat, fullName, patientName;
    private Date date, heureDebut,heureFin;
    private UserC patient;
    private Consultation consultation;// addthis to the project 
    
    public RendezVousC() {
    } 

    public RendezVousC(int idRdv, int idPlanning, int idPatient, String fullName, UserC patient) {
        this.idRdv = idRdv;
        this.idPlanning = idPlanning;
        this.idPatient = idPatient;
        this.fullName = fullName;
        this.patient = patient;
    }

    public RendezVousC(int idRdv, String symptomes, String etat, Date date, Date heureDebut, Date heureFin, int idPlanning) {
        this.idRdv = idRdv;
        this.symptomes = symptomes;
        this.etat = etat;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idPlanning = idPlanning;
    }

    public RendezVousC(int idRdv, int idPlanning, int idPatient, String symptomes, String etat, String patientName, Date date, Date heureDebut, UserC patient) {
        this.idRdv = idRdv;
        this.idPlanning = idPlanning;
        this.idPatient = idPatient;
        this.symptomes = symptomes;
        this.etat = etat;
        this.patientName = patientName;
        this.date = date;
        this.heureDebut = heureDebut;
        this.patient = patient;
    }
    
    public RendezVousC(int idRdv, String symptomes, String etat, Date date, Date heureDebut, Date heureFin) {
        this.idRdv = idRdv;
        this.symptomes = symptomes;
        this.etat = etat;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public RendezVousC(String symptomes, String etat, Date date, Date heureDebut, Date heureFin, int idPlanning) {
        this.symptomes = symptomes;
        this.etat = etat;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idPlanning = idPlanning;
    }

    public RendezVousC(int idPatient, int consultation_id) {
        this.idPatient = idPatient;
        this.consultation_id = consultation_id;
    }

    public RendezVousC(int idRdv, int idPatient, int consultation_id) {
        this.idRdv = idRdv;
        this.idPatient = idPatient;
        this.consultation_id = consultation_id;
    }

    //****************** GETTERS AND SETTERS ****************************
    
    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public UserC getPatient() {
        return patient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setPatient(UserC patient) {
        this.patient = patient;
    }

    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public int getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public String getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
    // add this to the table of rendez vous in the project
    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    public Consultation getConsultation() {
        return consultation;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "idRdv=" + idRdv + ", symptomes=" + symptomes + ", etat=" + etat + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }
        
}