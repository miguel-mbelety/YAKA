import java.util.List;
import java.util.Scanner;

import Entities.Classe;
import Entities.EnumFillière;
import Entities.EnumGrade;
import Entities.EnumNiveau;
import Entities.Etudiant;
import Entities.Inscription;
import Entities.Professeur;
import Services.ClasseService;
import Services.EtudiantService;
import Services.InscriptionService;
import Services.ProfesseurService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        int choix;
        ClasseService classeService=new ClasseService();
        ProfesseurService profService=new ProfesseurService();
        EtudiantService etudiantService=new EtudiantService();
        InscriptionService inscriptionService=new InscriptionService();
        
        do {
            System.out.println("-----------------MENU-----------------");
            System.out.println("1-  Créer une classe");
            System.out.println("2-  Lister toutes les classes");
            System.out.println("3-  Ajouter un professeur");
            System.out.println("4-  Lister tous les professeur");
            System.out.println("5-  Inscrire un Etudiant");
            System.out.println("6-  Réinscrire un Etudiant");
            System.out.println("7-  Lister tous les Etudiants inscrits");
            System.out.println("8-  Quitter");
            choix=sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    Classe classe=new Classe();
                    System.out.println("Entrer le Libelle : ");                
                    String libelle=sc.nextLine();
                    System.out.println("Choisissez le niveau : "); 
                    System.out.println("1- L1"); 
                    System.out.println("2- L2"); 
                    System.out.println("3- L3"); 
                    int niveau=sc.nextInt();
                    sc.nextLine();

                    System.out.println("Choisissez la fillière : "); 
                    System.out.println("1- IAGE"); 
                    System.out.println("2- ETSE"); 
                    System.out.println("3- CPD"); 
                    System.out.println("4- GLRS"); 
                    int fillière=sc.nextInt();
                    sc.nextLine();

                    switch (niveau) {
                        case 1:
                            classe.setNiveau(EnumNiveau.L1);
                            break;
                        case 2:
                            classe.setNiveau(EnumNiveau.L2); 
                            break;
                        case 3:
                            classe.setNiveau(EnumNiveau.L3);
                            break;
                        default:
                            System.out.println("Vous avez inséré une valeur incorrecte");
                            break;
                    }

                    switch (fillière) {
                        case 1:
                            classe.setFillière(EnumFillière.ETSE);
                            break;
                        case 2: 
                            classe.setFillière(EnumFillière.IAGE);
                            break;
                        case 3: 
                            classe.setFillière(EnumFillière.GLRS);                        
                            break;
                        case 4: 
                            classe.setFillière(EnumFillière.CPD);
                            break;
                    
                        default:
                            System.out.println("Vous avez inséré une valeur incorrecte");
                            break;
                    }
               
                    classe.setLibelle(libelle);
                
                    classeService.ajouterClasse(classe);

                    break;
                case 2:
                List<Classe> classes= classeService.listerClasses();
                for (Classe c : classes) {
                    System.out.println("Libelle : "+ c.getLibelle());
                    System.out.println("Niveau : "+ c.getNiveau());
                    System.out.println("Fillière : "+ c.getFillière());
                    System.out.println("===========================");

                }
                    break;
                case 3:
                    Professeur prof=new Professeur();
                    System.out.println("Entrer le Nci du professeur : ");                
                    String nci=sc.nextLine();
                    System.out.println("Entrer le nom complet du professeur : ");                
                    String nomComplet=sc.nextLine();
                    System.out.println("Choisissez le grade : ");
                    System.out.println("1- Stagiaire");                
                    System.out.println("2- Contractuel");
                    int grade=sc.nextInt();
                    sc.nextLine();
                    switch (grade) {
                        case 1:
                            prof.setGrade(EnumGrade.Stagiaire);
                            break;
                        case 2:
                            prof.setGrade(EnumGrade.Contractuel);
                            break;
                    
                        default:
                        System.out.println("Vous avez saisi n'importe quoi");
                            break;
                    }
                    prof.setNci(nci);
                    prof.setNomComplet(nomComplet);
                    profService.ajouterProfesseur(prof);
                    break;
                case 4:
                    List<Professeur> profs=profService.ListerProfesseur();
                    for (Professeur p : profs) {
                        System.out.println("Nci : "+ p.getNci());
                        System.out.println("Nom Complet : "+ p.getNomComplet());
                        System.out.println("Grade : "+ p.getGrade());
                        System.out.println("================================");
                    }
                    
                    System.out.println("Voulez-vous affecter une classe à un professeur {oui / non} ? ");
                    String rep_prof=sc.nextLine();
                    switch (rep_prof) {
                        case "oui":
                            System.out.println("Entrer le Nci du professeur : ");
                            String nci_1=sc.nextLine();
                            Professeur professeur=profService.rechercherParNci(nci_1);
                            if (professeur!=null) {
                                // Permet d'afficher classes 
                                List<Classe> classes_A= classeService.listerClasses();
                                for (Classe c : classes_A) {
                                    System.out.println("Libelle : "+ c.getLibelle());
                                }    
                                System.out.println("Entrer le libelle de la classe.");
                                String libelleA=sc.nextLine();
                                Classe classe2=classeService.rechercherParLibelle(libelleA);
                                if (classe2!=null) {
                                    // Affecter 
                                    profService.AffecterUneClasse(classe2);                                    
                                }
                            }else{
                                System.out.println("Le Nci saisi n'appartient à aucun prof.");
                            }                            
                            break;

                        case "non":
                            System.out.println("Ok");
                            break;
                    
                        default:
                            System.out.println("vous n'avez pas répondu correctement.");
                            break;
                    }
                    
                    System.out.println("Voulez-vous lister les classes d'un professeur  {oui / non} ? ");
                    String rep_prof1=sc.nextLine();
                    switch (rep_prof1) {
                        case "oui":
                            System.out.println("Entrer le Nci du professeur : ");
                            String nci_2=   sc.nextLine();
                            Professeur professeur=profService.rechercherParNci(nci_2);
                            if (professeur!=null) {
                                // Afficher classes 
                            }
                            
                            break;
                        case "non":
                            
                            break;
                    
                        default:
                            System.out.println("Le Nci saisi n'appartient à aucun prof.");
                            break;
                    }

                    break;

                case 5:
                    // Ajouter etudiant
                    Etudiant etudiant=new Etudiant();
                    System.out.println("Entrer le nom : ");        
                    String nomE=sc.nextLine();
                    System.out.println("Entrer le matricule : ");
                    String matricule=sc.nextLine();
                    System.out.println("Entrer le nom du tuteur : ");
                    String tuteur=sc.nextLine();

                    // Lister les classes
                    List<Classe> classes_1= classeService.listerClasses();
                    for (Classe c : classes_1) {
                        System.out.println("Libelle : "+ c.getLibelle());
                    }
                    // Insérer la classe de l'étudiant
                    // System.out.println("Choisissez la classe de l'etudiant : ");
                    System.out.println("Entrer le libelle de la classse : ");
                    String libelle_1=sc.nextLine();
                    Classe classe_1= classeService.rechercherParLibelle(libelle_1);
                    if (classe_1!=null) {
                         etudiant.setClasse(classe_1);              
                         etudiant.setMatricule(matricule);
                         etudiant.setNomComplet(nomE);
                         etudiant.setTuteur(tuteur);
                         etudiantService.ajouterEtudiant(etudiant);

                        // Faire l'incription 
                        Inscription inscription=new Inscription();
                         System.out.println("Entrer le montant de l'inscription : ");
                         Double montant=sc.nextDouble();
                         sc.nextLine();
                         System.out.println("Entrer l'année scolaire { ex : 2006, 2014} : ");
                         String anneeScolaire=sc.nextLine();
                         inscription.setAnnéeScolaire(anneeScolaire);
                         inscription.setMontant(montant);
                         inscription.setEtudiant(etudiant);
                         inscriptionService.inscrireEtudiant(inscription);
                         }
                    else  {
                        System.out.println("La classe n'existe pas : ");  
                    }
                    break;
                case 6:
                    
                    break;
                case 7:
                    List<Etudiant> etudiants= etudiantService.ListerEtudiants();
                    for (Etudiant e : etudiants) {
                        System.out.println("Nom Complet : "+ e.getNomComplet());
                        System.out.println("Matricule : "+ e.getMatricule());
                        System.out.println("Tuteur : "+ e.getTuteur());
                        System.out.println("Libelle Classe : "+ e.getClasse().getLibelle());
                        System.out.println("Niveau : "+ e.getClasse().getNiveau());
                        System.out.println("Fillière : "+ e.getClasse().getFillière());
                        System.out.println("================================");
                    }
                    System.out.println("Voulez-vous affichez les étudiants par classe {oui / non} ? ");
                    String rep=sc.nextLine();
                    switch (rep) {
                        case "oui":
                                for (Etudiant e2 : etudiants) {
                                    System.out.println("Salle : "+ e2.getClasse().getLibelle()+" "+ e2.getNomComplet());
                                }
                            break;
                        
                        case "non":
                            System.out.println("Ok");
                            break;
                    
                        default:
                            System.out.println("vous n'avez pas répondu correctement.");
                            break;
                    }
                    break;
               default:
                    break;
            }
                
        } while (choix!=8);
        
        sc.close();
    }
}
