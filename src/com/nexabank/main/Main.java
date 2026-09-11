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
}