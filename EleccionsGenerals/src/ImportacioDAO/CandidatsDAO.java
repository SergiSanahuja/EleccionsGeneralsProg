package ImportacioDAO;

import Clases.Candidats;
import Clases.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CandidatsDAO implements DAO<Candidats>{

    @Override
    public void create(Candidats c) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO candidats (candidatura_id, persona_id, provincia_id,num_ordre,tipus) VALUES (?,?,?,?,?)");
            ps.setInt(1, c.getCandidaturaID());
            ps.setInt(2, c.getPersonaID());
            ps.setInt(3, c.getProvinciaID());
            ps.setInt(4, c.getNum_ordre());
            ps.setString(5, c.getTipus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Candidats c) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM candidats WHERE candidat_id = ?");
            ps.setInt(1, c.getCandidatID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("candidat_id") + " | " + rs.getInt("candidatura_id") + " | " + rs.getInt("persona_id") + " | " + rs.getInt("provincia_id") + " | " + rs.getInt("num_ordre") + " | " + rs.getString("tipus"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Candidats c) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE candidats SET persona_id = ?, provincia_id = ? WHERE candidat_id = ?");
            ps.setInt(1, c.getPersonaID());
            ps.setInt(2, c.getProvinciaID());
            ps.setInt(3, c.getCandidatID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Candidats c) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM candidats WHERE candidat_id = ?");
            ps.setInt(1, c.getCandidatID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
