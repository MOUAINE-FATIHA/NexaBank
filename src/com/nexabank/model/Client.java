package com.nexabank.model;
import com.nexabank.exception.ErreurFichierException;
import com.nexabank.service.GestionnaireFichier;
import java.util.HashMap;

public class Client extends Personne {
    private int idClient;
    private HashMap<String, Compte> comptes;
    public Client(int idClient, String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.idClient = idClient;
        this.comptes = new HashMap<>();
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public HashMap<String, Compte> getComptes() {
        return comptes;
    }
    public void ajouterCompte(Compte compte) {
        this.comptes.put(String.valueOf(compte.getNumCompte()), compte);
    }
    public double consulterSolde(String numCompte) {
        Compte compte = comptes.get(numCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte introuvable pour ce client.");
        }
        return compte.getSolde();
    }

    public String consulterReleve(String numCompte) throws ErreurFichierException {
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nbComptes=" + comptes.size() +
                '}';
    }
}