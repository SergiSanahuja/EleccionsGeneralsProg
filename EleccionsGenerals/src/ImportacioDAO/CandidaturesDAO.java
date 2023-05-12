package ImportacioDAO;

import Clases.Candidatures;
import Clases.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CandidaturesDAO implements DAO<Candidatures> {


    @Override
    public void create(Candidatures candidatures) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("INSERT INTO candidatures (eleccio_id, codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, candidatures.getEleccioID());
            ps.setString(2, candidatures.getCodiCandidatura());
            ps.setString(3, candidatures.getNom_curt());
            ps.setString(4, candidatures.getNom_llarg());
            ps.setString(5, candidatures.getCodi_acumulacio_prov());
            ps.setString(6, candidatures.getCodi_acumulacio_ca());
            ps.setString(7, candidatures.getCodi_acumulacio_nacional());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(Candidatures candidatures) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("SELECT * FROM candidatures WHERE candidatura_id = ?");
            ps.setInt(1, candidatures.getCandidaturaID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("eleccio_id") + " | " + rs.getInt("codi_candidatura") + " | " + rs.getString("nom_curt") + "  | " + rs.getString("nom_llarg") + " | " + rs.getInt("codi_acumulacio_provincia") + " | " + rs.getInt("codi_acumulacio_ca") + " | " + rs.getInt("codi_acumulario_nacional"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Candidatures candidatures) {
        try {
            PreparedStatement ps = Main.con.prepareStatement("UPDATE candidatures SET nom_curt = ?, nom_llarg = ? WHERE candidatura_id = ?");
            ps.setString(1, candidatures.getNom_curt());
            ps.setString(2, candidatures.getNom_llarg());
            ps.setInt(3, candidatures.getCandidaturaID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes modificades: " + columnMod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Candidatures candidatures) {
        try{
            PreparedStatement ps = Main.con.prepareStatement("DELETE FROM candidatures WHERE candidatura_id = ?");
            ps.setInt(1, candidatures.getCandidaturaID());
            int columnMod = ps.executeUpdate();
            System.out.println("Columenes eliminades: " + columnMod);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
