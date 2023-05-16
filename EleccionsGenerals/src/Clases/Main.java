/**
 * Main del programa
 * @version 1.0
 * author Sergi Sanahuja and Elyas El Jerari
 */

package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
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
            //con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/eleccions_generals_prog", "perepi", "pastanaga");

            //Connectar a la base de dades Sergi
            con = DriverManager.getConnection("jdbc:mysql://192.168.184.140:3306/mydbProg", "perepi", "pastanaga");

            //Preparem el Date
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            Menu.menu(con);

            //Tancar la conexio
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}