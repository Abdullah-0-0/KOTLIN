# Rapport de spécification — SAÉ 2.01 — Flip 7

**Équipe :** Souhayb, Caleb, Nathan, Nael, Tristan

Ce rapport présente nos choix pour les deux diagrammes de classes (`analyse.puml` et
`detaille.puml`).

## Choix d'analyse

On a Trois familles de cartes.Une classe abstraite `Carte` est spécialisée en `CarteNumero`,
`CarteBonus` et `CarteSpeciale`, ce qui correspond aux trois familles du jeu (Numéro, Bonus,
Spéciale). `CarteBonus` est elle-même découpée en `BonusAdditif` (de +2 à +10) et `BonusFoisDeux` (x2),
car ces deux cartes n'ont pas le même effet sur le score. `CarteSpeciale` est découpée en `Stop`,
`SecondeChance` et `TroisALaSuite`.

On a décidé de mettre Une classe `Tas`. Plutôt que de répéter « contient des cartes » sur la pioche, la défausse et
les cartes du joueur, on a créé une classe abstraite `Tas` dont héritent `Pioche`, `Defausse`
et `CartesJoueur`. Le `Joueur` possède une `CartesJoueur` (mais un joueur n'est pas un tas). La
cardinalité `1` côté `Tas` exprime qu'une carte est dans **exactement un tas** à la fois grâce à l'attribut `- emplacement : Tas`.Nous avons mis `CartesJoueur` (les cartes posées devant le
joueur) pour pouvoir la faire hériter de `Tas` : on ne pouvait pas faire hériter `Joueur` de `Tas`,
car un joueur n'est pas un tas de cartes. Le `Joueur` **possède** donc une `CartesJoueur`.


**État du joueur sur `Joueur`.** L'état soit actif soit stoppé soit sauté et les cartes posées sont placés
directement sur `Joueur`, car ils sont réinitialisés à chaque manche donc cela évite une classe
intermédiaire. Les trois valeurs viennent des règles, et il était
important de séparer `STOPPE` et `SAUTE` car dans les deux cas le joueur sort de la manche, mais un
joueur stoppé garde ses points alors qu'un joueur qui a sauté marque 0. Le calcul du score
va dependre de cela.

**État du joueur.** La classe `Joueur` (nom, score total, état) représente un participant. Son
`etat` (actif / stoppé / sauté) et ses cartes sont placés directement sur `Joueur` car ils sont
réinitialisés à chaque manche ; cela évite une classe intermédiaire.


**Le donneur et le tour.** L'association `Manche — Joueur : donneur` représente le joueur qui distribue pour
une manche (rôle qui change à chaque manche). Nous avons rajouté un attribut `- joueurTour` qui désigne le joueur à qui c’est le tour. L'association `Partie — Joueur` détermine les participants de la manche.

**La cible des cartes spéciales.** Une méthode `selectionnerJoueur(partie : Partie) : Joueur` dans la classe `CarteSpeciale` permet à l’utilisateur de sélectionner un joueur encore actif à cibler. Ensuite la méthode abstraite `appliquer(cible : Joueur)` agit sur le joueur sélectionné (Stop, Trois à la suite, don de Seconde Chance).

### Justification des cardinalités
 
- `Partie "1" — "2..*" Manche` : une partie contient **au moins 2 manches**. En effet, le score
  maximum atteignable en une seule manche est d'environ 171 points (7 plus gros numéros = 63, x2 =
  126, + 30 de bonus additifs + 15 du Flip 7), donc **impossible d'atteindre 200 en une manche** : il
  en faut au moins deux pour terminer la partie.
- `Partie "1" — "3..*" Joueur` : il faut **au moins 3 joueurs** pour qu'il y ait opposition.
- `Manche "1" — "1" Pioche` et `Manche "1" — "1" Defausse` : une manche a exactement une pioche et
  une défausse.
- `Manche "*" — "1" Joueur (donneur)` : chaque manche a **un seul** donneur ; un même joueur peut
  être donneur de plusieurs manches.
- `Joueur "1" — "1" CartesJoueur` : un joueur a exactement une classe CartesJoueur.
- `Tas "1" — "0..*" Carte` : un tas contient 0 à plusieurs cartes, et une carte est dans **exactement un** tas à la fois (elle ne peut pas être en même temps dans la pioche, la défausse et une
  main).



## Choix de conception détaillée
 
Le diagramme détaillé reprend la même structure que l'analyse.
 
 
**Les méthodes** La plupart des méthodes sont des actions
évidentes, mais certaines correspondent directement à une règle :
 
- `CartesJoueur.aNumero(valeur)` : retourne True ou False selon si les cartes du joueur contiennent une carte avec le numéro passé en argument, ce qui permet de détecter qu'on repioche un numéro déjà possédé, ce qui fait sauter le joueur ;
- `CartesJoueur.nbCartesNumero()` : permet de vérifier si un joueur a 7 numéros différents (Flip 7) ;
- `CartesJoueur.calculerScore()` : applique l'ordre exact des règles (en s'aidant des méthodes `sommeCartesNumero()`, `sommeBonusAdditif()` et `aBonusFoisDeux()`) ;
- `Carte.deplacer(Tas)` : déplace la carte dans un autre tas, en utilisant les méthodes `ajouteCarte` et `retireCarte` de la classe `Tas`.
- `Joueur.selectionAction()` : affiche une nouvelle interface où le joueur pourra sélectionner s’il veut piocher ou stopper.
- `Pioche.piocher()` : retourne une carte aléatoire de la pioche. La pioche peut être aléatoire à chaque tirage au lieu d’avoir un ordre défini, ce qui évite d'avoir à créer une méthode mélanger. De même, les méthodes `piocher` et `ajouter` de la classe `Tas` gèrent la distribution.

**L'effet des cartes (`appliquer`).** Les cartes Bonus et Spéciales ont une méthode `appliquer()`.
L'idée est que chaque carte sait calculer son propre effet. La méthode est commune à toutes les cartes,
mais chacune la réalise à sa façon — un `BonusAdditif` ajoute sa valeur, un `BonusFoisDeux` double
le total. Comme ça, pour calculer le score, on demande juste à la carte d'appliquer son effet,
sans avoir à vérifier de quel type de carte il s'agit.

Nous avons utilisé **l'intelligence artificielle** pour comprendre la syntaxe PlantUML.

Nous avons utilisé **l'intelligence artificielle** pour comprendre la syntaxe PlantUML.
