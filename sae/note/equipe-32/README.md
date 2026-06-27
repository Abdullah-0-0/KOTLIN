# Projet flip7

## Application

### Projet par :
- Tom PICART
- Ylias LOO
- Ewen HUOU
- Gabin REDUREAU


### Lancement de l'application :
Pour pouvoir lancer l'application, il faut :
**Si vous êtes chez vous :**
1. Désactiver la configuration du proxy pour Gradle dans `gradle.properties`
2. Puis, au choix :
    - utiliser EduVPN pour que votre ordinateur soit considéré comme étant sur le réseau de l'IUT
    - ou remplacer les références à Nexus dans `build.gradle.kts` et `settings.gradle.kts` (voir les lignes à remplacer sur [ce lien](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/dev.objets.ressources#faire-fonctionner-les-tps-utilisant-gradle-chez-vous))

**INFO :** si vous êtes connecté à distance à un PC de l'IUT (Remmina), il faut laisser la configuration du proxy telle quelle.

- se placer dans le dossier `equipe32`
- exécuter la commande :
```
./gradlew run
```

```TIPS :Verifiez que comme en cours xhost + et DISPLAY=:0 sont bien entrer ;)```
```
Verifier aussi que :

// Dans le fichier build.gradle.kts
    application {
    mainClass.set("iut.info1.flip7.prototype.MainKt")
    }

```

### Déroulement d'une partie :

**Configuration de la partie**
1. Choisir le nombre de joueurs (de 2 à 4)
2. Choisir le score objectif (50, 100, 150 ou 200 points)
3. Saisir un nom pour chaque joueur
    - les noms insultants ou obscènes sont détectés et bloqués par l'application
    - deux joueurs ne peuvent pas porter le même nom, pour éviter toute confusion pendant la partie

**Pendant la partie**
- Les boutons **Piocher** / **Stop** se trouvent en bas à gauche de l'écran
- La carte piochée s'affiche au centre de l'écran
- La pioche, la défausse et les scores de chaque joueur sont affichés à droite
- À la fin de chaque manche, un tableau des scores récapitule la situation de tous les joueurs

**Fin de partie**
Lorsqu'un joueur atteint le score objectif, la partie se termine et un tableau de classement final affiche le rang de chaque joueur.