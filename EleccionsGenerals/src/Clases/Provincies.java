/**
 * Classe Provincies
 * @version 1.0
 * author Sergi Sanahuja and Elyas El Jerari
 */
package Clases;

import ImportacioDAO.ProvinciesDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Provincies {

    private int provinciaID, comunitat_aut_id, codi_ine, num_escons;
    private String nom;

    public Provincies(int provinciaID, int comunitat_aut_id, String nom,int codi_ine, int num_escons) {
        this.provinciaID = provinciaID;
        this.comunitat_aut_id = comunitat_aut_id;
        this.nom = nom;
        this.codi_ine = codi_ine;
        this.num_escons = num_escons;

    }

    public Provincies(int id){
        this.provinciaID = id;
        setComunitat_aut_id(-1);
        setCodi_ine(-1);
        setNum_escons(-1);
        setNom(null);
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

    //metodes CRUD
    //create
    public static void insert(int provinciaID, int comunitat_aut_id, String nom,int codi_ine , int num_escons){
       ProvinciesDAO PDAO = new ProvinciesDAO();
         PDAO.create(new Provincies(provinciaID,comunitat_aut_id,nom,codi_ine,num_escons));
        System.out.println("Provincia creada");
    }

    //read
    public static void read(int id){
        ProvinciesDAO PDAO = new ProvinciesDAO();
        PDAO.read(new Provincies(id));
    }

    //update
    public static void update(int id,String nom){
        ProvinciesDAO PDAO = new ProvinciesDAO();
        PDAO.update(new Provincies(id,11,nom,11,1));
        System.out.println("Provincia actualitzada");
    }

    //delete
    public static void delete(int id){
        ProvinciesDAO PDAO = new ProvinciesDAO();
        PDAO.delete(new Provincies(id));
        System.out.println("Provincia eliminada");
    }


    //importarProvincies
    public static void importarProvincies(Connection con) {
        File file = new File("./fitxers/07021606.DAT");

        try (
                BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;

            while ((st = br.readLine()) != null) {
                String nomProvincia = Importacio.llegirSegonsLlargada(15, 50, st).trim();
                int codiINEComunitat = Integer.parseInt(Importacio.llegirSegonsLlargada(10, 2, st));
                int codiINE = Integer.parseInt(Importacio.llegirSegonsLlargada(12,2,st));
                int codiComunitat = Integer.parseInt(Importacio.llegirSegonsLlargada(10, 2, st));
                int numEscons = Integer.parseInt(Importacio.llegirSegonsLlargada(150,6,st));

                //Treure codi id de provincies
                int comunitat_aut_id = Importacio.obtenirIdComunAmbINE(codiINEComunitat);

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
