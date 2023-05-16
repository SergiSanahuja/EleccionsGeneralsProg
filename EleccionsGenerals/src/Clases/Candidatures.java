package Clases;

import ImportacioDAO.CandidaturesDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Candidatures {
    int candidaturaID,eleccioID;
    String nom_curt,nom_llarg, codiCandidatura,codi_acumulacio_prov,codi_acumulacio_ca,Codi_acumulacio_nacional;


    public Candidatures(int candidaturaID, int eleccioID, String codiCandidatura, String codi_acumulacio_prov, String codi_acumulacio_ca, String Codi_acumulacio_nacional, String nom_curt, String nom_llarg) {
        this.candidaturaID = candidaturaID;
        this.eleccioID = eleccioID;
        this.codiCandidatura = codiCandidatura;
        this.codi_acumulacio_prov = codi_acumulacio_prov;
        this.codi_acumulacio_ca = codi_acumulacio_ca;
        this.Codi_acumulacio_nacional = Codi_acumulacio_nacional;
        this.nom_curt = nom_curt;
        this.nom_llarg = nom_llarg;
    }

    public Candidatures(int id){
        this.candidaturaID = id;
        setEleccioID(-1);
        setCodiCandidatura(String.valueOf(-1));
        setCodi_acumulacio_prov(String.valueOf(-1));
        setCodi_acumulacio_ca(String.valueOf(-1));
        setCodi_acumulacio_nacional(String.valueOf(-1));
        setNom_curt(null);
        setNom_llarg(null);
    }

    //Getters and setters
    public int getCandidaturaID() {
        return candidaturaID;
    }
    public void setCandidaturaID(int candidaturaID) {this.candidaturaID = candidaturaID;}
    public int getEleccioID() {
        return eleccioID;
    }
    public void setEleccioID(int eleccioID) {
        this.eleccioID = eleccioID;
    }
    public String getCodiCandidatura() {return codiCandidatura;}
    public void setCodiCandidatura(String codiCandidatura) {
        this.codiCandidatura = codiCandidatura;
    }
    public String getCodi_acumulacio_prov() {
        return codi_acumulacio_prov;
    }
    public void setCodi_acumulacio_prov(String codi_acumulacio_prov) {this.codi_acumulacio_prov = codi_acumulacio_prov;}
    public String getCodi_acumulacio_ca() {
        return codi_acumulacio_ca;
    }
    public void setCodi_acumulacio_ca(String codi_acumulacio_ca) {
        this.codi_acumulacio_ca = codi_acumulacio_ca;
    }
    public String getCodi_acumulacio_nacional() {
        return Codi_acumulacio_nacional;
    }
    public void setCodi_acumulacio_nacional(String codi_acumulacio_nacional) {Codi_acumulacio_nacional = codi_acumulacio_nacional;}
    public String getNom_curt() {
        return nom_curt;
    }
    public void setNom_curt(String nom_curt) {
        this.nom_curt = nom_curt;
    }
    public String getNom_llarg() {
        return nom_llarg;
    }
    public void setNom_llarg(String nom_llarg) {
        this.nom_llarg = nom_llarg;
    }


    //Metodes CRUD
    //Create
    public static void insert(int candidaturaID,int eleccioID, String codiCandidatura,String nom_curt, String nom_llarg, String codi_acumulacio_prov, String codi_acumulacio_ca,String Codi_acumulacio_nacional ) throws IOException {
        CandidaturesDAO c = new CandidaturesDAO();
        c.create(new Candidatures(candidaturaID,eleccioID,codiCandidatura,nom_curt,nom_llarg,codi_acumulacio_prov,codi_acumulacio_ca,Codi_acumulacio_nacional));
    }

    //Read
    public static void read(int candidaturaID) throws IOException {
        CandidaturesDAO c = new CandidaturesDAO();
        c.read(new Candidatures(candidaturaID));
    }

    //Update
    public static void update(int candidaturaID, String nom_curt, String nom_llarg) {
        CandidaturesDAO c = new CandidaturesDAO();
        c.update(new Candidatures(candidaturaID,0,"000095","95","95","95",nom_curt,nom_llarg));
    }

    //Delete
    public static void delete(int candidaturaID) throws IOException {
        CandidaturesDAO c = new CandidaturesDAO();
        c.delete(new Candidatures(candidaturaID));
    }

    //importacio de Candidatures
    public static void importarCandidatures( Connection con){
        File file = new File("./fitxers/03021606.DAT");

        try (
                BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.ISO_8859_1))) {
            String st;

            while ((st = br.readLine()) != null) {

                String codi_canditatura = Importacio.llegirSegonsLlargada(9, 6, st);
                int codi_acumulacio_provincia = Integer.parseInt(Importacio.llegirSegonsLlargada(215, 6, st));
                int codi_acumulacio_comautonoma = Integer.parseInt(Importacio.llegirSegonsLlargada(221, 6, st));
                int codi_acumulacio_nacional = Integer.parseInt(Importacio.llegirSegonsLlargada(227, 6, st));
                String nom_curt = Importacio.llegirSegonsLlargada(15, 50, st).trim();
                String nom_llarg = Importacio.llegirSegonsLlargada(65, 150, st).trim();

                //Treure codi id de eleccions
                int any = Integer.parseInt(Importacio.llegirSegonsLlargada(3, 4, st));
                int mes = Integer.parseInt(Importacio.llegirSegonsLlargada(7, 2, st));
                //int eleccio_id = Importacio.obtenirEleccioId(mes, any);

                // the mysql insert statement
                String query = "INSERT INTO candidatures (eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional)"
                        + " values (?, ?, ?, ? ,?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setInt(1, 0);
                preparedStmt.setString(2, codi_canditatura);
                preparedStmt.setString(3, nom_curt);
                preparedStmt.setString(4, nom_llarg);
                preparedStmt.setInt(5, codi_acumulacio_provincia);
                preparedStmt.setInt(6, codi_acumulacio_comautonoma);
                preparedStmt.setInt(7, codi_acumulacio_nacional);

                // execute the preparedstatement
                preparedStmt.execute();
                //System.out.println(eleccio_id + " " + codi_canditatura + " " + nom_curt + " " + nom_llarg + " " + codi_acumulacio_provincia + " " + codi_acumulacio_comautonoma + " " + codi_acumulacio_nacional);

            }
            System.out.println("La taula de Candidatures s'ha importat correctament.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
