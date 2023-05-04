import ImportacioDAO.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComunitatsAutonomesDAO implements DAO<ComunitatAutonoma> {

    @Override
    public void create(ComunitatAutonoma o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO comunitats_autonomes (nom, codi_ine) VALUES (?,?)");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCodiINE());
            ps.executeUpdate();
            System.out.println("La taula de Comunitats Autonomes s'ha importat correctament.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(ComunitatAutonoma o) {
        try {
         PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM comunitats_autonomes WHERE ComunitatAutonomaId = ?");
            ps.setInt(1, o.getComunitatAutonomaId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("ComunitatAutonomaId") + " " + rs.getString("nom") + " " + rs.getInt("codiINE"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ComunitatAutonoma o) {

    }

    @Override
    public void delete(ComunitatAutonoma o) {

    }
}
