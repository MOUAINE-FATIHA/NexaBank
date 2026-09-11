package com.nexabank.main;
import com.nexabank.model.*;
import java.util.ArrayList;
import java.util.List;
public class InitDonnees {

    public static List<Client> creerClientsTest() {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1, "majbar", "Sara", "saramajbar@gmail.com", "1234");
        CompteCourant compte1 = new CompteCourant(1001, 500.0);
        client1.ajouterCompte(compte1);
        clients.add(client1);

        Client client2 = new Client(2, "nassik", "taha", "tahanassik@gmail.com", "5678");
        CompteEpargne compte2 = new CompteEpargne(1002, 1000.0);
        client2.ajouterCompte(compte2);
        clients.add(client2);
        return clients;
    }

    public static Gestionnaire creerGestionnaireTest() {
        return new Gestionnaire(1, "idrissi", "Nadia", "nadiaidrissi@nexabank.com", "admin123");
    }
}