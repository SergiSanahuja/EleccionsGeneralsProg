package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static Connection con;

    public static void main (String[]args){
        //Descomprimir els .DAT de un zip concret
        TractarFitxers.descomprimirDATsZIP();

        //Fer la conexio a la base de dades i les importacions
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connectar a la base de dades elias
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/eleccions_generals_prog", "perepi", "pastanaga");

            //Connectar a la base de dades Sergi
            //con = DriverManager.getConnection("jdbc:mysql://192.168.184.140:3306/eleccions_generals_prog", "perepi", "pastanaga");

            //Preparem el Date
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static String llegirSegonsLlargada(int longiInci, int llargadaALlegir, String st) {
        String stringTornar = "";

        longiInci--;

        for (int i = 0; i < llargadaALlegir; ++i, ++longiInci) {
            stringTornar += String.valueOf(st.charAt(longiInci));

        }
        return stringTornar;
    }

     static int obtenirPersona_id(String nom_pers, String cog1_pers, String cog2_pers) {
        int id_persona = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Preparem una sentència.
            String query = "SELECT persona_id " +
                    " FROM persones " +
                    "WHERE nom = ? AND cog1 = ? AND cog2 = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nom_pers);
            preparedStmt.setString( 2, cog1_pers);
            preparedStmt.setString(3, cog2_pers);
            //System.out.println(preparedStmt);

            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()) {
                id_persona = rs.getInt("persona_id");
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return id_persona;

    }

     static int obtenirProvincia_id(int codiINEPro) {
        int id_provincia = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //SENTÈNCIA SELECT
            //Preparem una sentència amb paràmetres.
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE codi_ine = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, codiINEPro);


            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()) {
                //System.out.println(rs.getInt("provincia_id"));
                id_provincia = rs.getInt("provincia_id");
            }

        }catch(Exception e){
            System.out.println(e);
        }


        return id_provincia;
    }

     static int obtenirCandidatura_id(String candidatura_codi) {
        int id_candidatura = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Preparem una sentència.
            String query = "SELECT candidatura_id" +
                    " FROM candidatures " +
                    "WHERE codi_candidatura = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, candidatura_codi);
            //System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()) {
                id_candidatura = rs.getInt("candidatura_id");
            }

        }catch(Exception e){
            System.out.println(e);
        }


        return id_candidatura;

    }

    static int obtenirEleccioId(int mes, int any) {
        int eleccioid = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //SENTÈNCIA SELECT
            //Preparem una sentència amb paràmetres.
            String query = "SELECT eleccio_id " +
                    " FROM eleccions " +
                    "WHERE any = ? AND mes = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, any);
            preparedStmt.setInt(2, mes);

            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()) {
                //System.out.println(rs.getInt("eleccio_id"));
                eleccioid = rs.getInt("eleccio_id");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return eleccioid;
    }

    static int obtenirIdComunAmbINE(int codiINEComunitat) {
        int id_comuni = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //SENTÈNCIA SELECT
            //Preparem una sentència amb paràmetres.
            String query = "SELECT comunitat_aut_id FROM comunitats_autonomes WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, codiINEComunitat);

            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()) {
                //System.out.println(rs.getInt("comunitat_aut_id"));
                id_comuni = rs.getInt("comunitat_aut_id");
            }


        }catch(Exception e){
            System.out.println(e);
        }


        return id_comuni;
    }

}