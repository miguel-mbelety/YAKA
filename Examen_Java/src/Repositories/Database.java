package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
protected Connection conn=null;
    protected PreparedStatement statement=null;

    public void openConnexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3308/examen_gestion_des_inscription",
            "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du Driver");    
        }
        catch (SQLException e) {
            System.out.println("L'erreur c'est : "+ e);
        }
    }

    public void closeConnexion(){
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                
                System.out.println("Erreur de Fermeture de Connection");    
            }
        }
    }

    public void initPrepareStatement(String sql){
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Erreur d'initialisation du Prepare Statement");
        }
        

    }

    public ResultSet  executeSelect(){
        ResultSet rs=null;
          try {
              rs= statement.executeQuery();
          } catch (Exception e) {
              System.out.println("Erreur Initialisation de Requete" + e);
          }
          return rs;
      }
       
    public int executeUpdate() {
        int NbreLigne=0;
        try {
            NbreLigne = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la Requete : " + e);
        }
        return NbreLigne;
    }

}
