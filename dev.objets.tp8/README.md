# TP n°8 : capture, traitement et levée exceptions

(merci à J-F Remm pour les idées d'exercices)

> Voir la partie du dernier CM consacrée aux Exceptions en Kotlin.

## exo 1 : résolution d'adresse

Le programme fourni dans `exo1.Resolution.kt` permet d'obtenir l'IPv4 d'une machine à partir de son nom :

Essayez `wikipedia.fr`, `mediapart.fr`, `gitlab.univ-nantes.fr`, `nexus.dep-info.iut-nantes.univ-nantes.prive`, 

Mais que se passe-t-il si vous saisissez un nom invalide ?

Corrigez le programme pour qu'il affiche un message d'erreur mais ne plante pas ; procédez de deux manières (successivement) : 
1. dans la méthode `main()` ou 
2. dans la méthode `nomVersIP()`


## exo 2 : validation de saisie

Le programme fourni dans `exo2.Validation.kt` demande la saisie d'une chaine de caractères et la convertit
en un double. Cela marche pour `42.0`, `42`, `-42.0`

Que se passe-t-il si vous saisissez une valeur invalide ? ex : `totoro`

On souhaite corriger la fonction `donneDoubleSaisi()` ainsi :
1. demande d'une saisie de qch
2. saisie
3. tentative de conversion 
4. si échec alors on recommence en 1. tout en indiquant qu'il y a eu un problème

On repèrera l'échec de conversion grâce à l'exception `NumberFormatException` levée 


## exo 3 : pile d'entiers

Le code d'une pile d'entiers basé sur un tableau vous est fourni dans `exo3.pile.Pile`
Modifiez ce code pour que les exceptions soient levées : 

- `IllegalArgumentException` si la taille de départ de la pile est inférieure ou égale à 0
- `PileException` si on essaie de dépiler une pile vide
- `PileException` si on essaie d'empiler dans une pile pleine

Testez en utilisant le programme `exo3.UsagePile` (il sera nécessaire d'y ajouter des traitements d'exceptions)

## exo 4 : usage d'une pile d'entiers

Dans `exo4.Programme.kt` écrivez un programme interactif (en mode console) permettant de
1. initialiser une pile avec une taille aléatoire tirée entre -10 et 10
2. choisir entre empiler une valeur ou dépiler une valeur dans la pile ;  et recommencer en 2.

Bien entendu, vous utiliserez votre `exo3.pile.Pile` modifiée

> comme vous ne pouvez pas utiliser 
> `estVide()` ou `estPleine()` qui sont `private`, 
> vous devrez utiliser les exceptions `PileException` levées 
> afin de vous assurer que votre programme ne plante jamais.


## exo 5 : notation polonaise inversée (subsidiaire)

Utilisez une `exo3.pile.Pile` pour évaluer une expression mathématique 
donnée sous la forme d'une chaine de caractères)
et exprimée en notation `polonaise inversée` : 
`(2+8)/2` s'exprime en polonais inversé ainsi : `2 8 + 2 /`

Le principe de l'algorithme d'évaluation est le suivant :

1. On utilise une pile d'entiers
2. Quand on rencontre un entier, on l'empile
3. Quand on rencontre un opérateur (`op` = `+`, `-`, `*` ou `/`), _i)_ on dépile deux fois 
pour obtenir les opérandes `b`, puis `a`), _ii)_ on 
résout `a op b`, _iii)_ on ré-empile le résultat, et _iv)_ on continue l'évaluation

**Exemple :** Considérons `2 8 + 2 /` : 

1. on initialise une pile vide => `[]`
2. On lit `2` : on empile `2`  => `[2]`
3. On lit `8` : on empile `8` => `[8,2]`
4. On lit `+` : on dépile `8`, on dépile `2`, on calcule `2 + 8 = 10`, on empile `10` => `[10]`
5. On lit `2` : on empile `2` => `[2, 10]`
6. On lit `/` : on dépile `2`, on dépile `10`, on calcule `20 / 2 = 5`, on empile `5` => `[5]`
7. On a fini : on dépile le résultat = `5`

Implémentez cela dans `exo5.Polonaise.kt`

Evaluez des expressions plus complexes.
