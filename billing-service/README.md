# Activité Pratique N°2 - Mise en oeuvre d'une architecture micro-services

## Description
Ce projet est une démonstration de la mise en oeuvre d'une architecture micro-services en utilisant Spring Boot, Spring Cloud, et Feign. Il comprend un service de facturation (`billing-service`) qui interagit avec des services externes pour récupérer des informations sur les clients et les produits.

## Structure du projet
- `billing-service`: Service de facturation principal.
    - `entities`: Contient les entités JPA.
    - `feign`: Contient les clients Feign pour interagir avec les services externes.
    - `repositories`: Contient les interfaces de repository Spring Data JPA.
    - `web`: Contient les contrôleurs REST.

## Prérequis
- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- Un IDE comme IntelliJ IDEA

## Installation
1. Clonez le repository:
    ```sh
    git clone https://github.com/Lee4real/billing-service.git
    cd billing-service
    ```

2. Compilez le projet avec Maven:
    ```sh
    mvn clean install
    ```

## Configuration
Le fichier de configuration principal est `src/main/resources/application.properties`. Voici les paramètres principaux:
```ini
spring.application.name=billing-service
server.port=8083
spring.datasource.url=jdbc:h2:mem:billing-db
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
spring.cloud.discovery.enabled=true
```
## Exécution
Pour exécuter le projet, utilisez la commande suivante:
```sh
mvn spring-boot:run
```

## Endpoints
- `GET /fullBill/{id}`: Récupère une facture complète avec les détails du client et des produits.

### Exemple d'utilisation
- Démarrez l'application
- Accédez à http://localhost:8083/fullBill/1 pour récupérer les détails de la facture avec l'ID 1.

### Dépendances
- Spring Boot
- Spring Cloud
- Spring Data JPA
- H2 Database
- Feign
- Lombok
- Spring Web

### Auteur
- [Lee4real](https://github.com/Lee4real)

### License
Ce projet est sous licence MIT.


