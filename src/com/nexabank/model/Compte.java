package com.nexabank.model;
import com.nexabank.exception.ErreurFichierException;
import com.nexabank.exception.MontantInvalideException;
import com.nexabank.exception.SoldeInsuffisantException;
import com.nexabank.service.GestionnaireFichier;
import java.time.LocalDate;
import java.util.HashSet;

public abstract class Compte {
    protected int numCompte;
    protected double solde;
    protected HashSet<Transaction> historique;
    public Compte(int numCompte, double solde) {
        this.numCompte = numCompte;
        this.solde = solde;
        this.historique = new HashSet<>();
    }
    public int getNumCompte() {
        return numCompte;
    }
    public double getSolde() {
        return solde;
    }
    public HashSet<Transaction> getHistorique() {
        return historique;
    }
    public void deposer(double montant) throws MontantInvalideException, ErreurFichierException {
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant du dépôt doit être positif.");
        }
        this.solde += montant;
        Transaction t = new Transaction(
                genererIdTransaction(),
                TypeTransaction.DEPOT,
                montant,
                LocalDate.now(),
                null,
                this.numCompte
        );
        this.historique.add(t);
        GestionnaireFichier.enregistrerTransaction(this.numCompte, t);
    }
    public void retirer(double montant) throws MontantInvalideException, SoldeInsuffisantException, ErreurFichierException {
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant du retrait doit être positif.");
        }
        if (montant > this.solde) {
            throw new SoldeInsuffisantException("Solde insuffisant pour ce retrait.");
        }
        this.solde -= montant;
        Transaction t = new Transaction(
                genererIdTransaction(),
                TypeTransaction.RETRAIT,
                montant,
                LocalDate.now(),
                this.numCompte,
                null
        );
        this.historique.add(t);
        GestionnaireFichier.enregistrerTransaction(this.numCompte, t);
    }

    public void virement(Compte compteDestination, double montant)
            throws MontantInvalideException, SoldeInsuffisantException, ErreurFichierException {

        if (montant <= 0) {
            throw new MontantInvalideException("Le montant du virement doit être positif.");
        }
        if (montant > this.solde) {
            throw new SoldeInsuffisantException("Solde insuffisant pour ce virement.");
        }
        this.solde -= montant;
        compteDestination.solde += montant;
        Transaction t = new Transaction(
                genererIdTransaction(),
                TypeTransaction.VIREMENT,
                montant,
                LocalDate.now(),
                this.numCompte,
                compteDestination.getNumCompte()
        );
        this.historique.add(t);
        compteDestination.getHistorique().add(t);
        GestionnaireFichier.enregistrerTransaction(this.numCompte, t);
        GestionnaireFichier.enregistrerTransaction(compteDestination.getNumCompte(), t);
    }
    private int genererIdTransaction() {
        return (int) (Math.random() * 1_000_000);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "numCompte=" + numCompte +
                ", solde=" + solde +
                '}';
    }
}