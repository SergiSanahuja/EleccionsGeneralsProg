package Clases;

import ImportacioDAO.ComunitatsAutonomesDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class ComunitatAutonoma {

    int ComunitatAutonomaId;
    String nom;
    int codiINE;

    //Constructors
    public ComunitatAutonoma(int comunitatAutonomaId, String nom, int codiINE) {
        ComunitatAutonomaId = comunitatAutonomaId;
        this.nom = nom;
        this.codiINE = codiINE;
    }

    public ComunitatAutonoma(int id){
        this.ComunitatAutonomaId = id;
        setNom(null);
        setCodiINE(-1);
    }

    public ComunitatAutonoma(ComunitatAutonoma c) {
        this.ComunitatAutonomaId = c.getComunitatAutonomaId();
    }

    //Getters and setters
    public int getComunitatAutonomaId() {return ComunitatAutonomaId;}

    public void setComunitatAutonomaId(int comunitatAutonomaId) {ComunitatAutonomaId = comunitatAutonomaId; }

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public int getCodiINE() {return codiINE;}

    public void setCodiINE(int codiINE) {this.codiINE = codiINE;}

    @Override
    public String toString() {
        return "Clases.ComunitatAutonoma{" +
                "ComunitatAutonomaId=" + ComunitatAutonomaId +
                ", nom='" + nom + '\'' +
                ", codiINE=" + codiINE +
                '}';
    }

    //insert comunitat autonoma
    public static void insertComunitatAutonoma(int id, String nom, int codiINE){
        ComunitatsAutonomesDAO CAO = new ComunitatsAutonomesDAO();
        CAO.create(new ComunitatAutonoma(id,nom,codiINE));
    }

    public static void updateComunitatAutonoma(int id, String nom, int codiINE){
        ComunitatsAutonomesDAO CAO = new ComunitatsAutonomesDAO();
        CAO.update(new ComunitatAutonoma(id,nom,codiINE));
    }

    public static void readComunitatAutonoma(int id){
        ComunitatsAutonomesDAO CAO = new ComunitatsAutonomesDAO();
        CAO.read(new ComunitatAutonoma(id));
    }

    public static void deleteComunitatAutonoma(int id){
        ComunitatsAutonomesDAO CAO = new ComunitatsAutonomesDAO();
        CAO.delete(new ComunitatAutonoma(id));
    }

    //importacio a la BD de les comunitats autonomes
    public static void importarComunitatsAutonomes(Connection con){
        File file = new File("./fitxers/07021606.DAT");

        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.ISO_8859_1))) {

            String st;

            while ((st = br.readLine()) != null) {
                int codiINEComunitat = Integer.parseInt(Main.llegirSegonsLlargada(10, 2, st));
                int codiINE = Integer.parseInt(Main.llegirSegonsLlargada(12,2,st));
                String nomComunitat = Main.llegirSegonsLlargada(15, 50, st).trim();

                if (codiINE == 99 && codiINEComunitat != 99) {
                    // the mysql insert statement
                    String query = "INSERT INTO comunitats_autonomes (nom,codi_ine)"
                            + " values (?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = con.prepareStatement(query);

                    preparedStmt.setString(1, nomComunitat);
                    preparedStmt.setInt(2, codiINEComunitat);

                    // execute the preparedstatement
                    preparedStmt.execute();
                }
            }
            System.out.println("La taula de Comunitats Autonomes s'ha importat correctament.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


}
