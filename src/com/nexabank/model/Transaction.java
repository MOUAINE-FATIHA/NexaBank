package com.nexabank.model;
import java.time.LocalDate;
public class Transaction {

    private int idTransaction;
    private TypeTransaction type;
    private double montant;
    private LocalDate date;
    private Integer compteSource;
    private Integer compteDestination;

    public Transaction(int idTransaction, TypeTransaction type, double montant,
                       LocalDate date, Integer compteSource, Integer compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }
    public int getIdTransaction() {
        return idTransaction;
    }
    public TypeTransaction getType() {
        return type;
    }
    public double getMontant() {
        return montant;
    }
    public LocalDate getDate() {
        return date;
    }
    public Integer getCompteSource() {
        return compteSource;
    }
    public Integer getCompteDestination() {
        return compteDestination;
    }


    public String versLigneReleve() {
        String source = (compteSource == null) ? "null" : String.valueOf(compteSource);
        String destination = (compteDestination == null) ? "null" : String.valueOf(compteDestination);
        return date + " " + type + " " + montant + "€ " + source + " " + destination;
    }


    @Override
    public String toString() {
        return versLigneReleve();
    }
}