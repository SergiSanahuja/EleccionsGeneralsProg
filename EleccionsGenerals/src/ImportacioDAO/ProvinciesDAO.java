package ImportacioDAO;

import Clases.Main;
import Clases.Provincies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProvinciesDAO implements DAO<Provincies> {

    @Override
    public void create(Provincies provincies) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO provincies (comunitat_aut_id, nom,codi_ine,num_escons) VALUES (?,?,?,?)");
            ps.setInt(1, provincies.getComunitat_aut_id());
            ps.setString(2, provincies.getNom());
            ps.setInt(3, provincies.getCodi_ine());
            ps.setInt(4, provincies.getNum_escons());
            ps.executeUpdate();
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes insertades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Provincies provincies) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM provincies WHERE provincia_id = ?");
            ps.setInt(1, provincies.getComunitat_aut_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("comunitat_aut_id") + " | " + rs.getString("nom") + " | " + rs.getInt("codi_ine") + " | " + rs.getInt("num_escons"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Provincies provincies) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE provincies SET nom = ? WHERE provincia_id = ?");
            ps.setString(1, provincies.getNom());
            ps.setInt(2, provincies.getComunitat_aut_id());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Provincies provincies) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM provincies WHERE provincia_id = ?");
            ps.setInt(1, provincies.getComunitat_aut_id());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
