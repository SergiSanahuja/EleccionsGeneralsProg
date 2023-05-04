import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;

public class Main {

    public static Connection con;

    public static void main (String[]args){
        //Descomprimir els .DAT de un zip concret
        TractarFitxers.descomprimirDATsZIP();

        //Fer la conexio a la base de dades i les importacions
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://192.168.184.140:3306/mydbProg", "perepi", "pastanaga");

            //Preparem el Date
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            //Importacio de les Comunitats Autonomes
            ComunitatAutonoma.importarComunitatsAutonomes(con);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}