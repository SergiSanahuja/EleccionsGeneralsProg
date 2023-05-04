import java.sql.*;
import java.util.ArrayList;
//TODO: Alumnes > crear una bdd amb les taules i dades per a poder executar aquest codi
//TODO: Provar de canviar la connexió Statement per PreparedStatement
//TODO: transformar aquesta classe per a que no només executi les operacions CRUD, sinó que els clients i les comandes siguin objectes >> Encarrecsv2
//CRUD: Create Read Update Delete


public class Encarrecs {
    //llistat de clients
    public static ArrayList<CA> llistaClients = new ArrayList<CA>();
    public static ArrayList <CA> llistaCA = new ArrayList<CA>();

    public static void main(String[] args) {
        con();
        mostrarClients();

    }

    public static void con(){
        String bdd = "jdbc:mysql://192.168.184.140:3306/eleccions_generals";
        String user = "perepi";
        String password = "pastanaga";

        try {
            Connection conn = DriverManager.getConnection(bdd, user, password);  //instància Connection

            Comandes.mostrarDades(conn);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error de connexió a la base de dades: " + e.getMessage());
        }
    }

    public static void mostrarClients(){
        for (CA client: llistaCA) {
            System.out.println(client);
        }
    }

    /** si no volem tirar d'API, passar les dades d'un objecte als camps de la taula seria quelcom semblant a això:
     * public void insertarClient(Client client) throws SQLException {
    *     String sql = "INSERT INTO clients (nom, cognoms, adreça, telèfon) VALUES (?, ?, ?, ?)";
     *     PreparedStatement statement = conn.prepareStatement(sql);
     *     statement.setString(1, client.getNom());
     *     statement.setString(2, client.getCognoms());
     *     statement.setString(3, client.getAdreça());
     *     statement.setString(4, client.getTelèfon());
     *     statement.executeUpdate();
     * }
     * */

    //Exercici PreparedStatement
    /**
    String bdd = "jdbc:mysql://192.168.184.140:3306/escola";
    String user = "perepi";
    String password = "pastanaga";

        try {
        Connection conn = DriverManager.getConnection(bdd, user, password);  //instància Connection
        String sql = "SELECT nom  FROM alumne where nom = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);  //instanciem Statement
        stmt.setString(1, "Sergi");  //assignem valor a la primera interrogació
        ResultSet resultat = stmt.executeQuery();  //instranciem ResultSet
        //mostrar dades per pantalla resultat de la sentencia sql
        while (resultat.next()) {
            String nomClient = resultat.getString("nom");
            //String descripcioComanda = resultat.getString("descripcio");
            System.out.println("Client: " + nomClient);
        }
        //tancament de la connexio
        resultat.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error de connexió a la base de dades: " + e.getMessage());
    }
}*/
}

