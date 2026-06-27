# Rapport Test
on tient à precise que le majeur partie des tests sont des tests Paramétriques qui nécessite une forme de reflexion à la manier ou c'est utilise et que l'utilisation de l'IA n'est utilisé quand cas de rédaction et de comprehension !!
## Test pour les Cartes
1)  ### CartesNum:
    1) `ToStrings()`: 
            
        On a testé la methode `ToStings` en utilisant un `assertEquals` pour verifier si elle retourne bien la bonne face de `Print` le CarteNum.
    2) `equals` : 

        on a testé la methode `equals` en utilisant un `assertTrue` pour verifier s'il se comporte bien si on le compare à la même carte
        
        ````bash
        val carteTest= CarteNum(2)
        assertTrue(carteTest.equals(CarteNum(2)))
        ````
        Mais également avec un `assertFalse`, en testant de la même manier qu'avec le `assertTrue`.
        ````bash
        val carteTest= CarteNum(2)
        assertFalse(carteTest.equals(CarteNum(0)))
        ````
    3) `estCarteNum` :

        on a testé la methode `estCarteNum` en appellent cette methode sur d'autre type de Carte et bien evidement avec une `CarteNum`.

        pour cela, on utilise des `assertFalse` pour verifier que ce ne sont pas des `CarteNum` et l'inverse aussi utilise des `assertTrue` sur une `CarteNum` pour verifier que sa fonctionne normalement.
    4) `Creation Cartenum()`:
        on a testé aussi la Creation de `CarteNum()` en des `assertDoesNotThrow` pour verifier qu'il n'a pas d'exception qui se levée et que la carte est créé normalement.
        ````bash
        assertDoesNotThrow { val carte = CarteNum(n) }
        ````

        mais aussi en verifier les lévée d'expection dans le cas où on met une valeur invalide avec un `assertThrows`

        ````bash 
        assertThrows(IllegalArgumentException::class.java) {
                val carte = CarteNum(n)
            }
        ````


2) ### Test TestCarteBonusPlus: 
    1) `ToStrings` :
        pour vérifier la fonction `toString`, on a utilisé plusieurs cartes bonus valides (+2, +4, +6, +8 et +10) puis on a comparé le résultat retourné avec la chaîne attendue grâce à des `assertEquals`.

        ````bash
        val carte = CarteBonusPlus(2)
        assertEquals("[Carte Bonus +2]", carte.toString())
        ````
    2) `equals`:
         pour tester `equals`, on a comparé des cartes ayant la même valeur ainsi que des cartes ayant des valeurs différentes à l'aide des `assertTrue` et `assertFalse`.

        ````bash
        val carteTest = CarteNum(2)
        assertTrue(carteTest.equals(CarteNum(2)))
        ````
    3) `estCarteBonusPlus` :
       pour tester cette fonction, on a créé plusieurs types de cartes (CarteNum, CarteStop, Carte2ndeChance, Carte3aLaSuite, CarteBonusMultiplie et CarteBonusPlus) puis on a vérifié que seule une `CarteBonusPlus` retourne `true`.

       On a également testé les valeurs interdites afin de vérifier la levée de `IllegalArgumentException`.

        ````bash
        assertThrows(IllegalArgumentException::class.java) {
            CarteBonusPlus(1)
        }
        ````
3) ### Test CartesStop: 
    1) `ToStrings` :
       pour vérifier `toString`, on a utilisé un `assertEquals` afin de comparer la chaîne retournée avec la valeur attendue.

        ````bash
        val carte = CarteStop()
        assertEquals("[Carte Stop]", carte.toString())
        ````
    2) `equals`:
       pour vérifier `equals`, on a comparé une `CarteStop` avec une autre `CarteStop` ainsi qu'avec plusieurs autres types de cartes.

        ````bash
        assertTrue(CarteStop().equals(CarteStop()))
        ````
    3) `estCarteStop` :

       pour tester cette fonction, on a vérifié qu'une `CarteStop` retourne bien `true` lorsqu'on appelle `estCarteStop()`.

       On a également vérifié que les autres méthodes de type (`estCarteNum`, `estCarteBonusPlus`, `estCarte2ndeChance`, etc.) retournent bien `false`.

        ````bash
        val carte = CarteStop()
        assertTrue(carte.estCarteStop())
        ````

4) ### Test Carte3aLaSuite: 
    1) `ToStrings` :
       pour tester `toString`, on a utilisé un `assertEquals` afin de vérifier que la chaîne retournée correspond bien au texte attendu.

        ````bash
        val carte = Carte3aLaSuite()
        assertEquals("[Carte 3 à la suite]", carte.toString())
        ````
    2) `equals`:
       pour vérifier `equals`, on a comparé une `Carte3aLaSuite` avec une autre `Carte3aLaSuite` ainsi qu'avec plusieurs autres types de cartes.

        ````bash
        assertTrue(Carte3aLaSuite().equals(Carte3aLaSuite()))
        ````
    3) `estCarte3aLaSuite` :

       pour tester cette fonction, on a créé plusieurs cartes de types différents puis on a appelé `estCarte3aLaSuite()` sur chacune d'elles.

       On a vérifié qu'une `Carte3aLaSuite` retourne `true` et que toutes les autres cartes retournent `false`.

        ````bash
        val carte = Carte3aLaSuite()
        assertTrue(carte.estCarte3aLaSuite())
        ````
5) ### Test Carte2ndeChance: 
    1) `ToStrings`  :
        
        de la meme manière que l'on a testée carteNum on a utiliser un `assertEquals` pour comparer le toString avec une String égale au resultat demandée
    2) `equals`:

        pour verifier equals, 
        on a créé une liste de carte 2ᵉ chance liée à un string noté `equals` ,`not equals` et qui se comparait avec une seule carte 2ᵉ chance

        ````bash
        val carte1= Carte2ndeChance()
        val carte2= CarteStop()
        assertFalse(carte1.equals(carte2))
        ````
    3) `estCarte2ndeChance`:

        pour tester cette fonction on a fait une liste des diferent type de cartes puis on les a toutes fait passer dans la fonction `estCarte2ndeChance`
        puis on a tester les booléens qui en resortait pour verifier que c'etait les bons

        ````bash
        val carte2 = Carte2ndeChance()
        assertTrue(carte2.estCarte2ndeChance())
        ````
6) ### Test CarteBonusMultiplie: 
    1) `ToStrings` :

        pour verifier la fonction ToString on a de nouveau utiliser la technique du `assertEquals`
    2) `equals`:

        pour verifier la fonction equals on a utiliser plusieur carte de plusieur type (comme une carteStop, une autre carte carteBonusMultiplie, une carteNum, etc...)
        puis on les a fait se comparer entre en comparant le booléen donnée par `equals` a celui demandée
    3) `estCarteBonusMultiplie`:

        pour tester cette fonction on a fait un liste de diferent type de carte puis on les a toutes faites passer dans la fonction `estCarteBonusMultiplie`
        puis verifier que le booléen qui en rssortait etait bien le bon
7) ### Test EtatJoueur:
   #### TestEtatJoueur : 
   Le but ici était de simuler une partie et de vérifier l'état des joueurs à la fin de cette dernière afin de vérifier que la gestion de l'état des joueurs s'était bien passée
   #### TestEtatJoueurPerdu :
   Même principe ici sauf que l'on teste l'état PERDU des joueurs. En donnant par exemple un doublon à un seul joueur.
8) ### Test Exception:
   #### ListeJoueursInvalideException :
   J'ai testé les réactions si le nombre de joueurs était plus ou moins élévé par rapport à la liste rentrée
   #### NombreJoueursInvalideException :
   Même chose que pour ListeJoueursInvalideException
   J'ai aussi testé que ça ne renvoit pas d'erreur dans des conditions normales
   #### ScoreFinPartieInvalideException :
   J'ai testé là aussi sur le même modèle que ListeJoueursInvalideException
   #### CarteInvalideException : 
   J'ai testé avec tout les types de cartes ainsi qu'avec les cartes du même type présente dans le même deck. J'ai testé du coté de la fonction stop et de la fonction 3aLaSuite
   #### IndiceJoueurInvalideException :
   J'ai testé l'exécution de chaque fonction dans chaque état de etatpartie. Je me suis rendu compte à la fin que ça revenait aussi à tester la gestion des exceptions du coté de l'états des joueurs
9) ### Test Flip7: 
    IA utilise pour bien redige cette partie (Uniquement pour cette partie nommée Flip7)
    1) `Flip7`:

        Pour la création de `Flip7` nous avons vérifié les validations du constructeur avec des `assertThrows` et des `assertDoesNotThrow` :
            - `ListeJoueursInvalideException` quand la taille de la liste de joueurs ne correspond pas au nombre demandé ou que la liste est hors bornes (doit être entre 2 et 4).
            - `NombreJoueursInvalideException` quand le nombre de joueurs (paramètre) est invalide (trop petit ou trop grand).
            - `ScoreFinPartieInvalideException` quand le score final passé en paramètre n'est pas dans l'intervalle autorisé (50..200).
            - `PiocheInvalideException` si la pioche fournie est incomplète et que le mode `debug` est désactivé.

            Exemple d'utilisation dans les tests :
            ````bash
            assertDoesNotThrow {
                val flip7 = Flip7(nb_joueur, listeJoueur, cartes.subList(0, nbCarteMax), debug, scoreFinal)
            }
            ````
    2) `joueurCourantPiocheUneCarte` :

        Tests réalisés :
        - Le joueur courant peut piocher quand il est actif : `assertDoesNotThrow`.
        - La taille de la main augmente de 1 après une pioche : `assertEquals(tailleAvant + 1, tailleApres)`.
        - La carte piochée est bien présente dans la main du joueur : `assertTrue(carte in main)`.

        Exemple :
        ````bash
        val tailleAvant = flip7.main[flip7.joueurCourant]?.size
        val carte = assertDoesNotThrow { flip7.joueurCourantPiocheUneCarte() }
        val tailleApres = flip7.main[flip7.joueurCourant]?.size
        assertEquals(tailleAvant!! + 1, tailleApres)
        assertTrue(carte in flip7.main[flip7.joueurCourant])
        ````
    3) `joueurCourantCibleStop` :

        Tests réalisés :
        - Ciblage valide d'un autre joueur lorsque le joueur courant a pioché une `CarteStop` : `assertDoesNotThrow`.
        - `CarteInvalideException` si on tente d'utiliser une carte qui n'est pas une `CarteStop` pour cette action.
        - `EtatPartieInvalideException` si la méthode est appelée dans un état de partie inapproprié (ex. hors attente de cible stop).
        - `IndiceJoueurInvalideException` si l'indice du joueur ciblé est hors bornes.

        Dans les tests on utilise un `ControleDeck` pour forcer la pioche d'une `CarteStop` puis on appelle :
        ````bash
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertDoesNotThrow { flip7.joueurCourantCibleStop(carteStop, flip7.joueurCourant + 1) }
        ````
    4) `joueurCourantCible3aLaSuite` :

        Tests réalisés :
        - Application correcte de l'effet `3 à la suite` quand le joueur courant possède la `Carte3aLaSuite` (vérification des changements de taille de mains et des exceptions).
        - `CarteInvalideException` si on tente d'utiliser une carte non compatible.
        - `EtatPartieInvalideException` et `IndiceJoueurInvalideException` pour états ou cibles invalides.

        Les tests vérifient que, lorsqu'une `Carte3aLaSuite` est jouée, les mains des joueurs ciblés évoluent conformément à l'effet (tests basés sur tailles avant/après et `assertDoesNotThrow`/`assertThrows`).

        5) `joueurCourantDitStop` :

        Tests réalisés :
        - Le joueur courant passe en état `STOP` et ne peut plus jouer par la suite : `assertEquals(EtatJoueur.STOP, flip7.etatJoueur[index])`.
        - L'appel empêche le joueur de piocher ou d'agir ensuite (vérifié par `assertThrows` sur actions interdites).
        - L'indice du joueur courant avance correctement quand on dit stop.

        Exemple :
        ````bash
        tflip.joueurCourantDitStop()
        assertEquals(EtatJoueur.STOP, tflip.etatJoueur[0])
        ````

        6) `scoreManche` :

        Tests réalisés :
        - Appel prématuré (quand des joueurs n'ont pas terminé) lève `EtatPartieInvalideException`.
        - Après que tous les joueurs aient dit stop, `scoreManche()` calcule les scores et renvoie une liste de scores par joueur ; les tests comparent la somme des valeurs des cartes en main au score retourné (`assertEquals`).

        Exemple :
        ````bash
        // après que tous les joueurs ont dit stop
        val score = flip7.scoreManche()
        assertEquals(sommeValeursMainJoueur1, score[0])
        ````

        7) `nouvelleManche` :

        Tests réalisés :
        - Impossible de démarrer une nouvelle manche tant que le score de la manche courante n'a pas été calculé : `EtatPartieInvalideException`.
        - Après `scoreManche()` la méthode `nouvelleManche()` réinitialise l'état et la pioche pour la manche suivante : testée avec `assertDoesNotThrow`.

        Exemple :
        ````bash
        assertThrows(EtatPartieInvalideException::class.java) { flip7.nouvelleManche() }
        val score = flip7.scoreManche()
        assertDoesNotThrow { flip7.nouvelleManche() }
        ````
    
    



10) ### Test OutilCarte: 
    1) `verifiePiocheInitiale` :
        pour la `verifiePiocheInitiale` , on l'a testé en
        donne une piogne complet est ce que sa va levée une exception en temps normale non c'est pour cela que on n'a utilise des `assertDoesNotThrow` 

        ````bash
        assertDoesNotThrow{
                    outils.verifiePiocheInitiale(liste_carte)
                }
        ````
        et dans le cas ou la pioche est incorrect , on levée l'exception  `PiocheInvalideException` 

        ````bash
        assertThrows(PiocheInvalideException::class.java){
                    outils.verifiePiocheInitiale(liste_carte)
                }
        ````
    
    2) `verifieMainCorrecte`:

        Et pour celui la , ce que on a fait, c'est qu'on a 
        verifier que une Execption cette levée lorsque on donne une main qui contenait des doublons l'exception `verifieMainCorrecte` en utilise des `assertThrows` et dans le cas  inverse lorsqeu c'est une bonne mais que sa ne levée pas de expection avec `assertDoesNotThrow`.
        
         on l'a testé avec des test parametique mais pas que cette methode la majore partie des methodes
    3) `calculScore`:

        on calcule a la main la somme des points que le joueur devrais avoir et on le compare avec cette donne par la mehtode `calculScore` avec un `assertEquals`
    7) `estFlip7`:
        pour celui la on change pas de methode de test , on donne une liste plannigier , on fonction de la liste si c'est bien une liste de 7 carteNum different, on verifier que sa fait bien un `Flip7` en test avec un `assertTrue` mais aussi dans le cas inverse avce cette fois ci un `assertFalse`
        et on n'a emprofite pour test l'exception `MainInvalideException` qui levée une exception quand il y a un doublon dans la main ou que ce n'est un flip7 



# Table de Decisions
## testCarte2ndChance
|Entrées / Conditions|R1|R2|R3|R4|R5|       R6        |
|:---|:---:|:---:|:---:|:---:|:---:|:---------------:|
|Objet créé = Carte2ndeChance|X|X|X|X|X|        X        |
|Comparaison avec Carte2ndeChance|X|||||                 |
|Comparaison avec CarteBonusMultiplie||X||||                 |
|Comparaison avec CarteStop|||X|||                 |
|Comparaison avec CarteBonusPlus||||X||                 |
|Comparaison avec Carte3aLaSuite|||||X|                 |
|Appel de toString()||||||        X        |
|estCarte2ndeChance() = true|X|X|X|X|X|                 |
|equals() = true|X|||||                 |
|equals() = false||X|X|X|X|                 |
|toString() = "[Carte 2nde chance]"||||||        X        |
|Sortie attendue|Égalité|Différent|Différent|Différent|Différent| Chaîne correcte |

## TestCarte3aLaSuite
|Entrées / Conditions|R1|R2|R3|R4|R5|R6|R7|      R8      |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:------------:|
|Objet créé = Carte3aLaSuite|X|X|X|X|X|X|X|      X       |
|Comparaison avec Carte3aLaSuite|X|||||X||              |
|Comparaison avec CarteBonusMultiplie||X||||||              |
|Comparaison avec CarteStop|||X|||||              |
|Comparaison avec CarteBonusPlus||||X||||              |
|Comparaison avec Carte2ndeChance|||||X|||              |
|Appel de toString()|||||||X|              |
|Vérification estCarte3aLaSuite()|X|X|X|X|X|||      X       |
|equals() = true|X|||||X||              |
|equals() = false||X|X|X|X|||              |
|toString() = "[Carte 3 à la suite]"|||||||X|              |
|estCarte3aLaSuite() = true|X|X|X|X|X|||      X       |
|Sortie attendue|Égalité|Différent|Différent|Différent|Différent|Égalité|Chaîne correcte| Type Correct |

## TestCarteBonusMultiple
|Entrées / Conditions|R1|R2|R3|R4|R5|R6|R7|      R8      |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:------------:|
|Objet créé = CarteBonusMultiplie|X|X|X|X|X|X|X|      X       |
|Comparaison avec CarteBonusMultiplie|X|||||X||              |
|Comparaison avec CarteNum||X||||||              |
|Comparaison avec CarteStop|||X|||||              |
|Comparaison avec CarteBonusPlus||||X||||              |
|Comparaison avec Carte3aLaSuite|||||||
|Comparaison avec Carte2ndeChance|||||X|||              |
|Appel de toString()|||||||X|              |
|Vérification estCarteCarteBonusMultiplie()|X|X|X|X|X|||      X       |
|equals() = true|X|||||X||              |
|equals() = false||X|X|X|X|||              |
|toString() = "[Carte 3 à la suite]"|||||||X|              |
|estCarteBonusMultiplie() = true|X|||||||      X       |
|estCarteBonusMultiplie() = false||X|X|X|X|X||              |
|Sortie attendue|Égalité|Différent|Différent|Différent|Différent|Différent|Chaîne correcte| Type Correct |

## TestCarteBonusPlus
|Entrées / Conditions|   R1    |    R2     |    R3     |    R4     |         R5          |    R6     |       R7        |      R8      |
|:---|:-------:|:---------:|:---------:|:---------:|:-------------------:|:---------:|:---------------:|:------------:|
|Valeur < 2|    X    |           |           |           |                     |           |                 |              |
|Valeur impaire > 1|         |     X     |           |           |                     |           |                 |              |
|Valeur = 2|         |           |     X     |     X     |          X          |           |                 |              |
|Valeur = 4|         |           |           |           |                     |     X     |                 |              |
|Valeur = 6,8,10|         |           |           |     X     |                     |           |        X        |              |
|Comparaison de cartes|         |           |           |           |                     |           |                 |      X       |
|Résultats attendus|         |           |           |           |                     |           |                 |              |
|IllegalArgumentException|    X    |     X     |           |           |                     |           |        X        |              |
|Carte créée|         |           |     X     |     X     |          X          |     X     |        X        |      X       |
|estCarteBonusPlus() = true|         |           |     X     |           |                     |     X     |        X        |              |
|toString() correct|         |           |           |     X     |                     |           |        X        |      X       |
|estCarteBonusPlus() = false|         |           |           |           |          X          |           |        X        |              |
|equals() fonctionne|         |           |           |           |                     |           |                 |      X       |
|Sortie attendue| Exception | Exception | Type correct | Chaîne correcte | Faux sur autre type | Bonus +4 valide | Bonus pair valide | Égalité correcte |

## TestCarteStop

| Entrées / Conditions                 |    R1     |      R2      |       R3        |         R4          |    R5     |        R6        |
|:-------------------------------------|:---------:|:------------:|:---------------:|:-------------------:|:---------:|:----------------:|
| Valeur < 0                           |     X     |              |                 |                     |           |                  |
| Valeur ∈ [0..12]                     |           |      X       |        X        |          X          |           |                  |
| Valeur > 12 |           |              |                 |                     |     X     |                  |
| Comparaison avec autre type de carte          |           |              |                 |          X          |           |                  |
| Test equals()      |           |              |                 |                     |           |        X         |
| Résultats attendus      |           |              |                 |                     |           |                  |
| IllegalArgumentException                  |     X     |              |                 |          X          |           |                  |
| Carte créée          |           |      X       |        X        |          X          |           |        X         |
| estCarteNum() = true                    |           |      X       |                 |                     |           |                  |
| toString() correct                    |           |              |        X        |                     |           |                  |
| estCarteNum() = false sur autre carte   |           |              |                 |          X          |           |                  |
| equals() correct   |           |              |                 |                     |           |        X         |
| Sortie attendue                      | Exception | Type correct | Chaîne correcte | Faux sur autre type | Exception | Égalité correcte |

## TestExeption

| Entrées / Conditions                 |    R1     |      R2      |       R3        |         R4          |    R5     |        R6        |
|:-------------------------------------|:---------:|:------------:|:---------------:|:-------------------:|:---------:|:----------------:|
| Comparaison avec CarteStop           |     X     |              |                 |                     |           |                  |
| Comparaison avec CarteBonusMultiplie |           |      X       |                 |                     |           |                  |
| Comparaison avec CarteBonusPlus      |           |              |        X        |                     |           |                  |
| Comparaison avec Carte3aLaSuite      |           |              |                 |          X          |           |                  |
| Comparaison avec Carte2ndeChance     |           |              |                 |                     |     X     |                  |
| Appel toString()                     |           |              |                 |                     |           |        X         |
| Résultats attendus                   |           |              |                 |                     |           |                  |
| estCarteStop() =  true               |     X     |      X       |        X        |          X          |     X     |                  |
| equals() = true                      |     X     |              |                 |                     |           |                  |
| equals() = false                     |           |      X       |        X        |          X          |     X     |                  |
| estCarteNum() = false                |           |      X       |        X        |          X          |     X     |                  |
| estCarte2ndeChance() = false         |           |      X       |        X        |          X          |     X     |                  |
| estCarte3aLaSuite() = false          |           |      X       |        X        |          X          |     X     |                  |
| estCarteBonusMultiplie() = false     |           |      X       |        X        |          X          |     X     |                  |
| estCarteBonusPlus() = false          |           |      X       |        X        |          X          |     X     |                  |
| toString() = "[Carte Stop]"          |           |              |                 |                     |           |        X         |
| Sortie attendue                      | Égalité | Différent | Différent | Différent | Différent | Chaîne correcte |


## TestFlip7

| Entrées / Conditions            | R1 | R2 | R3 | R4 | R5 | R6 | R7 | R8 |
|:--------------------------------|:---------:|:------------:|:---------------:|:-------------------:|:---------:|:----------------:|:---------:|:---------:|
| Entrées                         | | | | | | | | |
| Nb joueurs < 2                  | X | | | | | | | |
| Nb joueurs valide (2..4)        | | X | X | X | X | X | X | X |
| Taille liste != nb joueurs      | | X | | | | | | |
| Taille liste = nb joueurs       | | | X | X | X | X | X | X |
| Score < 50                      | | | | X | | | | |
| Score > 200                     | | | | | X | | | |
| Pioche incomplète               | | | | | | X | X | |
| Pioche valide                   | | | | | | | | X |
| Résultats attendus              | | | | | | | | |
| NombreJoueursInvalideException  | X | | | | | | | |
| ListeJoueursInvalideException   | | X | | | | | | |
| ScoreFinPartieInvalideException | | | | X | X | | | |
| PiocheInvalideException         | | | | | | X | X | |
| Construction réussie            | | | X | | | | | X |
| Sortie attendue                 | Nb invalide | Liste invalide | Création OK | Score invalide | Score invalide | Pioche invalide | Pioche invalide | Création OK |

### JoueurCourantPiocheUneCarte()
| Entrées / Conditions                | R1 |
|:------------------------------------|:---------:|
| Entrées                             | |
| Joueur courant actif                | X |
| Carte disponible dans la pioche     | X |
| Résultat attendu                    | |
| Aucune exception                    | X |
| Taille de la main +1                | X |
| Carte piochée présente dans la main | X |

### joueurCourantCibleStop()

| Entrées / Conditions                       | R1 | R2 | R3 |
|:-------------------------------------------|:---------:|:------------:|:---------------:|
| Entrées                                    | | | |
| Carte ciblée = CarteStop                   | X | | |
| Carte ciblée != CarteStop                  | | X | |
| Partie en attente de cible Stop            | X | | X |
| Action interdite (piocher avant de cibler) | | | X |
| Résultat attendu                           | | | |
| Ciblage autorisé                           | X | | |
| EtatPartieInvalideException                | | X | X |
| Effet Stop appliqué                        | X | | |

### JoueurCourantPiocheUneCarte()

| Entrées / Conditions          | R1 | R2 |
|:------------------------------|:---------:|:------------:|
| Entrées                       | | |
| Carte jouée = Carte3aLaSuite  | X | |
| Carte jouée != Carte3aLaSuite | | X |
| Partie en attente de cible    | X | |
| Résultat attendu              | | |
| Action autorisée              | X | |
| EtatPartieInvalideException   | | X |
| Distribution des cartes bonus | X | |

### joueurCourantCible3aLaSuite

| Entrées / Conditions          | R1 | R2 |
|:------------------------------|:---------:|:------------:|
| Entrées                       | | |
| Carte jouée = Carte3aLaSuite  | X | |
| Carte jouée != Carte3aLaSuite | | X |
| Partie en attente de cible    | X | |
| Résultat attendu              | | |
| Action autorisée              | X | |
| EtatPartieInvalideException   | | X |
| Distribution des cartes bonus | X | |

## TestOutilCarte

### EstFlip7()

| Entrées / Conditions          | R1 | R2 | R3 |
|:------------------------------|:---------:|:------------:|:---------------:|
| Entrées                       | | | |
| Main valide                   | X | X | |
| Toutes les cartes différentes | X | | |
| Doublon présent               | | X | |
| Main invalide                 | | | X |
| Résultats attendus            | | | |
| estFlip7() = true             | X | | |
| estFlip7() = false            | | X | |
| MainInvalideException         | | | X |
| Sortie attendue               | Flip7 | Pas Flip7 | Exception |

---

### CalculScore()

| Entrées / Conditions  | R1 | R2 | R3 |
|:----------------------|:---------:|:------------:|:---------------:|
| Entrées               | | | |
| Main valide           | X | X | |
| Doublon présent       | | X | |
| Main invalide         | | | X |
| Résultats attendus    | | | |
| Score calculé         | X | | |
| Score = 0             | | X | |
| MainInvalideException | | | X |
| Sortie attendue       | Score normal | Score nul | Exception |

---

### VerifieMainCorrecte()

| Entrées / Conditions  | R1 | R2 |
|:----------------------|:---------:|:------------:|
| Entrées               | | |
| Aucune valeur répétée | X | |
| Valeur répétée        | | X |
| Résultats attendus    | | |
| Main valide           | X | |
| MainInvalideException | | X |
| Sortie attendue       | Main correcte | Exception |

---

### verifiePiocheInitiale()

| Entrées / Conditions        | R1 | R2 | R3 |
|:----------------------------|:---------:|:------------:|:---------------:|
| Entrées                     | | | |
| Toutes les cartes présentes | X | | |
| Carte manquante             | | X | |
| Répartition incorrecte      | | | X |
| Résultats attendus          | | | |
| Pioche valide               | X | | |
| PiocheInvalideException     | | X | X |
| Sortie attendue             | Conforme | Exception | Exception |