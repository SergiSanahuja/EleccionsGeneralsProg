package ImportacioDAO;

import Clases.Main;
import Clases.Persones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonesDAO implements DAO<Persones>{


    @Override
    public void create(Persones p) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO persones (nom, cog1, cog2,sexe,data_naixement,dni) VALUES (?,?,?,?,?,?)");
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCog1());
            ps.setString(3, p.getCog2());
            ps.setString(4, p.getSexe());
            ps.setDate(5,  new java.sql.Date(p.getData_naixement().getTime()));
            ps.setString(6, p.getDNI());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Persones p) {
        try{
            PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM persones WHERE persona_id = ?");
            ps.setInt(1, p.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("persona_id") + " | " + rs.getString("nom") + " | " + rs.getString("cog1") + " | " + rs.getString("cog2") + " | " + rs.getString("sexe") + " | " + rs.getDate("data_naixement") + " | " + rs.getString("dni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Persones p) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE persones SET nom = ?, cog1 = ?, cog2 = ? WHERE persona_id = ?");
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCog1());
            ps.setString(3, p.getCog2());
            ps.setInt(4, p.getId());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Persones p) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM persones WHERE persona_id = ?");
            ps.setInt(1, p.getId());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
