package com.nexabank.main;
import com.nexabank.exception.CompteInexistantException;
import com.nexabank.exception.ErreurFichierException;
import com.nexabank.exception.MontantInvalideException;
import com.nexabank.exception.SoldeInsuffisantException;
import com.nexabank.model.*;
import com.nexabank.service.GestionnaireFichier;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Client> clients;
    private static Gestionnaire gestionnaire;
    public static void main(String[] args) {
        clients = InitDonnees.creerClientsTest();
        gestionnaire = InitDonnees.creerGestionnaireTest();

        System.out.println("Bienvenue chez NexaBank");
        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n1. Se connecter en tant que Client");
            System.out.println("2. Se connecter en tant que Gestionnaire");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> menuClient();
                case "2" -> menuGestionnaire();
                case "3" -> quitter = true;
                default -> System.out.println("Choix invalide, réessayez.");
            }
        }

        System.out.println("Au revoir");
        scanner.close();
    }

    private static void menuClient() {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        Client clientConnecte = authentifierClient(email, password);

        if (clientConnecte == null) {
            System.out.println("Email ou mot de passe incorrect.");
            return;
        }

        System.out.println("Connexion réussie. Bienvenue " + clientConnecte.getPrenom() + " !");

        boolean retourMenuPrincipal = false;
        while (!retourMenuPrincipal) {
            System.out.println("\n--- Menu Client ---");
            System.out.println("1. Consulter le solde d'un compte");
            System.out.println("2. Effectuer un dépôt");
            System.out.println("3. Effectuer un retrait");
            System.out.println("4. Réaliser un virement");
            System.out.println("5. Consulter le relevé bancaire");
            System.out.println("6. Retour au menu principal");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> actionConsulterSolde(clientConnecte);
                case "2" -> actionDeposer(clientConnecte);
                case "3" -> actionRetirer(clientConnecte);
                case "4" -> actionVirement(clientConnecte);
                case "5" -> actionConsulterReleve(clientConnecte);
                case "6" -> retourMenuPrincipal = true;
                default -> System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    private static Client authentifierClient(String email, String password) {
        for (Client c : clients) {
            if (c.seConnecter(email, password)) {
                return c;
            }
        }
        return null;
    }

    private static void actionConsulterSolde(Client client) {
        try {
            Compte compte = trouverCompte(client);
            System.out.println("Solde du compte " + compte.getNumCompte() + " : " + compte.getSolde() + " €");
        } catch (CompteInexistantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void actionDeposer(Client client) {
        try {
            Compte compte = trouverCompte(client);
            System.out.print("Montant à déposer : ");
            double montant = Double.parseDouble(scanner.nextLine());

            compte.deposer(montant);
            System.out.println("Dépôt effectué. Nouveau solde : " + compte.getSolde() + " €");

        } catch (CompteInexistantException | MontantInvalideException | ErreurFichierException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erreur : veuillez saisir un montant valide (nombre).");
        }
    }

    private static void actionRetirer(Client client) {
        try {
            Compte compte = trouverCompte(client);
            System.out.print("Montant à retirer : ");
            double montant = Double.parseDouble(scanner.nextLine());

            compte.retirer(montant);
            System.out.println("Retrait effectué. Nouveau solde : " + compte.getSolde() + " €");

        } catch (CompteInexistantException | MontantInvalideException | SoldeInsuffisantException | ErreurFichierException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erreur : veuillez saisir un montant valide (nombre).");
        }
    }

    private static void actionVirement(Client client) {
        try {
            Compte compteSource = trouverCompte(client);

            System.out.print("Numéro du compte destination : ");
            String numDestination = scanner.nextLine();
            Compte compteDestination = trouverCompteParNumero(numDestination);

            System.out.print("Montant à virer : ");
            double montant = Double.parseDouble(scanner.nextLine());

            compteSource.virement(compteDestination, montant);
            System.out.println("Virement effectué. Nouveau solde du compte source : " + compteSource.getSolde() + " €");

        } catch (CompteInexistantException | MontantInvalideException | SoldeInsuffisantException | ErreurFichierException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erreur : veuillez saisir un montant valide (nombre).");
        }
    }

    private static void actionConsulterReleve(Client client) {
        try {
            Compte compte = trouverCompte(client);
            String releve = GestionnaireFichier.lireReleve(compte.getNumCompte());

            if (releve.isEmpty()) {
                System.out.println("Aucune transaction enregistrée pour ce compte.");
            } else {
                System.out.println("--- Relevé du compte " + compte.getNumCompte() + " ---");
                System.out.println(releve);
            }

        } catch (CompteInexistantException | ErreurFichierException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static Compte trouverCompte(Client client) throws CompteInexistantException {
        System.out.print("Numéro de compte : ");
        String numCompte = scanner.nextLine();

        Compte compte = client.getComptes().get(numCompte);
        if (compte == null) {
            throw new CompteInexistantException("Aucun compte trouvé avec ce numéro pour ce client.");
        }
        return compte;
    }

    private static Compte trouverCompteParNumero(String numCompte) throws CompteInexistantException {
        for (Client c : clients) {
            Compte compte = c.getComptes().get(numCompte);
            if (compte != null) {
                return compte;
            }
        }
        throw new CompteInexistantException("Aucun compte trouvé avec ce numéro : " + numCompte);
    }
}