package com.nexabank.model;
public class CompteCourant extends Compte {

    private double decouvertAutorise;
    public CompteCourant(int numCompte, double solde) {
        super(numCompte, solde);
        this.decouvertAutorise = 0.0;
    }
    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }
    public void autoriserDecouvert(double montant) {
        this.decouvertAutorise = montant;
    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "numCompte=" + numCompte +
                ", solde=" + solde +
                ", decouvertAutorise=" + decouvertAutorise +
                '}';
    }
}