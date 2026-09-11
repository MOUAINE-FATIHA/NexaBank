package com.nexabank.main;
import com.nexabank.model.*;
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

}