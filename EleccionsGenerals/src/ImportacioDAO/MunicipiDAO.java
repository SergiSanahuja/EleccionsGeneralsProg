package ImportacioDAO;

import Clases.Main;
import Clases.Municipi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MunicipiDAO implements DAO<Municipi> {

    @Override
    public void create(Municipi o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO municipis (nom, codi_ine,provincia_id,districte) VALUES (?,?,?,?)");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCodi_ine());
            ps.setInt(3, o.getProvincia_id());
            ps.setString(4, o.getDistricte());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Municipi o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM municipis WHERE municipi_id = ?");
            ps.setInt(1, o.getMunicipiID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " | " + rs.getInt("codi_ine") + " | " + rs.getInt("provincia_id") + " | " + rs.getInt("districte"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Municipi o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE municipis SET nom = ?, districte = ? WHERE municipi_id = ?");
            ps.setString(1, o.getNom());
            ps.setString(2, o.getDistricte());
            ps.setInt(3, o.getMunicipiID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Municipi o) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM candidats WHERE municipi_id = ?");
            ps.setInt(1, o.getMunicipiID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
