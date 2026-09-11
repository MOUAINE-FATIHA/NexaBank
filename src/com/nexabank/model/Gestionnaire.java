package com.nexabank.model;
import com.nexabank.exception.ErreurFichierException;
public class Gestionnaire extends Personne {

    private int idGestionnaire;
    public Gestionnaire(int idGestionnaire, String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.idGestionnaire = idGestionnaire;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }
    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }
    public Compte creerCompte(Client client, String typeCompte, int numCompte) {
        Compte nouveauCompte;
        if (typeCompte.equalsIgnoreCase("Courant")) {
            nouveauCompte = new CompteCourant(numCompte, 0.0);
        } else if (typeCompte.equalsIgnoreCase("Epargne")) {
            nouveauCompte = new CompteEpargne(numCompte, 0.0);
        } else {
            throw new IllegalArgumentException("Type de compte inconnu : " + typeCompte);
        }
        client.ajouterCompte(nouveauCompte);
        return nouveauCompte;
    }
    public void cloturerCompte(Client client, String numCompte) {
        if (!client.getComptes().containsKey(numCompte)) {
            throw new IllegalArgumentException("Compte introuvable.");
        }
        client.getComptes().remove(numCompte);
    }
    public void mettreAJourClient(Client client, String nom, String prenom, String email) {
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
    }
    public String consulterReleve(Client client, String numCompte) throws ErreurFichierException {
        return client.consulterReleve(numCompte);
    }



    @Override
    public String toString() {
        return "Gestionnaire{" +
                "idGestionnaire=" + idGestionnaire +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}