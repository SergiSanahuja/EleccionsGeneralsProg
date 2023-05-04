package ImportacioDAO;

import java.sql.PreparedStatement;

public class ComunitatsAutonomesDAO implements DAO{

    @Override
    public void create(Object o) {
    }

    public void read(Object o) {

    }
    @Override
    public void read(int id) {
        String query = "Select nom from comunitats_autonomes where comunitat_aut_id = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, nomComunitat);
        preparedStmt.setInt(2, codiINEComunitat);

        // execute the preparedstatement
        preparedStmt.execute();

    }

    @Override
    public void update(Object o) {
    }

    @Override
    public void delete(Object o) {

    }
}
