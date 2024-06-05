package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entities.Inscription;

public class InscriptionRepository extends Database{
    private final String SQL_INSERT="INSERT INTO `inscription` (`date`, `montant`, `anneeScolaire`, `etudiant_id`) VALUES (?,?,?,?)";
    private final String SQL_SELECT_ALL="SELECT * FROM `inscription`";

    public void insert(Inscription inscription) {
        try {
            openConnexion();
            initPrepareStatement(SQL_INSERT);
            // conversion
            statement.setDate(1, new java.sql.Date(new Date().getTime()));
            statement.setDouble(2, inscription.getMontant());
            statement.setString(3, inscription.getAnnéeScolaire());
            statement.setInt(4, inscription.getEtudiant().getId());
            int NbreLigne= executeUpdate();
            statement.close();
            closeConnexion();
        } 
        catch (SQLException e) {
            System.out.println("Erreur de  requête : Inscrire étudiant.");
        }
        
    }

     public List<Inscription> selectAllInscriptions(){
        List<Inscription> inscriptions=new ArrayList<>();
        
        try {
            openConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            // conversion
            ResultSet rs=executeSelect();
            while (rs.next()) {
                
                Inscription inscription=new Inscription();
                inscription.setId(rs.getInt("id_inscription"));
                inscription.setDate(rs.getDate("date"));
                inscription.setMontant(rs.getDouble("montant"));
                inscription.setAnnéeScolaire(rs.getString("anneeScolaire"));
                inscriptions.add(inscription);
            }
                
                closeConnexion();
            } catch (SQLException e) {
                System.out.println("Erreur de  requête : Afficher les classes");
            }
            
        return inscriptions;
    }
}
