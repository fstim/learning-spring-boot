Création d'une application SpringBoot
=====================================
Pour créer une application déployable à l'aide de SpringBoot, il suffit de créer une classe contenant une méthode main et de l'annoter avec '@SpringBootApplication'. Il faut évidemment avoir tirer les dépendances nécessaires à l'utilisation de SpringBoot.
Ensuite, il est possible d'utiliser des frameworks tels que SpringMVC pour que le conteneur puisse rendre des pages HTML templatées grâce à Thymeleaf.
On peut aussi créer des Controller qui permettront de faire le mapping avec les templates HTML en ajoutant les données nécessaires au model (HomeController).
On peut enfin ajouter des RestController qui permette d'exposer des services REST au travers du conteneur porté par SpringBoot (ChapterController).

Facilité de développement
=========================
LiveReload
__________
Utilisation de LiveReload uniquement sur Chrome.
Le plugin sur Firefox ne fonctionne pas correctement.
Spring dev tools
________________
L'utilisation de SpringDevTools accroît fortement la vitesse de développement.
Le redéploiement automatique de l'application associé à LiveReload permet d'avoir un rendu immédiat.
SpringRemoteApplication
_______________________
En cas de déploiement de l'application sur un environnement non local, il suffit d'ajouter 'spring.devtools.remote.secret=learning-spring-boot' au fichier application.properties.
Ensuite, il faut démarrer le projet à l'aide de la main class 'SpringRemoteApplication' (avec le projet dans le classpath).
Ceci permet de redéployer à chaud les modifications locales sur le serveur distant.
Feature très intéressante dans le cas d'une version déployée en recette ou homologation.

Administration sur SpringBoot
=============================
Endpoints d'administration
__________________________
En ajoutant les dépendances 'org.springframework.boot:spring-boot-actuator' et 'org.springframework.boot:spring-boot-starter-remote-shell' dans le build, on ajoute des endpoints sur le conteneur qui ajoutent des fonctions simples d'administration.
Par exemple :
*   '/health' permet d'avoir un état de santé de l'application.
    *   Il est possible de customiser cet endpoint en implémentant la classe HealthIndicator
*   '/autoconfig' permet d'avoir un rapport de ce que contient le classpaht du conteneur. Il est facile de trouver le conteneur utilisé (tomcat, undertow...).
    *   Il est aussi possible d'afficher le rapport de configuration au démarrage du conteneur en ajoutant '--debug' au variables de démarrage du programme.
*   '/beans' affiche l'ensemble des beans initialisés
*   '/env' affiche l'ensemble des informations d'environnement (JVM, OS, Port ...).
*   '/configprops' affiche l'ensemble des propriétés de configuration.
*   '/metrics' affiche des métriques simples sur le conteneur.
*   '/dump' crée un thread dump.
*   '/info' affiche des informations présentées par l'applications s'il y en a.
    *   A l'aide du plugin 'com.gorylenko.gradle-git-properties', on peut enrichir les informations et ainsi identifier aisément la version déployée. Les informations qui sont ajoutées sont des informations liées à git.
*   '/mappings' affiche les endpoints offerts par le conteneur.
*   '/trace' affiche l'ensemble des requêtes reçues par le conteneur.
*   '/actuator' permet d'aggréger tous les liens d'administration dans une seule page web. Cet endpoint nécessite la dépendance 'org.springframework.hateoas:spring-hateoas'.
    *   Il est possible de customiser cette page et d'y ajouter des indicateurs : http://fazlansabar.blogspot.fr/2015/02/hateoas-rest-apis-with-springboot.html

Changement du conteneur
_______________________
Par défaut, Spring Boot embarque un conteneur tomcat. Il est possible de modifier ce conteneur à l'aide des dépendances (maven ou gradle).
Il faut exclure le module 'spring-boot-starter-tomcat' des configurations et ajouter la dépendance 'org.springframework.boot:spring-boot-starter-undertow' pour utiliser un conteneur undertow par exemple.

TODO
====
Regarder Hatoas avec Actuator
Regarder SpringBoot Management Console

Questions pour Régis
====================
SpringBoot scan automatiquement tous les packages (pour les classes annotées '@Component') ?
Problème avec generateGitProperties quand on essaie d'enrichir '/info' :
    plugins {
        id "com.gorylenko.gradle-git-properties" version "1.4.17"
    }