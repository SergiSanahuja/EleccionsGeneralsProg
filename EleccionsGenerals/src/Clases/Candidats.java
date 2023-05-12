package Clases;

import ImportacioDAO.CandidatsDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Candidats {
    private int candidatID,candidaturaID,personaID,provinciaID,num_ordre;
    private String tipus;

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

    //funcions per a CRUD
    public static void insert(int candidatID, int candidaturaID, int personaID, int provinciaID, int num_ordre, String tipus){
        CandidatsDAO Candidat = new CandidatsDAO();
        Candidat.create(new Candidats(candidatID,candidaturaID,personaID,provinciaID,num_ordre,tipus));
        System.out.println("Candidat inserit");
    }

    public static void read(int candidatID){
        CandidatsDAO Candidat = new CandidatsDAO();
        Candidat.read(new Candidats(candidatID));
    }

    public static void update(int candidatID,int personaID, int provinciaID){
        CandidatsDAO Candidat = new CandidatsDAO();
        Candidat.update(new Candidats(candidatID,1,personaID,provinciaID,5,"T"));
    }

    public static void delete(int candidatID){
        CandidatsDAO Candidat = new CandidatsDAO();
        Candidat.delete(new Candidats(candidatID));
    }


//Importacio de la taula candidats
    public static void importarCandidats(Connection con) {
        File file = new File("./fitxers/04021606.DAT");

        try (BufferedReader br = new BufferedReader(new FileReader(file,StandardCharsets.ISO_8859_1))) {

            String st;
            String[] nom;

            while ((st = br.readLine()) != null) {
                String candidatura_codi = Importacio.llegirSegonsLlargada(16, 6, st);
                int candidatura_id = Importacio.obtenirCandidatura_id(candidatura_codi);
                String nom_pers = Importacio.llegirSegonsLlargada(26, 25, st).trim();
                String cog1_pers = Importacio.llegirSegonsLlargada(51, 25, st).trim();
                String cog2_pers = Importacio.llegirSegonsLlargada(76, 25, st).trim();
                if (cog1_pers.equals("i")) {
                    nom = nom_pers.split(" ");
                    nom_pers = nom[0];
                    cog1_pers = nom[1];
                }


                int persona_id = Importacio.obtenirPersona_id(nom_pers, cog1_pers, cog2_pers);

                int codiINEPro = Integer.parseInt(Importacio.llegirSegonsLlargada(10,2,st));
                int provincia_id  = Importacio.obtenirProvincia_id(codiINEPro);

                int num_ordre = Integer.parseInt(Importacio.llegirSegonsLlargada(22, 3, st));
                String tipus = Importacio.llegirSegonsLlargada(25, 1, st);

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
            System.out.println("La taula de Clases.Candidats s'ha importat correctament.");
        } catch (IOException | SQLException e) {

            e.printStackTrace();
        }
    }



}
