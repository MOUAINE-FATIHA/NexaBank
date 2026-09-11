package com.nexabank.model;
public abstract class Personne {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String password;

    public Personne(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean seConnecter(String emailSaisi, String passwordSaisi) {
        return this.email.equals(emailSaisi) && this.password.equals(passwordSaisi);  // equals dans java -> ==
    }

    @Override  // signale qu'on redéfinit une méthode -toString()- héritée 'object' (c une classe parent de toutes les classes)
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}