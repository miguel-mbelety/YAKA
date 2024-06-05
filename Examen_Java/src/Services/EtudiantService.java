package Services;

import java.util.List;

import Entities.Etudiant;
import Repositories.EtudiantRepository;

public class EtudiantService {
    EtudiantRepository etudiantRepository=new EtudiantRepository();
    
    public void ajouterEtudiant(Etudiant etudiant){
        etudiantRepository.insert(etudiant);
    }
    
    public List<Etudiant> ListerEtudiants(){
        return etudiantRepository.selectAll();
    }

    public Etudiant rechercherParNom(String nom) {
        return etudiantRepository.selectByNom(nom);
    }



    public List<Etudiant> FillterParClasses(){
        return etudiantRepository.FilterByClass();
    }

    // public void reinscrireEtudiant(){}
}
