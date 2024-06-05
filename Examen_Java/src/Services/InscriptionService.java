package Services;

import Entities.Inscription;
import Repositories.InscriptionRepository;

public class InscriptionService {
     InscriptionRepository inscriptionRepository=new InscriptionRepository();

    public void inscrireEtudiant(Inscription inscription) {
        inscriptionRepository.insert(inscription);
    }
    
    // public List<Inscription> listerClasses(){
    //     return inscriptionRepository.selectAll();
    // }

}
