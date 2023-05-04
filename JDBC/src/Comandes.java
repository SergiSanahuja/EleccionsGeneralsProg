import java.sql.*;

public class Comandes {

    public static int contador = 1;
    public static void instertarDades(Connection conn, String nom, String cognom) {
        try {

            String sql = "INSERT INTO alumne (id_alumne,nom,cognom)  VALUES (?,?, ?)";
            //instanciem Statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contador++);
            stmt.setString(2, nom);  //assignem valor a la primera interrogació
            stmt.setString(3, cognom);  //assignem valor a la primera interrogació

            stmt.executeUpdate();  //instranciem ResultSet
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error de connexió a la base de dades: " + e.getMessage());
        }

    }

    public static void esborrar(){

    };

    public static void mostrarDades(Connection conn){
        try {
            CA ca = null;
            String sql = "SELECT nom FROM comunitats_autonomes";
            //instanciem Statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery();  //instranciem ResultSet
            while (resultat.next()) {
                String nomClient = resultat.getString("nom");
                //String descripcioComanda = resultat.getString("descripcio");
                ca = new CA(nomClient);
                Encarrecs.llistaCA.add(ca);
            }
            //tancament de la connexio

            resultat.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error de connexió a la base de dades: " + e.getMessage());
        }
    }

}
