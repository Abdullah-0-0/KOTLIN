# Rapport de test SAÉ 2.01 [Flip 7]

**Équipe :** Souhayb, Caleb, Nathan, Nael, Tristan

Ce rapport documente l'implementation de nos cas de tests pour les trois fichiers qu'on a rendu dans le projet.

---

## 1. Fichier de test : `CartesTest.kt`

Ce fichier sert a verifier que toutes les classes de cartes se comportent correctement, appartiennent aux bons types et rejettent les valeurs hors bornes.

### `Conception des tests (Analyse partitionnelle)`

| Données de test | Plages de valeurs | R1 | R2 | R3 | R4 | R5 | R6 |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| `CarteNum(X)` (Valide) | `0 <= X <= 12` | True | X | X | X | X | X |
| `CarteNum(X)` (Invalide bas) | `X < 0` (ex: `-1`, `Int.MIN_VALUE`) | X | True | X | X | X | X |
| `CarteNum(X)` (Invalide haut) | `X > 12` (ex: `13`, `Int.MAX_VALUE`) | X | X | True | X | X | X |
| `CarteStop` / `2ndeChance` / `3aLaSuite` | `valeur = 0` | Faux | X | X | True | Faux | Faux |
| `CarteBonusPlus(X)` | `2 <= X <= 10` | Faux | X | X | Faux | True | Faux |
| `CarteBonusMultiplie` | `valeur = 2` | Faux | X | X | Faux | X | True |
| **Oracle attendu** | — | **Valeur X** | **Exception** | **Exception** | **Valeur 0** | **Valeur X** | **Valeur 2** |

### `Analyse de la testabilite`
**Observabilité :** C'est super simple a tester, toutes les variables comme `.valeur` ou les fonctions de verifications sont accessibles directement en lecture seule.

**Contrôlabilité :** Les cartes n'ont aucune dependance. Il suffit de faire un `CarteNum(5)` ou un `CarteStop()` dans le test pour pouvoir bosser dessus direct.

### `Implémentation et Oracles`
Les tests de ce fichier appliquent directement les regles definies par notre analyse :

* **Valeurs nominales et limites :** On teste individuellement chaque valeur de `CarteNum` de 0 à 12 (`testValeurCarteNum0()`, etc...) pour verifier que `.valeur` renvoie l'entier exact, ainsi que la valeur par defaut à 0 pour les cartes speciales (`CarteStop`, `Carte2ndeChance`).

* **Hierarchie des types :** Validation via l'operateur `is` pour s'assurer qu'une carte speciale herite bien de `CarteSpeciale` et que les cartes bonus implementent `CarteBonus`.

* **Algorithme de comparaison :** Utilisation de l'oracle `Assertions.assertTrue(CarteNum(0) < CarteNum(1))` et de verifications sur `compareTo` pour valider l'ordre naturel des cartes.

---

## 2. Fichier de test : `OutilsCarteTest.kt`

Ce fichier sert a verifier les calculs de points (les scores), les bonus du Flip7 et la verification des paquets de cartes.

### `Conception des tests (Analyse partitionnelle)`

| Données de test | Critères de structure | R1 | R2 | R3 | R4 | R5 | R6 |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| Main vide ou basique | Taille = 0 ou valeurs valides | True | X | X | X | X | X |
| Combinaison avec Bonus | Présence de `CarteBonusMultiplie`/`Plus` | X | True | X | X | X | X |
| Main à 7 chiffres distincts | 7 instances uniques de `CarteNum` | X | X | True | X | X | X |
| Dépassement de doublons | Taille > 7 numéros ou >= 2 fois la même | X | X | X | True | X | X |
| Excès de cartes spéciales | > 1 Stop, ou >= 4 SecondeChance/3aLaSuite | X | X | X | X | True | X |
| Paquet de pioche incorrect | Taille != 94 ou mauvaise répartition | X | X | X | X | X | True |
| **Oracle attendu** | — | **Score direct** | **Calcul combiné** | **Score + 15** | **Exception** | **Exception** | **Exception** |

### `Analyse de la testabilite`
**Observabilité :** C'est tres facile a verifier. Les fonctions donnent direct un resultat simple : un nombre pour le score, ou un vrai/faux pour le Flip7.

**Contrôlabilité :** On cree l'objet outils une seule fois au debut (`private val outils = OutilsCarte()`). Ensuite, on controle tout en lui passant juste la liste de cartes qu'on veut tester.

### `Implémentation et Oracles`
Nos tests verifient que les regles de calculs et de triche marchent bien :

* **Calcul des scores :** On verifie l'ordre des calculs (par exemple faire le multiplicateur puis le bonus plus), que la carte zero ne donne aucun point, et que les cartes speciales (comme le Stop) valent bien 0 point dans le score.

* **Reconnaître un Flip 7 :** On utilise `assertTrue(outils.estFlip7(...))` pour verifier qu'une main avec 7 chiffres differents donne bien le bonus, meme s'il y a d'autres cartes speciales au milieu.

* **Bloquer les tricheries :** On utilise `assertThrows` pour verifier que le code declenche bien une erreur si un joueur a une main impossible (comme deux cartes x2 ou trop de cartes Seconde Chance) ou si le paquet de pioche de depart est faux.

---

## 3. Fichier de test : `Flip7MoteurTest.kt`

Ce fichier teste le deroulement d'une partie avec la classe principale `Flip7` (les joueurs qui piochent, les changements de tours et la fin du jeu).

### `Conception des tests (Analyse partitionnelle)`

| Données de test | États Initiaux | R1 | R2 | R3 | R4 | R5 | R6 |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| Carte standard disponible | `CarteNum(X)` sans doublon local | True | X | X | X | X | X |
| Doublon immédiat provoqué | `CarteNum(X)` identique à la main du joueur | X | True | X | X | X | X |
| Carte Spéciale d'attaque | `CarteStop` | X | X | True | X | X | X |
| Carte Spéciale d'effet | `Carte3aLaSuite` ou `Carte2ndeChance` | X | X | X | True | X | X |
| Carte de type Jeton Bonus | `CarteBonusPlus(X)` ou `CarteBonusMultiplie` | X | X | X | X | True | X |
| Structure de pioche vide | `emptyList()` | X | X | X | X | X | True |
| **Oracle attendu** | — | **Joueur +1** | **Joueur +1** | **État CIBLE** | **Joueur +1** | **Joueur +1** | **Exception** |

### `Analyse de la testabilite`
**Observabilité :** En mettant le mode `debug = true`, on peut regarder directement la variable `jeu.joueurCourant` pour verifier a quel joueur c'est le tour apres chaque pioche.

**Contrôlabilité :** On a cree de faux joueurs simplifie avec `mockJoueur(nom)` pour ne pas avoir besoin de lancer une vraie interface graphique. On utilise aussi notre methode `creerJeu()` pour donner une liste de cartes fixe : ca enleve le hasard et on a toujours le meme resultat.

### `Implémentation et Oracles`
On verifie que les joueurs passent bien leur tour comme il faut :

* **Changement de tour normal :** On verifie qu'apres avoir pioche une carte classique (un chiffre, un zero, ou un 12), l'index du joueur augmente bien de 1 pour passer au suivant.

* **Faire le tour de la table :** On enchaîne plusieurs pioches de suite pour verifier que le jeu donne bien la main au joueur 1, puis au joueur 2, et revient bien au joueur 0 quand on a 3 joueurs.

* **Bloquer le jeu sur les cartes d'attaques :** Quand on pioche un Stop ou un 3 a la suite, on verifie avec `assertNotNull(jeu.etatPartie)` que le jeu change bien d'etat et passe en mode "attente" pour choisir une cible, ce qui bloque le flux normal.

---
### 4. Utilisation de l'intelligence artificielle
Pour Flip7MoteurTest.kt, on a utilisé l'IA uniquement pour nous sortir le code répétitif des 40 cas de tests (le 0, le 12, les bonus et les cartes spéciales) afin de gagner du temps.

Mais le code fourni était trop imprécis et l'IA a confondu les règles : elle pensait que le joueur gardait la main tant qu'il n'avait pas de doublon, alors que dans notre projet, piocher passe automatiquement le tour. Résultat, plus de la moitié des tests bloquaient sur Gradle. Elle générait aussi des pioches vides qui faisaient crasher le constructeur.

On a donc dû tout reprendre nous-mêmes. On a corrigé la méthode creerJeu pour éviter les erreurs de pioche, ajusté tous les assertEquals pour coller au vrai comportement du fichier .jar, et supprimé les tests inutiles qui ne respectaient pas nos propres tableaux de spécifications.