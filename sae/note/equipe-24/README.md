# Flip 7
équipe 24 : Abdourahimi Souhayb, Daiki Nael, Bakouma Caleb, Cardin Tristan 
Guide pour lancer notre projet Flip7 

## Prérequis

- JDK 11 ou supérieur
- Gradle
- Connexion au réseau de l'IUT

## Compiler le projet

```bash
./gradlew build
```

## Exécuter le jeu

### Depuis IntelliJ

1. Ouvrir le projet dans IntelliJ
2. Attendre la fin de la synchronisation Gradle
3. Clique droit sur le fichier `src/main/kotlin/flip7/Main.kt`
4. Puis cliquer sur le bouton vert 'Run'

### Depuis le Terminal

- Dans un terminal (pas celui d'IntelliJ) exécuter : `xhost +`
- Dans IntelliJ : cliquer sur les trois petits points `...` à côté du bouton debug en haut à droite → Edit... → ajouter `DISPLAY=:0` dans Environment variables

### Depuis le terminal

```bash
./gradlew run
```

## Lancer les tests

```bash
./gradlew test
```



Équipe 24 — SAÉ 2.01, 2025-2026
