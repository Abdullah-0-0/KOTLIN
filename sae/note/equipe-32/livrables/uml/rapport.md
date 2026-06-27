# Rapport de spécification — Flip7

## 1. Analyse du domaine

### 1.1 Entités identifiées

Le jeu Flip7 repose sur plusieurs mécaniques clés :

- **Carte** : Une entité abstraite dont héritent toutes les cartes.
- **CarteNumero** : Des cartes affichent des valeurs entières, appelée CarteNumero, allant de 0 à 12. La carte 0 ne rapporte aucun point. Le paquet est constitué d’un certain nombre d’exemplaires de chaque carte : on y trouve N exemplaires de la carte N, soit douze cartes de valeur 12, onze de valeur 11, et ainsi de suite, jusqu’à une seule carte de valeur 1 et une seule de valeur 0.
- **CarteBonus** : Des cartes qui modifient le score en fin de manche, sans faire sauter le joueur et sans compter pour le Flip 7. Deux catégories : les BonusPointsFixes (qui ajoutent +2, +4, +6, +8 ou +10 points) et les BonusMultiplicateur (comme ×2, qui double le total des CarteNumero avant d'ajouter les autres bonus).
- **CarteSpeciale** : Carte à effet immédiat. Trois sous-types : CarteStop (force un joueur à stopper), CarteSecondeChance (annule un saut), CarteTroisASuite (distribue 3 cartes à un joueur ciblé).
- **Main** : Ensemble de cartes devant un joueur pendant une manche.
- **Joueur** : Un participant identifié par un nom, portant un score total cumulé sur toutes les manches.
- **Paquet** : Une pile de cartes mélangées dans laquelle le Donneur pioche.
- **Défausse** : Une pile des cartes jouées, réintégrée dans le Paquet quand celui-ci est épuisé.
- **Manche** : Une série de tours, qui s'arrête soit quand tous les participants ont été éliminés, soit lorsqu'un Flip 7 est réussi.
- **Partie** : Succession de manches jusqu'à ce qu'un joueur atteigne 200 points.

### 1.2 Règles de la manche importantes

- Un joueur **saute** (perd tous ses points de la manche) s'il reçoit une CarteNumero dont il possède déjà la valeur.
- Un joueur réalise un **Flip 7** s'il possède 7 CarteNumero *différentes* : la manche s'arrête pour tout le monde, il gagne 15 points supplémentaires rajouter après, l'addition du score de la manche grâce au CarteNumero et des Bonus de Points Fixes(si le joueur en à) multiplier par 2 si il possèfr la carte x2. 
- La carte **SecondeChance** est conservée devant le joueur ; elle est défaussée automatiquement si utilisée ou en fin de manche. Un joueur ne peut en avoir qu'une.
- La carte **×2** ne double **pas** les Bonus de Points Fixes ni les 15 points du Flip 7.
- Si deux joueurs ont le même score et que celui-ci est supérieur ou égal à 200 points, on relance des manches jusqu'à ce que l'un des joueurs ait un score plus grand que tous les autres joueurs et supérieur ou égal à 200.

## 2. Conception détaillée

### 2.1 Outil utilisé

- PlantUML: https://plantuml.com/fr/

### 2.2 Choix d'analyse

- Utilisation d'une approche orientée objet afin de représenter chaque élément du jeu sous forme de classe.
- Séparation claire entre les entités Joueur, Carte, Partie. Et les éléments spécifiques aux cartes spéciales.
- Mise en place de relations d'héritage pour éviter la duplication de code.
- Respect du principe de responsabilité unique : chaque classe possède un rôle précis dans le jeu.

### 2.3 Analyse du diagramme de classes

Le diagramme est organisé autour des principales entités nécessaires à la gestion d'une partie du jeu. 
La conception repose sur une hiérarchie des cartes ainsi que sur des classes sur les joueurs, 
les manches et la partie complète.

#### 2.3.1 Gestion des carte

La classe Carte représente le concept général d'une carte. Elle contient l'attribut :

- nom : String

Cette classe sert de base à plusieurs spécialisations :

##### Cartes numériques

La classe CarteNumero hérite de Carte et ajoute :

- valeur : int

Elle représente les cartes possédant une valeur numérique utilisée dans le calcul du score.

##### Cartes bonus

La classe CarteBonus hérite également de Carte et définit la méthode :

- calculerBonus(total : int) : int

Cette méthode permet d'appliquer un bonus au score obtenu par le joueur.

Les classes :

- Fois
- Plus

sont des spécialisations de CarteBonus et implémentent chacune leur propre logique de calcul.

##### Cartes spéciales

La classe CarteSpe regroupe les cartes ayant un effet particulier.

Les classes :

- Stop
- Trois
- SecondeChance

héritent de cette classe et représentent les différents effets spéciaux pouvant intervenir durant une manche.

Cette organisation permet d'ajouter facilement de nouvelles cartes sans modifier les classes existantes.

#### 2.3.2 Gestion de la main du joueur

La classe Main représente l'ensemble des cartes possédées par un joueur durant une manche.

Elle contient :

- Une liste de cartes numériques.
- Une liste de cartes bonus.
- Une éventuelle carte spéciale.
- Une référence vers le joueur propriétaire.

Les principale méthodes permettent :

- d'ajouter des carte ;
- de vérifier la présence d'une valeur ;
- de calculer le score de la main ;
- de vider la main à la fin de la manche.

La classe Main centralise donc toute la logique liée au calcul des points.

#### 2.3.3 Gestion des joueurs

La classe Joueur contient les informations relatives à un participant :

- nom
- scoreTotal
- estDansLaManche
- Flip7
- main

Les méthodes :

- sauter()
- stopper()

permettent de gérer les décisions prises par le joueur durant la manche.

Chaque joueur possède une main, ce qui est représenté par une association de cardinalité 1 à 1 entre Joueur 
et Main.

#### 2.3.4 Gestion du paquet et de la défausse

La classe Paquet représente la pioche du jeu.

Elle fournit plusieurs opérations :

- piocher()
- melanger()
- estVide()
- taille()

La classe Defausse stocke les cartes retirées du jeu durant une manche.

Les méthodes :

- ajouter(carte)
- vider()

permettent de gérer son contenu.

Une relation de réapprovisionnement est présente entre la défausse et le paquet afin de recréer une pioche lorsque 
celle-ci est vide.

#### 2.3.5 Gestion des manches

La classe Manche coordonne le déroulement d'un tour de jeu.

Elle possède :

- un paquet ;
- une défausse ;
- le donneur ;
- la liste des joueurs participants.

Les principales responsabilités sont :

- démarrer une manche ;
- distribuer les cartes ;
- gérer la fin de manche.

Cette classe agit comme contrôleur principal des actions réalisées pendant un tour.

#### 2.3.6 Gestion de la partie

La classe Partie représente l'ensemble du jeu.

Elle contient :

- la liste des joueurs ;
- le score objectif ;
- les manches jouées.

Les méthodes :

- demarrer()
- jouerManche()
- estTerminer()
- determinerVainqueur()

permettent de gérer le cycle complet d'une partie jusqu'à la désignation du gagnant.

### 2.4 Justification des choix de conception

L'utilisation de l'héritage permet de modéliser efficacement les différents types de cartes tout en conservant 
une structure cohérente pour les classes Carte, CarteBonus et CarteSpe 

La séparation entre Partie, Manche, Joueur et Main(🖐️ du joueur) améliore la lisibilité du code et facilite sa maintenance. 
Chaque classe possède un rôle clairement défini, ce qui favorise la compréhension du code.


