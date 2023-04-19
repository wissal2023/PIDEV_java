package entities;

public class Consultation {
    
    private int idConslt;
    private float poids, taille, imc, temperature, prix, pression_arterielle, frequence_cardiaque, taux_glycemie;
    private String maladie, traitement, observation;

    public Consultation() {
    }

    public Consultation(int idConslt, float poids, float taille, float imc, float temperature, float prix, float pression_arterielle, float frequence_cardiaque, float taux_glycemie, String maladie, String traitement, String observation) {
        this.idConslt = idConslt;
        this.poids = poids;
        this.taille = taille;
        this.imc = imc;
        this.temperature = temperature;        
        this.prix = prix;
        this.pression_arterielle = pression_arterielle;
        this.frequence_cardiaque = frequence_cardiaque;
        this.taux_glycemie = taux_glycemie;
        this.maladie = maladie;
        this.traitement = traitement;
        this.observation = observation;
    }

    public Consultation(int idConslt, float poids, float taille, float prix, String maladie, String traitement) {
        this.idConslt = idConslt;
        this.poids = poids;
        this.taille = taille;
        this.prix = prix;
        this.maladie = maladie;
        this.traitement = traitement;
    }

    public Consultation(float poids, float taille, float imc, float temperature, float prix, float pression_arterielle, float frequence_cardiaque, float taux_glycemie, String maladie, String traitement, String observation) {
        this.poids = poids;
        this.taille = taille;
        this.imc = imc;
        this.temperature = temperature;
        this.prix = prix;
        this.pression_arterielle = pression_arterielle;
        this.frequence_cardiaque = frequence_cardiaque;
        this.taux_glycemie = taux_glycemie;
        this.maladie = maladie;
        this.traitement = traitement;
        this.observation = observation;
    }

    

    public int getIdConslt() {
        return idConslt;
    }

    public float getPoids() {
        return poids;
    }

    public float getTaille() {
        return taille;
    }

    public float getImc() {
        return imc;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPression_arterielle() {
        return pression_arterielle;
    }

    public float getFrequence_cardiaque() {
        return frequence_cardiaque;
    }

    public float getTaux_glycemie() {
        return taux_glycemie;
    }

    public float getPrix() {
        return prix;
    }

    public String getMaladie() {
        return maladie;
    }

    public String getTraitement() {
        return traitement;
    }

    public String getObservation() {
        return observation;
    }

    public void setIdConslt(int idConslt) {
        this.idConslt = idConslt;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setPression_arterielle(float pression_arterielle) {
        this.pression_arterielle = pression_arterielle;
    }

    public void setFrequence_cardiaque(float frequence_cardiaque) {
        this.frequence_cardiaque = frequence_cardiaque;
    }

    public void setTaux_glycemie(float taux_glycemie) {
        this.taux_glycemie = taux_glycemie;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Consultation{" + "idConslt=" + idConslt + ", poids=" + poids + 
                ", taille=" + taille + ", imc=" + imc + ", temperature=" 
                + temperature + ", prix=" + prix + ", pression_arterielle=" + pression_arterielle 
                + ", frequence_cardiaque=" + frequence_cardiaque + ", taux_glycemie=" 
                + taux_glycemie + ", maladie=" + maladie + ", traitement=" 
                + traitement + ", observation=" + observation + '}';
    }
        
}
