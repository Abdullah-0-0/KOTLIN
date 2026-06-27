L'ia a été utilisée pour aider à la redaction du rapport, pas pour effectuer les schémas umls

# Classe Carte

La classe Carte a été définie comme une classe abstraite car toutes les cartes du jeu partagent un concept commun : elles sont des éléments manipulables pendant une partie. Elles ont toutes un identifiant, elles peuvent être distribuées aux joueurs, placées dans la pioche ou dans la défausse, et elles participent au déroulement d’une manche.

Le diagramme sépare les cartes en trois catégories :

- la classe cartes numériques;
- la classe abstraite cartes spéciales;
- la classe abstraite cartes bonus.

ces 3 sous-classes sont justifiés par le fait qu'elles existent 3 types de cartes ayant des types d'effets radicalement différents, les relations d'héritages sont logiques car elles permettent d'éviter de dupliquer des attributs communs comme l’identifiant, tout en permettant de spécialiser chaque type de carte selon ses besoins.

# Classe Joueur

La classe Joueur est indispensable, car le jeu est avant tout une interaction entre plusieurs joueurs,un joueur possède un pseudo, une main de cartes, un score.

# Classe Score

La classe Score permet de suivre l'évolution des points de chaque joueur par manche et combien de points total le joueur a accumulé

# Classe Manche

La classe Manche représente un tour de jeu au sein d’une partie. Elle est nécessaire parce qu’un Flip 7 ne se joue pas en une seule manche, mais en plusieurs successives. Une manche doit gérer :

- la pioche ;
- la défausse ;
- les scores ;
- les joueurs éliminés ;
- le journal de manche.

Cette classe a donc été choisie pour regrouper tout ce qui concerne le déroulement d’un tour de jeu.

# Classe Partie

La classe Partie correspond à l’ensemble de la session de jeu. Elle contient les joueurs et les manches. Son rôle est de donner une structure globale au jeu, en rassemblant les éléments de plusieurs tours dans une seule entité.

## Relation entre Partie et Manche

La relation entre Partie et Manche a été choisie parce qu’une partie est composée de plusieurs manches. Cela reflète la logique du jeu, où l’on joue successivement plusieurs tours. Le fait d’utiliser une agrégation ou une composition permet de montrer que les manches appartiennent au contexte d’une partie.

## Relation entre Partie et Joueur

Une partie implique plusieurs joueurs. Cette relation est essentielle, car le jeu est un jeu de confrontation entre participants. Le modèle impose donc qu’une partie regroupe au moins deux joueurs.

## Relation entre Joueur et Carte

Un joueur possède une main de cartes. Cette relation est naturelle, car les cartes sont distribuées aux joueurs et constituent un élément central de leur stratégie. Elle permet aussi de montrer que chaque joueur peut avoir un nombre variable de cartes selon l’avancement du jeu.

## Relation entre Joueur et Score

Chaque joueur possède un score global, mais il peut aussi avoir un score spécifique à chaque manche. Ce choix permet de séparer la performance générale du joueur dans la partie et sa performance dans une manche précise.

## Relation entre Manche et cartes

La manche contient à la fois une pioche et une défausse. Cela reflète le fonctionnement réel du jeu : les cartes sont tirées, jouées et mises de côté. Le fait d’associer la manche à ces deux collections rend la logique du jeu plus claire.

# Classe journal

La classe Journal a été introduite pour conserver des informations utiles sur une manche : scores, joueur le plus performant, joueurs éliminés. Cela permet de garder une trace du déroulement du jeu et facilite l’analyse des résultats.
(Elle a été ajoutée dans pendant la partie detaille, elle ne figure donc pas dans la partie analyse)