# Rapport des maquettes — SAÉ 2.01 [Flip 7]

**Équipe :** Souhayb, Caleb, Naël, Tristan

Ce rapport décrit les maquettes de notre application Flip 7. Les maquettes ont été
réalisées sur Figma ; les captures de chaque écran sont déposées dans ce dossier
`livrables/maquettes/`.

## Les vues de l'application

Notre maquette présente l'enchaînement complet d'une partie, du menu jusqu'à la fin.

Tout d'abord on a fait le **Menu principal.** Le premier écran affiche le titre Flip 7 et trois boutons : *Play* pour lancer
une partie, *Règles* pour consulter les règles, et *Quitter* pour fermer l'application.

Ensuite l'**Écran des règles.** Accessible depuis le menu, il rappelle le but du jeu qui est atteindre le score cible
 pour gagner, le déroulement d'un tour qui est de piocher pour accumuler des points ou s'arrêter
volontairement pour empêcher d'avoir un doublon et perdre la partie , l'effet de chaque type de carte (numéros, Seconde Chance, 3 à la suite, bonus,
multiplicateur) et les conditions de fin de tour (arrêt volontaire, doublon, Flip 7). Ça permet à un
joueur qui ne connaît pas le jeu de comprendre avant de commencer.

**Nouvelle partie (les configuration).** Avant de jouer, le joueur choisit le nombre de participants puisque le jeu est de minimimum 2 joueurs et max 4 joueurs nous avons mis
(2, 3 ou 4) et donc on empêche une valeur invalide
dès l'interface avec un selecteur 2/3/4 et saisit le nom de chaque joueur. Le joueur fixe également le score cible de fin de partie à l'aide d'un curseur
borné entre 50 et 200 points, conformément aux règles de la bibliothèque : là encore, le curseur
empêche toute valeur en dehors de cette plage.

**Écran de jeu.** C'est l'écran principal. Le joueur qui joue est mis en avant avec ses cartes, et les
autres joueurs sont affichés sur les côtés avec leurs cartes, leur score et leur état (en jeu / stop volontaire).
La pioche est au centre, et le joueur courant a le choix entre *Piocher* et *Stopper volontairement*. Les cartes sont
affichées avec un style et une couleur propres à chaque type (numéro, bonus, multiplicateur,
spéciales) pour qu'on les reconnaisse d'un coup d'œil.

**Manche terminée.** Quand une manche se termine, un écran affiche la manche terminéé donc sur l'exemple la mache 1 et récapitule le score de chaque joueur pour
la manche et son total cumulé, avec un bouton *Prochaine manche* pour continuer.

**Partie terminée.** Dès qu'un joueur atteint le score cible, un écran annonce le gagnant et affiche
les scores finaux de tous les joueurs. Deux boutons : *Menu* pour revenir à l'accueil et *Rejouer*
pour relancer une partie.

## Les interactions avec l'utilisateur

On a porté une attention particulière aux moments où le joueur doit confirmer une action ou faire un
choix, en utilisant des fenêtres de dialogue. Notre maquette en présente quatre.

**Confirmation avant de quitter.** Quand le joueur veut quitter, une fenêtre « Êtes-vous sûr de
vouloir arrêter ? » avec *Non* / *Oui* évite de fermer la partie par erreur : c'est une action
destructrice et  la partie en cours serait perdue.

**Confirmation de l'arrêt volontaire.** Quand le joueur courant clique sur *Stop*, une fenêtre
« Êtes-vous sûr de vouloir vous arréter ? » lui demande confirmation. S'arrêter est définitif pour
la manche, donc on évite le clic accidentel.Cela serait bête de plus prendre de point par erreur.

**Choix d'une cible après une carte Stop.** Quand un joueur pioche une carte Stop, la partie ne passe
pas directement au joueur suivant : une fenêtre « Sur quel joueur voulez-vous l'utiliser ? » lui fait
choisir le joueur à arrêter.

**Choix d'une cible après une carte 3 à la suite.** De la même manière, quand un joueur pioche une
carte « 3 à la suite », une fenêtre lui fait choisir le joueur ciblé.

Ces quatre fenêtres couvrent toutes les actions importantes du jeu : on ne déclenche jamais une
action comme quitter ou s'arrêter ou un effet sur un autre joueur (Stop, 3 à la suite) sans
passer par une confirmation ou un choix explicite.

## Lien avec l'architecture MVC

Les maquettes préparent directement le découpage MVC de l'application :

- le **modèle** sera la bibliothèque `flip7` (l'objet `Flip7` porte tout l'état et toutes les
  règles), on ne recode aucune règle de notre côté ;
- les **vues** seront les écrans présentés ici, codés en JavaFX, qui se contentent d'afficher l'état
  du jeu sans contenir de logique ;
- les **contrôleurs** feront le lien : ils réagissent aux actions des boutons (*Piocher*, *Stop*,
  choix de cible...), appellent la méthode correspondante du modèle, puis rafraîchissent la vue selon
  le nouvel état renvoyé. Par exemple, c'est le passage de la partie à l'état `ATTENTE_CIBLE_STOP` qui
  déclenchera l'ouverture de la fenêtre de choix de cible.
