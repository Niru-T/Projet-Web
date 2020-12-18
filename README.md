# Projet Web

## Présentation du projet

Ce projet consiste à la création de 3 API permettant, respectivement, à la gestion d'utilisateur (inscription, login, édition du compte etc ...), à la création de sondages 
sur des lieux et date de rendez-vous et permettant de voté sur ces sondages. Enfin un site web en local utilisant les API que nous avons crées.

### Prérequis

* Intell IJ
* Navigateur web

### Technologie utilisée

* Spring boot pour le back-end
* MySQL pour la base de donnée
* HTML, CSS et Thymeleaf pour le front-end

### Consignes respectées

* API permettant la gestion d'utilisateurs
* API permettant la création de sondages
* API permettant de voter sur les sondages
* Appel aux 3 API crées via un site web
* Gitflow

### Informations complémentaires
Pour utiliser notre site web il faut tout d'abord exécuter le fichier ScriptSQL.sql se trouvant dans Projet-Web/src/main/resources/ dans votre base de donnée.
Il faudra éventuellement modifier dans le fichier application.properties se trouvant également dans Projet-Web/src/main/resources/ et y configurer les paramètres de votre base
donnée.
