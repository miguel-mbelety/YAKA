package Services;

import java.util.List;

import Entities.Classe;
import Repositories.ClasseRepository;

public class ClasseService {
     ClasseRepository classeRepository=new ClasseRepository();

    public void ajouterClasse(Classe classe){
        classeRepository.insert(classe);
    }   

    public List<Classe> listerClasses(){
        return classeRepository.selectAll();
    }

    public Classe rechercherParLibelle(String libelle) {
        return classeRepository.selectByLibelle(libelle);
    }
    
}
