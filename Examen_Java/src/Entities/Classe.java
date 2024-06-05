package Entities;

import java.util.ArrayList;
import java.util.Set;

public class Classe {
    private int id;
    private String libelle;

    // Enum 
    private EnumFillière fillière;
    private EnumNiveau niveau;
    
    // Relation One To Many (un à plusieur)
    private ArrayList<Etudiant> etudiants;

    // Relation Many To Many
    private Set<Professeur> professeurs;
 
    // getters and setters sont des méthodes qui permettent de lire
// modifier les valeurs des attributs privés d'une classe
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public EnumFillière getFillière() {
        return fillière;
    }

    public void setFillière(EnumFillière etse) {
        this.fillière = etse;
    }

    public EnumNiveau getNiveau() {
        return niveau;
    }

    public void setNiveau(EnumNiveau niveau) {
        this.niveau = niveau;
    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Set<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(Set<Professeur> professeurs) {
        this.professeurs = professeurs;
    }

}
