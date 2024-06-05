package Services;

import java.util.List;

import Entities.Classe;
import Entities.Professeur;
import Repositories.ProfesseurRepository;

public class ProfesseurService {
     ProfesseurRepository professeurRepository=new ProfesseurRepository();

    public void ajouterProfesseur(Professeur professeur){
        professeurRepository.insert(professeur);
    }   

    public List<Professeur> ListerProfesseur(){
        return professeurRepository.selectAll();
    }

    public void AffecterUneClasse(Classe classe){
        professeurRepository.insertClasse(classe);
    }

    public Professeur rechercherParNci(String nci){
        return professeurRepository.selectByNci(nci);
    }

    public Professeur FiltrerParClassesDuProf(){
        return professeurRepository.FilterByClass();
    }
}
