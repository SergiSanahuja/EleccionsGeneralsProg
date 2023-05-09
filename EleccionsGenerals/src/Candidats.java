import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Candidats {
    int candidatID,candidaturaID,personaID,provinciaID,num_ordre;
    String tipus;

    public Candidats(int candidatID, int candidaturaID, int personaID, int provinciaID, int num_ordre, String tipus) {
        this.candidatID = candidatID;
        this.candidaturaID = candidaturaID;
        this.personaID = personaID;
        this.provinciaID = provinciaID;
        this.num_ordre = num_ordre;
        this.tipus = tipus;
    }

    public Candidats(int CandidatID){
        this.candidatID = CandidatID;

    }

    //GETTER I SETTERS
    public int getCandidatID() {return candidatID;}
    public void setCandidatID(int candidatID) {this.candidatID = candidatID;}
    public int getCandidaturaID() {return candidaturaID;}
    public void setCandidaturaID(int candidaturaID) {this.candidaturaID = candidaturaID;}
    public int getPersonaID() {return personaID;}
    public void setPersonaID(int personaID) {this.personaID = personaID;}
    public int getProvinciaID() {return provinciaID;}
    public void setProvinciaID(int provinciaID) {this.provinciaID = provinciaID;}
    public int getNum_ordre() {return num_ordre;}
    public void setNum_ordre(int num_ordre) {this.num_ordre = num_ordre;}
    public String getTipus() {return tipus;}
    public void setTipus(String tipus) {this.tipus = tipus;}


    public static void importarCandidats(Connection con) {
        File file = new File("./fitxers/04021606.DAT");

        try (BufferedReader br = new BufferedReader(new FileReader(file,StandardCharsets.ISO_8859_1))) {

            String st;
            String[] nom;

            while ((st = br.readLine()) != null) {
                String candidatura_codi = Main.llegirSegonsLlargada(16, 6, st);
                int candidatura_id = Main.obtenirCandidatura_id(candidatura_codi);
                String nom_pers = Main.llegirSegonsLlargada(26, 25, st).trim();
                String cog1_pers = Main.llegirSegonsLlargada(51, 25, st).trim();
                String cog2_pers = Main.llegirSegonsLlargada(76, 25, st).trim();
                if (cog1_pers.equals("i")) {
                    nom = nom_pers.split(" ");
                    nom_pers = nom[0];
                    cog1_pers = nom[1];
                }

                int persona_id = Main.obtenirPersona_id(nom_pers, cog1_pers, cog2_pers);

                int codiINEPro = Integer.parseInt(Main.llegirSegonsLlargada(10,2,st));
                int provincia_id  = Main.obtenirProvincia_id(codiINEPro);

                int num_ordre = Integer.parseInt(Main.llegirSegonsLlargada(22, 3, st));
                String tipus = Main.llegirSegonsLlargada(25, 1, st);

                //System.out.println(candidatura_id + " " + persona_id + " " + provicia_id + " " + num_ordre + " " + tipus);

                String query = "INSERT INTO candidats (candidatura_id, persona_id, provincia_id, num_ordre, tipus)"
                        + " values (?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setInt(1, candidatura_id);
                preparedStmt.setInt(2, persona_id);
                preparedStmt.setInt(3, provincia_id);
                preparedStmt.setInt(4, num_ordre);
                preparedStmt.setString(5, tipus);
                //System.out.println(preparedStmt);
                preparedStmt.execute();
            }
            System.out.println("La taula de Candidats s'ha importat correctament.");
        } catch (IOException | SQLException e) {

            e.printStackTrace();
        }
    }



}
