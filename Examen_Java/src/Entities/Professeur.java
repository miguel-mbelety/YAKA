package Entities;

import java.util.Set;

public class Professeur {
private int id;
    private String nci;
    private String nomComplet;

    // Enum 
    private EnumGrade grade;

    // Relation Many To Many
    private Set<Classe> classes;
    
// getters and setters sont des méthodes qui permettent de lire
// modifier les valeurs des attributs privés d'une classe
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public EnumGrade getGrade() {
        return grade;
    }

    public void setGrade(EnumGrade grade) {
        this.grade = grade;
    }

    public Set<Classe> getClasses() {
        return classes;
    }

    public void setClasses(Set<Classe> classes) {
        this.classes = classes;
    }

}
