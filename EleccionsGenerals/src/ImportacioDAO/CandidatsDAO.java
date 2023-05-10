package ImportacioDAO;

import Clases.Candidats;
import Clases.Main;

import java.sql.PreparedStatement;

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

    }

    @Override
    public void update(Candidats c) {

    }

    @Override
    public void delete(Candidats c) {

    }
}
