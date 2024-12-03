import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//etape 1: spécification avec logique formelle
class LogiqueFormelle {
    public boolean verificationCarteValide(boolean estValide) {
        return estValide;
    }

    public boolean verificationCodeCorrect(boolean estCorrect) {
        return estCorrect;
    }
}

//etape 2 : Modélisation par automate
class AutomatesLangages {
    private String etat; //etats : initial, validation de la carte, validation du code, accès accordé, alarme, accès refusé

    public AutomatesLangages() {
        this.etat = "initial";
    }

    public void transitionEtat(String nouvelEtat) {
        this.etat = nouvelEtat;
    }



    public void afficherEtat() {
        System.out.println("L'état actuel est : " + etat);
    }
}

//etape 3 : Conception logique
class AccessSysteme {
    private AutomatesLangages automate;
    private Map<String, String> cartesCodes; // Stockage des cartes et codes valides

    public AccessSysteme() {
        this.automate = new AutomatesLangages();
        this.cartesCodes = new HashMap<>();
    }

    public void enregistrerCarte(String carteId, String code) {
        cartesCodes.put(carteId, code);
        System.out.println("La carte " + carteId + " a été enregistrée avec succès !");
    }

    public String verifierAcces(String carteId, String code) {
        automate.transitionEtat("Validation de la carte");
        if (!cartesCodes.containsKey(carteId)) {
            automate.transitionEtat("Alarme");
            return "Accès refusé, Carte invalide, Alarme déclenchée.";
        }

        automate.transitionEtat("Validation du code");
        if (cartesCodes.get(carteId).equals(code)) {
            automate.transitionEtat("Accès accordé");
            return "Accès accordé.";
        } else {
            automate.transitionEtat("Accès refusé");
            return "Accès refusé, Code incorrect.";
        }
    }

    public void afficherEtatAutomate() {
        automate.afficherEtat();
    }
}

//etape 4:vérification et validation formelle du système avec menu
public class ControleAccess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccessSysteme sys = new AccessSysteme();

        boolean continuer = true;

        while (continuer) {
            System.out.println("MENU DU SYSTÈME DE CONTRÔLE D'ACCÈS");
            System.out.println("1.Enregistrer une carte et un code");
            System.out.println("2.Tester une carte et un code");
            System.out.println("3.Afficher l'état actuel du système");
            System.out.println("4.Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Entrer l'ID de la carte : ");
                    String carteId = scanner.nextLine();

                    System.out.print("Entrer le code associé : ");
                    String code = scanner.nextLine();

                    sys.enregistrerCarte(carteId, code);
                    break;

                case 2:
                    System.out.print("Entrer l'ID de votre carte : ");
                    String entreeCarteId = scanner.nextLine();

                    System.out.print("Entrer votre code : ");
                    String entreeCode = scanner.nextLine();

                    String resultat = sys.verifierAcces(entreeCarteId, entreeCode);
                    System.out.println("Le résultat : " + resultat);
                    break;

                case 3:
                    System.out.println("l'état actuel");
                    sys.afficherEtatAutomate();
                    break;

                case 4:
                    System.out.println("Merci d'avoir utilisé le système. Au revoir !");
                    continuer = false;
                    break;

                default:
                    System.out.println("Option invalide, veuillez choisir une option entre 1 et 4.");
            }
        }

        scanner.close();
    }
}
