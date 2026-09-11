# NexaBank — Application de gestion bancaire digitale

## Présentation du projet

NexaBank est une application console développée en Java, simulant la gestion des comptes bancaires
de clients. Ce projet est un prototype réalisé dans le cadre du brief "Développement d'une application
de gestion bancaire digitale"

L'application permet :
- Aux **clients** de consulter leurs comptes, effectuer des dépôts/retraits/virements, et consulter
  leur relevé bancaire.
- Aux **gestionnaires** de créer, modifier et clôturer les comptes de leurs clients.
- La génération automatique d'un relevé bancaire (fichier `.txt`) pour chaque compte.

## Choix techniques

- **Langage** : Java 17
- **Architecture** : organisation en packages (`model`, `service`, `exception`, `main`)
- **POO** : héritage (`Personne` → `Client`/`Gestionnaire`, `Compte` → `CompteCourant`/`CompteEpargne`),
  encapsulation stricte (attributs privés/protégés avec accesseurs), polymorphisme (création de comptes
  via la classe abstraite `Compte`)
- **Collections** : `HashMap<String, Compte>` pour les comptes d'un client, `HashSet<Transaction>`
  pour l'historique d'un compte
- **Gestion des exceptions** : 4 exceptions personnalisées (`MontantInvalideException`,
  `SoldeInsuffisantException`, `CompteInexistantException`, `ErreurFichierException`), toutes héritant
  de `Exception` (checked exceptions), pour forcer leur gestion explicite dans le code
- **Persistance** : un fichier `.txt` unique par compte, généré et mis à jour automatiquement après
  chaque opération, via le service `GestionnaireFichier`

## Structure du projet

```
src/com/nexabank/
├── model/       → Entités métier (Personne, Client, Gestionnaire, Compte, CompteCourant,
│                  CompteEpargne, Transaction, TypeTransaction)
├── service/     → Logique technique transverse (GestionnaireFichier : lecture/écriture des .txt)
├── exception/   → Exceptions personnalisées
└── main/        → Point d'entrée (Main), menus console, données de test (InitDonnees)

uml/             → Diagrammes UML (.puml) : classes, cas d'utilisation, séquence
```

## Lancer l'application

1. Ouvrir le projet dans IntelliJ IDEA (ou tout IDE Java).
2. Vérifier qu'un JDK 17+ est configuré.
3. Exécuter la classe `com.nexabank.main.Main`.

## Comptes de test disponibles

| Rôle                                        | Email                     | Mot de passe |
|---------------------------------------------|---------------------------|---|
| Client (Sara Majbar, compte courant n°1001) | saramajbar@gmail.com      | 1234 |
| Client (Taha Nassik, compte épargne n°1002) | tahamajbar@gmail.com      | 5678 |
| Gestionnaire (Nadia Idrissi)                | nadiaidrissi@nexabank.com | admin123 |

## Diagrammes UML

Les 3 diagrammes UML (classes, cas d'utilisation, séquence) sont disponibles dans le dossier `uml-nexabank/`.
