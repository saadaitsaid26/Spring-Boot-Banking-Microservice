# Bank Account Service

Micro-service Spring Boot pour la gestion de comptes bancaires, exposé via **REST**, **Spring Data REST** et **GraphQL**.

Projet réalisé dans le cadre du module Systèmes Distribués et DevOps — ENSET Mohammedia.

---

## Présentation

Ce micro-service permet de créer, consulter, modifier et supprimer des comptes bancaires. Un même service métier est exposé de **trois façons différentes** (REST, Spring Data REST, GraphQL) afin de comparer les approches. L'application est construite en couches (entité → repository → service → présentation), et l'entité JPA n'est jamais exposée directement : les échanges se font via des **DTOs**.

## Technologies

Java 21 · Spring Boot · Spring Data JPA · H2 · Lombok · Spring Data REST · springdoc-openapi (Swagger) · Spring for GraphQL · Maven

## Structure du projet
- `entity/` → Entité JPA `BankAccount`
- `enums/` → `AccountType` (CURRENT_ACCOUNT, SAVING_ACCOUNT)
- `repository/` → `BankAccountRepository` (Spring Data JPA)
- `dto/` → `AccountRequest` (entrée), `AccountResponse` (sortie)
- `mapper/` → Conversion entité ↔ DTO
- `service/` → Couche métier (interface + implémentation)
- `projection/` → Projections Spring Data REST (`summary`, `fullAccount`)
- `web/` → Contrôleurs REST et GraphQL

## Les trois façons d'exposer l'API

**1. API REST classique** — un contrôleur `BankAccountController` avec les endpoints CRUD standards (`/accounts`). C'est l'approche la plus contrôlée : on choisit précisément les status codes, les DTOs et la logique.

**2. Spring Data REST** — le repository est exposé automatiquement en API REST, sans écrire de contrôleur. Les **projections** permettent de choisir les champs renvoyés (et d'afficher l'`id`, masqué par défaut) : `summary` et `fullAccount`. Idéal pour un CRUD simple.

**3. GraphQL** — un point d'entrée unique (`/graphql`) où le client demande exactement les champs qu'il veut. Le schéma définit des **queries** (lecture) et des **mutations** (écriture).

## Démarrage

```bash
mvn spring-boot:run
```

L'application démarre sur `http://localhost:8080` (10 comptes de démonstration sont générés au démarrage).

## Endpoints

| Ressource | URL |
|-----------|-----|
| API REST | `GET/POST/PUT/DELETE /accounts` |
| Swagger UI | `/swagger-ui.html` |
| Spring Data REST | `/accounts?projection=fullAccount` |
| GraphQL (GraphiQL) | `/graphiql` |
| Console H2 | `/h2-console` — JDBC : `jdbc:h2:mem:bank-account-db` |

**Exemple REST :**
```http
POST /accounts
Content-Type: application/json

{ "balance": 7500, "currency": "MAD", "type": "CURRENT_ACCOUNT" }
```

**Exemple GraphQL :**
```graphql
# Query
query { accountsList { id balance currency type } }

# Mutation
mutation {
  saveAccount(account: { balance: 5000, currency: "MAD", type: SAVING_ACCOUNT }) {
    id balance type
  }
}
```

## Travail réalisé

1. Projet Spring Boot (Web, Spring Data JPA, H2, Lombok)
2. Entité JPA `BankAccount` et énumération `AccountType`
3. Interface `BankAccountRepository` (Spring Data)
4. Génération de données de test au démarrage (`CommandLineRunner`)
5. Web service RESTful de gestion des comptes (CRUD complet)
6. Test de l'API REST avec Postman
7. Documentation Swagger des API REST (OpenAPI)
8. Exposition du repository via Spring Data REST avec projections
9. DTOs et Mapper (découplage entité / API)
10. Couche Service (logique métier)
11. Web service GraphQL (queries + mutations)

## Améliorations possibles

- Validation des entrées (contraintes Bean Validation sur `AccountRequest`)
- Gestion centralisée des exceptions (Réponses d'erreur cohérentes)
- Buckets de tests d'intégration pour chaque couche API (REST, Data REST, GraphQL)
- Passage de H2 à PostgreSQL avec Docker (profil `prod`)

---

*Saad Ait Saidou-Ali — GLSID, ENSET Mohammedia*
