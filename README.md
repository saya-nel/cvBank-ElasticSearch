# Projet 3 DAAR.

Pablito BELLO & Pierre Gomez

## Sujet

Le sujet du projet ce trouve dans le fichier sujet.pdf

## Arborescence 

- Le sous répertoire *client* contient le code du client web, réalisé en React. 
- Le sous répertoire *server* contient le code du serveur web, réalisé en java avec Spring boot. 

## Réalisation 

Le sujet de base du projet à été réalisé, notre projet implémente les fonctionnalitées suivantes : 

- Depuis le navigateur web, ajout de cv au format pdf. 
- Recherche de cv par tag via le navigateur web (ex : java), une fois le formulaire envoyé, le site affiche tout les cv trouvés contenant le tag. Les cv ainsi affichés sont téléchargeable en cliquant sur leur nom. 
- Le serveur travail en collaboration avec une instance elasticsearch déployé sur votre machine.

## Installation 

### versions

Voici les versions utilisées pour le developpement du projet : 
- elasticsearch : 7.10
- npm : 6.14.4
- java : openjdk 11.0.9

### guide

Pour utiliser le projet, il faudra télécharger l'archive elasticsearch depuis le site dedié. 

On lance ensuite une instance elasticsearch : 

```
./elasticsearch-7.10.0/bin/elasticsearch
```

On créer un index nommé "cv" dans elasticsearch :

```
curl -X PUT "localhost:9200/cv"  
```

elasticsearch est maintenant prêt à l'emploie.

Le serveur web peut être lancer soit via l'IDE Intellij IDEA en important le dossier *server* en tans que projet, soit directement avec le .jar fournit. Executer la commande suivante depuis le répertoire racine :

```
java -jar server/target/cvBankElasticsearch-0.0.1-SNAPSHOT.jar
``` 

(la version de java doit être >= 11)

Enfin pour lancer le client, on se place dans le répertoire *client* : 

```
cd client
```

et on execute les deux  commandes : 

```
npm install
npm start
```

si tout c'est bien passé, vous pouvez aller à l'adresse suivante dans un navigateur web : http://localhost:3000/

### Utilisation

Une fois le site ouvert, celui-ci affiche deux formulaire avec chacun un bouton de soumission. 

Le premier formulaire permet l'enregistrement d'un cv au format pdf dans elasticsearch. Une fois le fichier choisit (un cv est fournit dans le répertoire server/lib) on appuie sur "enregistrer". Le serveur enregistre le cv mais aucune confirmation visuelle n'est donnée. 

Pour tester une recherche, le deuxième formulaire permet d'entrer un mot clé à chercher dans tout les cv de la base, tout les cv contenant ce mot clé apparaitrons en tans que liens (fihier .pdf du cv téléchargable) une fois le bouton "chercher" appuyer. 
La caste n'est importante dans les mots clés des cv, ni dans le mot clé de la recherche. 
On pourra par exemple uploader le cv donner en exemple, et faire une recherche avec le mot clé "java", le nom du cv apparait alors, et on peut s'assurer que c'est le bon cv en le téléchargeant et en l'ouvrant. 