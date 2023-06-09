/**
 * Persones
 * @version 1.0
 * author Sergi Sanahuja and Elyas El Jerari
 */
package Clases;

import ImportacioDAO.PersonesDAO;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Persones {
    private String nom,cog1,cog2,sexe,DNI;
    private Date dataNaixement;
    private int id;
//Constructors
    public Persones(int id,String nom, String cog1, String cog2, String sexe, String DNI, Date data_naixement) {
        this.id = id;
        this.nom = nom;
        this.cog1 = cog1;
        this.cog2 = cog2;
        this.sexe = sexe;
        this.DNI = DNI;
        this.dataNaixement = data_naixement;
    }

    public Persones(int id){
        this.id = id;
        setNom(null);
        setCog1(null);
        setCog2(null);
        setSexe(null);
        setDNI(null);
        setData_naixement(null);
    }

//Getters and Setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCog1() {
        return cog1;
    }
    public void setCog1(String cog1) {
        this.cog1 = cog1;
    }
    public String getCog2() {
        return cog2;
    }
    public void setCog2(String cog2) {
        this.cog2 = cog2;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public Date getData_naixement() {return dataNaixement;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public void setData_naixement(Date data_naixement) {
        this.dataNaixement = data_naixement;
    }

//CRUD
    //insertar persona
    public static void insert(int id, String nom, String cog1, String cog2, String sexe, Date data_naixement, String dni){
        PersonesDAO persona = new PersonesDAO();
        persona.create(new Persones(id ,nom ,cog1 ,cog2 ,sexe ,dni ,data_naixement));
        System.out.println("Persona insertada");
    }

    public static void read(int id){
        PersonesDAO persona = new PersonesDAO();
        persona.read(new Persones(id));
    }

    public static void update(int id, String nom, String cog1, String cog2) {
        PersonesDAO persona = new PersonesDAO();
        persona.update(new Persones(id,nom,cog1,cog2,"M","12345678A",new Date()));
        System.out.println("Persona actualizada");
    }

    public static void delete(int id){
        PersonesDAO persona = new PersonesDAO();
        persona.delete(new Persones(id));
        System.out.println("Persona eliminada");
    }


    //importar persones
    public static void importarPersones(Connection con) {
        File file = new File("./fitxers/04021606.DAT");

        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.ISO_8859_1))) {

            String st;
            String[] nom;

            while ((st = br.readLine()) != null) {

                String nom_pers = Importacio.llegirSegonsLlargada(26, 25, st).trim();
                String cog1_pers = Importacio.llegirSegonsLlargada(51, 25, st).trim();
                String cog2_pers = Importacio.llegirSegonsLlargada(76, 25, st).trim();
                String sexe = Importacio.llegirSegonsLlargada(101, 1, st).trim();
                String dni = Importacio.llegirSegonsLlargada(110, 10, st).trim();
                if (dni.equals("000000000")){
                    dni = null;
                }
                //modifica el cog1_pers si nomes conte un "i" per la segona paraula del nom.
                if (cog1_pers.equals("i")) {
                    nom = nom_pers.split(" ");
                    nom_pers = nom[0];
                    cog1_pers = nom[1];
                }


                int dia = Integer.parseInt(Importacio.llegirSegonsLlargada(102, 2, st));
                int mes = Integer.parseInt(Importacio.llegirSegonsLlargada(104, 2, st));
                int any = Integer.parseInt(Importacio.llegirSegonsLlargada(106, 4, st));
                String query = "INSERT INTO persones (nom, cog1, cog2, sexe,data_naixement,dni)"
                        + " values (?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, nom_pers);
                preparedStmt.setString(2, cog1_pers);
                preparedStmt.setString(3, cog2_pers);
                preparedStmt.setString(4, sexe);
                preparedStmt.setDate(5, new java.sql.Date(any, mes, dia));
                preparedStmt.setString(6, dni);

                //si la data es un 0 el camp sera null pero si no escriu la data.
                if (any != 0) {
                    Date data_naixement = DateFormat.getInstance().parse(dia + "/" + mes + "/" + any);
                    //System.out.println(nom_pers + " " + cog1_pers + " " + cog2_pers + " "  + data_naixement);
                    preparedStmt.setDate(4, (java.sql.Date) data_naixement);

                }
                else{
                    //System.out.println(nom_pers + " " + cog1_pers + " " + cog2_pers + " " );
                    preparedStmt.setNull(4, Types.DATE);
                }
                preparedStmt.execute();
            }
            System.out.println("La taula de Clases.Persones s'ha importat correctament.");
        } catch (IOException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
