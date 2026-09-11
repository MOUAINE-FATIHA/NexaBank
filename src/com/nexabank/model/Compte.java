package com.nexabank.model;
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