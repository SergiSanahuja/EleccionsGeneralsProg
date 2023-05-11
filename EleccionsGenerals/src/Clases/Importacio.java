package Clases;

import java.sql.Connection;

public class Importacio {
   public static void importacions(Connection con){
       //Importacio de les Comunitats Autonomes
       ComunitatAutonoma.importarComunitatsAutonomes(con);

       //Crud Comunitats Autonomes
       // ComunitatAutonoma.insertComunitatAutonoma();
       // ComunitatAutonoma.updateComunitatAutonoma();
       // ComunitatAutonoma.readComunitatAutonoma();
       // ComunitatAutonoma.deleteComunitatAutonoma();


       //importar provincies
       Provincies.importarProvincies(con);

       //importar municipis
       Municipi.importarMunicipis(con);

       //importacio persones
       Persones.importarPersones(con);

       //importar candidats
       Candidats.importarCandidats(con);

       //importar candidatures
       Candidatures.importarCandidatures(con);
   }
}
