package ImportacioDAO;

import Clases.Candidatures;
import Clases.Main;

import java.sql.PreparedStatement;

public class CandidaturesDAO implements DAO<Candidatures> {


    @Override
    public void create(Candidatures candidatures) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO candidatures (eleccio_id, codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, candidatures.getEleccioID());
            ps.setInt(2, candidatures.getCodiCandidatura());
            ps.setString(3, candidatures.getNom_curt());
            ps.setString(4, candidatures.getNom_llarg());
            ps.setInt(5, candidatures.getCodi_acumulacio_prov());
            ps.setInt(6, candidatures.getCodi_acumulacio_ca());
            ps.setInt(7, candidatures.getCodi_acumulacio_nacional());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Candidatures candidatures) {

    }

    @Override
    public void update(Candidatures candidatures) {

    }

    @Override
    public void delete(Candidatures candidatures) {

    }
}
