# compte rendu

## Classe Table 

la classe table peut démarrer une manche, la terminer, changer de donneur a chaque fin de manche et vérifier si un joueur a dépassé les 200 points. elle stock le numéro de la manche et quel est le donneur actuel. elle conserve aussi la pioche et la défausse, ainsi que les joueurs de la partie.

## Classe TasDeCarte

la classe abstraite TasDeCarte permet de définir la pioche et la défausse, elle possède au maximum 94 cartes (nombre de cartes du jeu).

### Classe Défausse

cette classe est un tas de carte qui se fais remplir de cartes le long de la partie.

### Classe Pioche 

cette classe est elle aussi un tas de carte, qu'on peut remplir une fois vidée. une fois que la pioche est vide, elle récupère les cartes de la défausse et les mélange. ces cartes deviennent la nouvelle pioche.

## Classe Joueur 

le joueur possède un nom, un prénom et un score. il fais partie d'une table et détiens une unique main. A la fin d'une manche, le joueur ajoute le score de sa main a son score total.

## Classe Main

cette classe possède entre 0 et 14 cartes, elle compte son score a la fin de la manche grâce a sa fonction calculScore, qui sera stocké dans la variable scoreManche. elle décide aussi si le joueur est encore dans la manche avec sa variable enJeu, si le joueur décide de s'arrêter, enJeu change d'état grâce a la fonction Stop. une main peut également tirer une carte, en défausser une, défausser toute ses cartes s'il perd et vérifier s'il détiens un flip 7.

## Classe Carte

La classe abstraite carte est la super classe de CarteBonus, CarteSpecial et CarteNumero.

### Classe CarteBonus

cette classe abstraite possède une valeur et une variable abstraite opérande,qui définis si l'effet est une addition ou une multiplication.

#### Classe CarteBonusAdd

cette classe permet d'additionner la valeur de CarteBonus au score de la main courante.

#### Classe CarteBonusMult

cette classe permet de multiplier la valeur de CarteBonus au score de la main courante.

### Classe CarteSpecial

cette classe abstraite possède un nom et un effet qui a pour cible une des mains de la partie.

#### CarteSpecialStop

met fin a la manche du joueur ciblé encore dans la manche. Si vous êtes le seul joueur encore dans la manche, vous êtes obligé de vous cibler vous-même.

#### CarteSpecial2ndChance

Cette carte est stockée dans la main du joueur qui l'a pioché. Si, plus tard dans cette manche, vous recevez une carte Numéro qui vous fait sauter, défaussez-la, ainsi que la carte Seconde Chance. Cela vous évite de sauter. Un joueur ne peut avoir qu’une seule carte Seconde Chance devant lui. S’il reçoit une deuxième carte Seconde Chance, il doit la donner à une autre main encore dans la manche. S’il n’y a pas d’autre joueur dans la manche, ou si tous les joueurs restants en ont déjà une, la carte Seconde Chance est défaussée.

#### CarteSpecialTroisALaSuite

Cible une main encore dans la manche (cela peut être vous). Si vous êtes le seul, vous êtes obligés de vous cibler vous-même. Le Donneur distribue les 3 prochaines cartes du paquet à cette main. Interrompez la distribution s’il saute ou réalise un Flip 7.

### Classe CarteNumero

cette classe représente les cartes de base du jeu, elles ont une valeur entre 0 et 12.