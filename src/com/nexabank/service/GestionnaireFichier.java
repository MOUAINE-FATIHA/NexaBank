package com.nexabank.service;
import com.nexabank.exception.ErreurFichierException;
import com.nexabank.model.Transaction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class GestionnaireFichier {

    private static final String DOSSIER_RELEVES = "releves";
    public static void enregistrerTransaction(int numCompte, Transaction transaction) throws ErreurFichierException {
        String cheminFichier = DOSSIER_RELEVES + File.separator + "compte_" + numCompte + ".txt";
        try {
            Files.createDirectories(Paths.get(DOSSIER_RELEVES));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
                writer.write(transaction.versLigneReleve());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ErreurFichierException("Impossible d'écrire dans le fichier du compte " + numCompte + " : " + e.getMessage());
        }
    }
    public static String lireReleve(int numCompte) throws ErreurFichierException {
        String cheminFichier = DOSSIER_RELEVES + File.separator + "compte_" + numCompte + ".txt";
        StringBuilder contenu = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ErreurFichierException("Impossible de lire le relevé du compte " + numCompte + " : " + e.getMessage());
        }
        return contenu.toString();
    }
}