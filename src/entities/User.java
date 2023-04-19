package entities;

import java.util.List;
import java.util.Objects;

public class User {
    
    private int id  ;
    private String email,nom, prenom, adresse, num_tel, specialite, cin, image ;
    
    private List<RendezVous> rendezVouses;
    private List<String> roles;

    public User() {
    }

    public User(int id, String email, String nom, String prenom, String adresse, String num_tel, String specialite, String cin, String image, List<RendezVous> rendezVouses, List<String> roles) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.specialite = specialite;
        this.cin = cin;
        this.image = image;
        this.rendezVouses = rendezVouses;
        this.roles = roles;
    }

   
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getCin() {
        return cin;
    }

    public String getImage() {
        return image;
    }

    public List<RendezVous> getRendezVouses() {
        return rendezVouses;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRendezVouses(List<RendezVous> rendezVouses) {
        this.rendezVouses = rendezVouses;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.num_tel, other.num_tel)) {
            return false;
        }
        if (!Objects.equals(this.specialite, other.specialite)) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.rendezVouses, other.rendezVouses)) {
            return false;
        }
        return Objects.equals(this.roles, other.roles);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", nom=" + nom +
                ", prenom=" + prenom + ", adresse=" + adresse + ", num_tel=" +
                num_tel + ", specialite=" + specialite + ", cin=" + cin + ", image=" +
                image + ", rendezVouses=" + rendezVouses + ", roles=" + roles + '}';
    }
    
    
    
}
