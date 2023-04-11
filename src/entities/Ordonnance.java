package entities;

import java.util.Date;

public class Ordonnance {
    
    private int id, nombre_jours;
    private String medicaments, code_ordonnance, dosage, qrCodeFilename, description ;
    private Date date_de_creation, date_de_modification;

    public Ordonnance() {
    }

    public Ordonnance(int id, int nombre_jours, String medicaments, String code_ordonnance, String dosage, String qrCodeFilename, String description, Date date_de_creation, Date date_de_modification) {
        this.id = id;
        this.nombre_jours = nombre_jours;
        this.medicaments = medicaments;
        this.code_ordonnance = code_ordonnance;
        this.dosage = dosage;
        this.qrCodeFilename = qrCodeFilename;
        this.description = description;
        this.date_de_creation = date_de_creation;
        this.date_de_modification = date_de_modification;
    }

    public Ordonnance(int id, int nombre_jours, String medicaments, String code_ordonnance, String description) {
        this.id = id;
        this.nombre_jours = nombre_jours;
        this.medicaments = medicaments;
        this.code_ordonnance = code_ordonnance;
        this.description = description;
    }

    public Ordonnance(int id, String code_ordonnance, String description, Date date_de_creation) {
        this.id = id;
        this.code_ordonnance = code_ordonnance;
        this.description = description;
        this.date_de_creation = date_de_creation;
    }

    public int getId() {
        return id;
    }

    public int getNombre_jours() {
        return nombre_jours;
    }

    public String getMedicaments() {
        return medicaments;
    }

    public String getCode_ordonnance() {
        return code_ordonnance;
    }

    public String getDosage() {
        return dosage;
    }

    public String getQrCodeFilename() {
        return qrCodeFilename;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_de_creation() {
        return date_de_creation;
    }

    public Date getDate_de_modification() {
        return date_de_modification;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre_jours(int nombre_jours) {
        this.nombre_jours = nombre_jours;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }

    public void setCode_ordonnance(String code_ordonnance) {
        this.code_ordonnance = code_ordonnance;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setQrCodeFilename(String qrCodeFilename) {
        this.qrCodeFilename = qrCodeFilename;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_de_creation(Date date_de_creation) {
        this.date_de_creation = date_de_creation;
    }

    public void setDate_de_modification(Date date_de_modification) {
        this.date_de_modification = date_de_modification;
    }

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + 
                ", nombre_jours=" + nombre_jours + 
                ", medicaments=" + medicaments + ", code_ordonnance=" 
                + code_ordonnance + ", dosage=" + dosage + ", qrCodeFilename=" 
                + qrCodeFilename + ", description=" + description + ", date_de_creation=" 
                + date_de_creation + ", date_de_modification=" + date_de_modification + '}';
    }
    
    
    
    
}
