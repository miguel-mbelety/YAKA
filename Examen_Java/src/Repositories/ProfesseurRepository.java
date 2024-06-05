package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Classe;
import Entities.EnumGrade;
import Entities.Professeur;

public class ProfesseurRepository extends Database{
    private final String SQL_INSERT="INSERT INTO `professeur` (`nci`, `nomComplet`, `grade`) VALUES (?,?,?)";
    private final String SQL_SELECT_ALL="SELECT * FROM `professeur`";
    private final String SQL_SELECT_BY_NCI="SELECT * FROM `professeur` WHERE nci LIKE ?";
    
    public void insert(Professeur professeur) {
        try {
            openConnexion();
            initPrepareStatement(SQL_INSERT);
            // conversion
            statement.setString(1, professeur.getNci());
            statement.setString(2, professeur.getNomComplet());
            statement.setInt(3, professeur.getGrade().ordinal());
            int NbreLigne= executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur de  requête : insérer professeur.");
        }
        closeConnexion();

    }

    public List<Professeur> selectAll() {
        List<Professeur> profs=new ArrayList<>();
        
        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            // conversion
            ResultSet rs=executeSelect();
            while (rs.next()) {
                Professeur prof=new Professeur();
                prof.setId(rs.getInt("id_prof"));
                prof.setNci(rs.getString("nci"));
                prof.setNomComplet(rs.getString("nomComplet"));
                int grade=rs.getInt("grade");
                if (grade==0) {
                    prof.setGrade(EnumGrade.Stagiaire);
                }else  {
                    prof.setGrade(EnumGrade.Contractuel);
                }
                profs.add(prof);
                }

            } catch (SQLException e) {
                System.out.println("Erreur de  requête : Afficher professeurs.");
            }
            
        closeConnexion();
        return profs;
    }

    
    // Affecter une classe
    public void insertClasse(Classe classe){

    }


    public Professeur selectByNci(String nci){
        Professeur professeur=null;

        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_BY_NCI);
            statement.setString(1, nci);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                professeur=new Professeur();
                professeur.setId(rs.getInt("id_prof"));
                professeur.setNci(rs.getString("nci"));
                professeur.setNomComplet(rs.getString("nomComplet"));
                int grade=rs.getInt("grade");
                switch (grade){
                    case 0:
                        professeur.setGrade(EnumGrade.Stagiaire);
                    break;
                    case 1:
                        professeur.setGrade(EnumGrade.Contractuel);
                    break;
                }
                        
                rs.close();                
                statement.close();
                closeConnexion();
            }
        } catch (SQLException e) {
            System.out.println("Erreur de  requête : Select Professeur By Nci. "+ e);
        }
        return professeur;

    }

    // Filtrer les classes
    public Professeur FilterByClass(){
        return null;
    }

}
