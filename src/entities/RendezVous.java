package entities;

import java.util.Date;

public class RendezVous {
    private int idRdv;
    private String symptomes, etat;
    private Date heureDebut, heureFin, date;
    private User patient;
    
    public RendezVous() {
    } 
    
    public RendezVous(int idRdv, String symptomes, String etat, Date date, Date heureDebut, Date heureFin) {
        this.idRdv = idRdv;
        this.symptomes = symptomes;
        this.etat = etat;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public RendezVous(int idRdv, String symptomes, Date date, User patient) {
        this.idRdv = idRdv;
        this.symptomes = symptomes;
        this.date = date;
        this.patient = patient;
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

    @Override
    public String toString() {
        return "RendezVous{" + "idRdv=" + idRdv + ", symptomes=" + symptomes + ", etat=" + etat + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }
    
    
}
