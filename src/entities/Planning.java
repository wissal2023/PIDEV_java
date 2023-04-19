package entities;

import java.util.Collection;
import java.util.Date;

public class Planning {
    
    private int id;
    private Date date_debut, date_fin, heure_debut, heure_fin, date_de_creation, date_de_modification;
    private String etat, description;
    private Collection<RendezVous> les_rendez_vous;
    private User medecin;

    public Planning() {
    }

    public Planning(int id, Date date_debut, Date date_fin, Date heure_debut, Date heure_fin, Date date_de_creation, Date date_de_modification, String etat, String description, Collection<RendezVous> les_rendez_vous, User medecin) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.date_de_creation = date_de_creation;
        this.date_de_modification = date_de_modification;
        this.etat = etat;
        this.description = description;
        this.les_rendez_vous = les_rendez_vous;
        this.medecin = medecin;
    }

    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public Date getHeure_debut() {
        return heure_debut;
    }

    public Date getHeure_fin() {
        return heure_fin;
    }

    public Date getDate_de_creation() {
        return date_de_creation;
    }

    public Date getDate_de_modification() {
        return date_de_modification;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }

    public Collection<RendezVous> getLes_rendez_vous() {
        return les_rendez_vous;
    }

    public User getMedecin() {
        return medecin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setHeure_debut(Date heure_debut) {
        this.heure_debut = heure_debut;
    }

    public void setHeure_fin(Date heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setDate_de_creation(Date date_de_creation) {
        this.date_de_creation = date_de_creation;
    }

    public void setDate_de_modification(Date date_de_modification) {
        this.date_de_modification = date_de_modification;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLes_rendez_vous(Collection<RendezVous> les_rendez_vous) {
        this.les_rendez_vous = les_rendez_vous;
    }

    public void setMedecin(User medecin) {
        this.medecin = medecin;
    }

    
}
