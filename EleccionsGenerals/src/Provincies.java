import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Provincies {

    int provinciaID, comunitat_aut_id, codi_ine, num_escons;
    String nom;

    public Provincies(int provinciaID, int comunitat_aut_id, int codi_ine, int num_escons, String nom) {
        this.provinciaID = provinciaID;
        this.comunitat_aut_id = comunitat_aut_id;
        this.codi_ine = codi_ine;
        this.num_escons = num_escons;
        this.nom = nom;
    }

    public int getProvinciaID() {
        return provinciaID;
    }

    public void setProvinciaID(int provinciaID) {
        this.provinciaID = provinciaID;
    }

    public int getComunitat_aut_id() {
        return comunitat_aut_id;
    }

    public void setComunitat_aut_id(int comunitat_aut_id) {
        this.comunitat_aut_id = comunitat_aut_id;
    }

    public int getCodi_ine() {
        return codi_ine;
    }

    public void setCodi_ine(int codi_ine) {
        this.codi_ine = codi_ine;
    }

    public int getNum_escons() {
        return num_escons;
    }

    public void setNum_escons(int num_escons) {
        this.num_escons = num_escons;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void importarProvincies(Connection con) {
        File file = new File("./fitxers/07021606.DAT");

        try (
                BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;

            while ((st = br.readLine()) != null) {
                String nomProvincia = Main.llegirSegonsLlargada(15, 50, st).trim();
                int codiINEComunitat = Integer.parseInt(Main.llegirSegonsLlargada(10, 2, st));
                int codiINE = Integer.parseInt(Main.llegirSegonsLlargada(12,2,st));
                int codiComunitat = Integer.parseInt(Main.llegirSegonsLlargada(10, 2, st));
                int numEscons = Integer.parseInt(Main.llegirSegonsLlargada(150,6,st));

                //Treure codi id de provincies
                int comunitat_aut_id = Main.obtenirIdComunAmbINE(codiINEComunitat);

                if (codiINE != 99 && codiComunitat != 99) {

                    // the mysql insert statement
                    String query = "INSERT INTO provincies (comunitat_aut_id,nom,codi_ine,num_escons)"
                            + " values (?, ?, ?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = con.prepareStatement(query);

                    preparedStmt.setInt(1, comunitat_aut_id);
                    preparedStmt.setString(2, nomProvincia);
                    preparedStmt.setInt(3, codiINE);
                    preparedStmt.setInt(4, numEscons);

                    // execute the preparedstatement
                    preparedStmt.execute();

                }
            }
            System.out.println("La taula de Provincies s'ha importat correctament.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
