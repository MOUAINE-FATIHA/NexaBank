package com.nexabank.model;
public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(int numCompte, double solde) {
        super(numCompte, solde);
        this.tauxInteret = 0.02;
    }
    public double getTauxInteret() {
        return tauxInteret;
    }
    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
    public double calculerInteret() {
        return this.solde * this.tauxInteret;
    }
    @Override
    public String toString() {
        return "CompteEpargne{" +
                "numCompte=" + numCompte +
                ", solde=" + solde +
                ", tauxInteret=" + tauxInteret +
                '}';
    }
}