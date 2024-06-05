package Entities;

import java.util.Date;

public class Inscription {
    private int id;
    private Date date;
    private Double montant;
    private String annéeScolaire;
    
    // Relation Many To One 
    private Etudiant etudiant;
    
    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getMontant() {
        return montant;
    }
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    public String getAnnéeScolaire() {
        return annéeScolaire;
    }
    public void setAnnéeScolaire(String annéeScolaire) {
        this.annéeScolaire = annéeScolaire;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

}
