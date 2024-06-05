package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Classe;
import Entities.EnumFillière;
import Entities.EnumNiveau;

public class ClasseRepository extends Database{
     private final String SQL_INSERT="INSERT INTO `classe` (`Libelle`, `fillière`, `niveau`) VALUES (?,?,?)";
    private final String SQL_SELECT_ALL="SELECT * FROM `classe`";
    private final String SQL_SELECT_BY_LIBELLE="SELECT * FROM `classe` WHERE `Libelle` LIKE ?";
    
    public void insert(Classe classe) {
        openConnexion();
        try {
            initPrepareStatement(SQL_INSERT);
            // conversion
            statement.setString(1, classe.getLibelle());
            statement.setInt(2, classe.getFillière().ordinal());
            statement.setInt(3, classe.getNiveau().ordinal());
            int NbreLigne= executeUpdate();
            statement.close();
            closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur de  requête : Ajouter une classe.");
        }
        
        

    }

    public List<Classe> selectAll(){
        List<Classe> classes=new ArrayList<>();
        
        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            // conversion
            ResultSet rs=executeSelect();
            while (rs.next()) {
                Classe classe=new Classe();
                 classe.setId(rs.getInt("id_classe"));
                 classe.setLibelle(rs.getString("Libelle"));
                 int fillière=rs.getInt("fillière");
                 switch (fillière){

                case 0:
                    classe.setFillière(EnumFillière.ETSE);
                    break;
                case 1:
                    classe.setFillière(EnumFillière.IAGE);	
                    break;
                case 2:
                    classe.setFillière(EnumFillière.GLRS);
                    break;
                case 3:
                    classe.setFillière(EnumFillière.CPD);  
                    break;
                }

                int niveau=rs.getInt("niveau");
                switch (niveau){

                case 0:
                    classe.setNiveau(EnumNiveau.L1);  
                    break;
                case 1:
                    classe.setNiveau(EnumNiveau.L2);    
                    break;
                case 2:
                    classe.setNiveau(EnumNiveau.L3);    
                    break;
                }
                classes.add(classe);
                }

            } catch (SQLException e) {
                System.out.println("Erreur de  requête : Afficher les classes");
            }
            
        closeConnexion();
        return classes;
    }
  
    public  Classe selectByLibelle(String libelle){
        Classe classe=null;

     try {
          openConnexion();
          initPrepareStatement(SQL_SELECT_BY_LIBELLE);
          statement.setString(1, libelle);
          ResultSet rs= executeSelect();
          if (rs.next()) {
                classe=new Classe();
                classe.setId(rs.getInt("id_classe"));
                classe.setLibelle(rs.getString("Libelle"));
                int fillière=rs.getInt("filliere");
                 switch (fillière){

                case 0:
                    classe.setFillière(EnumFillière.ETSE);
                    break;
                case 1:
                    classe.setFillière(EnumFillière.IAGE);	
                    break;
                case 2:
                    classe.setFillière(EnumFillière.GLRS);
                    break;
                case 3:
                    classe.setFillière(EnumFillière.CPD);  
                    break;
                }
                
                int niveau=rs.getInt("niveau");
                switch (niveau){

                case 0:
                    classe.setNiveau(EnumNiveau.L1);  
                    break;
                case 1:
                    classe.setNiveau(EnumNiveau.L2);    
                    break;
                case 2:
                    classe.setNiveau(EnumNiveau.L3);    
                    break;
                }
            }
            rs.close();
            statement.close();
            closeConnexion();
        } 
        catch (SQLException e) {
            System.out.println("Erreur de  requête : Recherche par Libelle");
        }
      return  classe;
     }

}
