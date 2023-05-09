import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Municipi {
    int municipiID,codi_ine,provincia_id;
    String nom,districte;

    public Municipi(int municipiID, int codi_ine, int provincia_id, String nom, String districte) {
        this.municipiID = municipiID;
        this.codi_ine = codi_ine;
        this.provincia_id = provincia_id;
        this.nom = nom;
        this.districte = districte;
    }

    public int getMunicipiID() {
        return municipiID;
    }
    public void setMunicipiID(int municipiID) {
        this.municipiID = municipiID;
    }
    public int getCodi_ine() {
        return codi_ine;
    }
    public void setCodi_ine(int codi_ine) {
        this.codi_ine = codi_ine;
    }
    public int getProvincia_id() {
        return provincia_id;
    }
    public void setProvincia_id(int provincia_id) {
        this.provincia_id = provincia_id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDistricte() {
        return districte;
    }
    public void setDistricte(String districte) {
        this.districte = districte;
    }

    //importar municipis
    public static void importarMunicipis(Connection con) {
        File file = new File("./fitxers/05021606.DAT");

        try (
                BufferedReader br = new BufferedReader(new FileReader(file,StandardCharsets.ISO_8859_1))) {
            String st;

            while ((st = br.readLine()) != null) {
                String nomMunicipi = Main.llegirSegonsLlargada(19, 100, st).trim();
                int codiINEmunicipi = Integer.parseInt(Main.llegirSegonsLlargada(14,3,st));
                int codiINEprovincia = Integer.parseInt(Main.llegirSegonsLlargada(12,2,st));
                int num_districte = Integer.parseInt(Main.llegirSegonsLlargada(17,2,st));


                //tenir el codi de provincia_id
                int provincia_id = Main.obtenirProvincia_id(codiINEprovincia);

                if (num_districte == 99 ) {
                    // the mysql insert statement
                    String query = "INSERT INTO municipis (nom,codi_ine,provincia_id, districte)"
                            + " values (?, ?, ?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = con.prepareStatement(query);

                    preparedStmt.setString(1, nomMunicipi);
                    preparedStmt.setInt(2, codiINEmunicipi);
                    preparedStmt.setInt(3, provincia_id);
                    preparedStmt.setInt(4, num_districte);

                    // execute the preparedstatement
                    preparedStmt.execute();
                    //System.out.println(nomMunicipi + " " + codiINEmunicipi + " "+ num_districte );
                }
            }
            System.out.println("La taula de Municipis s'ha importat correctament.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
