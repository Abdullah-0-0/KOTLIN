# Rapport de Test - Bibliothèque Flip7

**UE :** R2.03 - Qualité de développement  
**Livrable :** Conception et implémentation de tests unitaires pour la librairie `iut.info1.flip7-1.x.jar`

---

## Introduction

Dans le cadre de ce projet, nous avons méthodiquement testé la bibliothèque Java/Kotlin fournissant le moteur de jeu du **Flip7**. Notre objectif n'était pas seulement d'aligner des lignes de code de test pour gonfler les statistiques, mais de cartographier intelligemment les comportements du jeu pour débusquer les cas limites (*edge cases*) et comprendre les rouages internes de la machine à états.

Ce rapport synthétise notre démarche, de la modélisation théorique aux ajustements pragmatiques que nous avons dû opérer lors de l'implémentation de nos assertions JUnit.

---

## 1. Conception des tests par approche fonctionnelle

Pour éviter de tester "au hasard" et garantir une couverture pertinente, nous avons découpé les spécifications du Flip7 en fonctions et en états logiques fondamentaux.

### A. Table de décision : Action de Piocher (`joueurCourantPiocheUneCarte`)
L'action de piocher dépend fortement de l'état actuel de la main du joueur et du contenu de la pioche. Voici la table de décision qui a guidé l'écriture de nos cas de tests :

| Conditions / Critères | C1 : Pioche vide | C2 : Carte unique | C3 : Carte déjà présente (Doublon) | C4 : 7e carte unique |
| :--- | :---: | :---: | :---: | :---: |
| **Actions / Résultats** | | | | |
| Levée d'exception | **X** | | | |
| Score augmenté | | **X** | | **X** |
| Déclenchement du *Bust* (0 pt) | | | **X** | |
| Passage à `MANCHE_TERMINEE` | | | **X** | **X** |

### B. Scénarios séquentiels (Multi-manches)
Le Flip7 étant un jeu à score cumulé, un simple test unitaire "statique" ne suffisait pas à valider la robustesse de l'enchaînement des phases. Nous avons conçu des scénarios chaînés pour valider la machine à états (`EtatPartie`) :

1. **Scénario "Fin de partie nominale" :** Manche 1 sans vainqueur -> Transition vers `NOUVELLE_MANCHE` -> Initialisation de la Manche 2 -> Un joueur dépasse le seuil requis -> Transition vers `PARTIE_TERMINEE`.
2. **Scénario "Sécurisation des états invalides" :** Forcer une action normalement interdite (par exemple, appeler `scoreManche()` alors que la partie est déjà déclarée terminée) afin de vérifier que la bibliothèque lève bien les barrières de sécurité attendues.

---

## 2. Analyse de la testabilité

Tester un jeu de cartes basé par définition sur le hasard (le mélange du paquet) pose un défi majeur de **testabilité**. Nous avons analysé la bibliothèque à l'aide des heuristiques classiques.

### Contrôlabilité
* **Le point fort :** La présence de la méthode `partieDebug()` offre une excellente contrôlabilité. Elle nous permet d'injecter un tableau de cartes prédéfini (`cartesEnTete`) pour simuler exactement les tirages voulus et rendre nos tests 100% déterministes.
* **Le piège découvert (Heuristique de dépendance) :** Au cours de nos sessions de code, nous avons réalisé que le moteur de jeu souffre d'une dépendance invisible vis-à-vis de l'état futur de la pioche. Même si un test ne prévoit de piocher que 4 cartes au total, l'appel à `scoreManche()` pousse le moteur à inspecter l'avenir de la pioche pour préparer la suite. Si le tableau injecté est vide ou trop court, le moteur corrompt ses variables de score ou crashe prématurément.
* **Notre solution :** L'injection systématique de **cartes de sécurité (padding)** à la fin de nos tableaux de débug pour stabiliser le comportement interne du framework.

### Observabilité
* L'état de la partie (`partie.etatPartie`) et le tableau des scores retourné par `scoreManche()` offrent une bonne observabilité de surface.
* Cependant, nous avons dû rester vigilants sur les effets de bord : le calcul du score en cas de *Bust* (doublon) efface instantanément le gain de la manche en cours. Lors de nos premiers essais, un test échouait en renvoyant un état `NOUVELLE_MANCHE` au lieu de `PARTIE_TERMINEE`. Après analyse, ce n'était pas un bug du moteur, mais une mauvaise configuration de nos cartes injectées qui provoquaient un doublon involontaire, annulant le score du joueur et l'empêchant d'atteindre la limite de fin de partie.

---

## 3. Implémentation des tests unitaires et Oracles

Nos tests ont été écrits en Kotlin avec JUnit 5. Nous avons veillé à expliciter clairement l'**intention** et l'**oracle** (le résultat attendu) pour chaque fonction.

### Exemple 1 : Validation du cumul des scores (Pas de remplacement)
* **Intention :** Vérifier que les scores acquis lors de la manche 1 s'ajoutent bien à ceux de la manche 2 au lieu d'être écrasés.
* **Oracle :** `assertEquals(8, scoresManche2[0])` (soit 3 points de la M1 + 5 points de la M2).

```kotlin
@Test
fun `le score de la manche 1 est cumule avec celui de la manche 2, pas remplace`() {
    val partie = partieDebug(
        nbJoueurs = 2,
        scoreFinPartie = 200, // Limite haute pour ne pas finir le test trop tôt
        cartesEnTete = arrayOf(
            CarteNum(3), CarteNum(4), // M1 : J0 prend 3, J1 prend 4
            CarteNum(5), CarteNum(6), // M2 : J0 prend 5, J1 prend 6
            CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4) // Padding de sécurité
        )
    )

    // --- MANCHE 1 ---
    partie.joueurCourantPiocheUneCarte() ; partie.joueurCourantDitStop()
    partie.joueurCourantPiocheUneCarte() ; partie.joueurCourantDitStop()
    partie.scoreManche()

    partie.nouvelleManche()

    // --- MANCHE 2 ---
    partie.joueurCourantPiocheUneCarte() ; partie.joueurCourantDitStop()
    partie.joueurCourantPiocheUneCarte() ; partie.joueurCourantDitStop()
    val scoresManche2 = partie.scoreManche()

    // Oracle : Vérification du cumul (3 + 5 = 8) et non du remplacement (5)
    assertEquals(8, scoresManche2[0], "Le score du joueur 0 doit cumuler les 2 manches")
    assertEquals(10, scoresManche2[1], "Le score du joueur 1 doit cumuler les 2 manches")
}
```
Note : Malheuresement nous avons du utiliser en plus grande quantité l'ia pour remplacer nos camarades Ewen Huou, et Gabin Redureau qui ne nous pas beaucoup aider dans les soutenance et dans le travail en général.
Voici le lien pour pouvoir voir notre analyse des tests que nous voulions réalisé : https://docs.google.com/spreadsheets/d/1RLSs29JbhvmZfEvUO0cAqJS5XtrFhXP2GAFzIHrgNO4/edit?usp=sharing
Vous pourrez voir dans ce lien qui a travailler sur quel fonction