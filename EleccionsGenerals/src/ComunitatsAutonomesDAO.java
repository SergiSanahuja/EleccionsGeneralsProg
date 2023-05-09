import ImportacioDAO.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComunitatsAutonomesDAO implements DAO<ComunitatAutonoma> {

    @Override
    public void create(ComunitatAutonoma o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO comunitats_autonomes (nom, codi_ine) VALUES (?,?)");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCodiINE());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(ComunitatAutonoma o) {
        try {
         PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM comunitats_autonomes WHERE comunitat_aut_id = ?");
            ps.setInt(1, o.getComunitatAutonomaId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("comunitat_aut_id") + " | " + rs.getString("nom") + " | " + rs.getInt("codi_ine"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ComunitatAutonoma o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE comunitats_autonomes SET nom = ?, codi_ine = ? WHERE comunitat_aut_id = ?");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCodiINE());
            ps.setInt(3, o.getComunitatAutonomaId());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete (ComunitatAutonoma o){
        try {
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM comunitats_autonomes WHERE comunitat_aut_id = ?");
            ps.setInt(1, o.getComunitatAutonomaId());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
