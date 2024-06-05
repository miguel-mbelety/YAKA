package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Classe;
import Entities.EnumFillière;
import Entities.EnumNiveau;
import Entities.Etudiant;

public class EtudiantRepository extends Database{
    private final String SQL_INSERT="INSERT INTO `etudiant` (`matricule`, `nomComplet`, `tuteur`, `classe_id`) VALUES (?,?,?,?)";
    

    // private final String SQL_SELECT_ALL="SELECT * FROM `etudiant`";
    private final String SQL_SELECT_ALL="SELECT * FROM `etudiant` e, Classe c WHERE e.classe_id=c.id_classe";
    private final String SQL_SELECT_BY_NOM="SELECT * FROM `inscription` WHERE nom LIKE ?";
    

    public void insert(Etudiant etudiant){
        try {
            openConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, etudiant.getMatricule());
            statement.setString(2, etudiant.getNomComplet());
            statement.setString(3, etudiant.getTuteur());
            statement.setInt(4, etudiant.getClasse().getId());
            int NbreLigne=executeUpdate();
            statement.close();
            closeConnexion();
        } 
        catch (SQLException e) {
            System.out.println("Erreur de  requête : Insérer Etudiant. "+ e);
        }
    }

    public List<Etudiant> selectAll(){
        List<Etudiant> etudiants=new ArrayList<>();

        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            ResultSet rs= executeSelect();
            while (rs.next()) {
                Classe classe=new Classe();
                classe.setId(rs.getInt("id_classe"));
                classe.setLibelle(rs.getString("libelle"));
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

                Etudiant etudiant=new Etudiant();
                etudiant.setId(rs.getInt("id_etudiant"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNomComplet(rs.getString("nomComplet"));
                etudiant.setTuteur(rs.getString("tuteur"));
                etudiant.setClasse(classe);
                etudiants.add(etudiant);
            }
            rs.close();
            statement.close();
            closeConnexion();

        } catch (SQLException e) {
            System.out.println("Erreur de  requête : Afficher étudiants.");
        }
        return etudiants;
    }
   
    public List<Etudiant> FilterByClass(){
        return null;
    }

    public Etudiant selectByNom(String nom){
        Etudiant etudiant=null;

        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_BY_NOM);
            statement.setString(1, nom);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                Classe classe=new Classe();
                classe.setId(rs.getInt("id_classe"));
                classe.setLibelle(rs.getString("libelle"));
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


                etudiant=new Etudiant();
                etudiant.setId(rs.getInt("id_etudiant"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNomComplet(rs.getString("nomComplet"));
                etudiant.setTuteur(rs.getString("tuteur"));
                etudiant.setClasse(classe);
                rs.close();
                statement.close();
                closeConnexion();
            }
        } catch (SQLException e) {
            System.out.println("Erreur de  requête : Select Etudiant BY NOM . "+ e);
        }



        return etudiant;
    }
}
