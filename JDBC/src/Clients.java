import java.sql.*;

public class Clients {
    private String nom;
    private String cognoms;

    Clients(String nom, String cognoms){
        this.nom = nom;
        this.cognoms = cognoms;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "nom='" + nom + '\'' +
                ", cognoms='" + cognoms + '\'' +
                '}';
    }
}
