# Point

	exo02
	├── README.md
	├── build
	├── classes
	│   └── Point.class
	├── lib
	│   └── fenetre.jar
	└── src
	    └── UtilisePoint.kt

Dans `classes/` vous avez une classe `Point` qui vous est fournie. Vous travaillerez dans `src/TestPoint.kt`.


### Partie 1 

Implémentez le "scénario" suivant, en utlisant les méthodes fournies par la classe `Point` (documentation de `Point` ci-dessous) : 

- Créer un `Point` p1 initialisé à (0,0), l'afficher.
- Créer un `Point` p2 initialisé à (1,2), l'afficher.
- Afficher la coordonnée x de p1, puis de p2.
- Modifier la coordonnée y de p2 en y affectant la valeur 3 , afficher p2.
- Translater p2 de 1 en x et de 1 en y, afficher p2.
- Déplacer p1 en (2,10), l'afficher.
- Calculer la distance de p2 à l'origine, l'afficher.
- Calculer la distance entre p1 et p2, l'afficher.
- Affecter p1 à p2, afficher les deux.
- Translater p1 de (2,1), afficher p1 et p2 (surpris(e) ?)


Documentation de `Point` :

- `Point() : Point `-- créé un nouveau Point initialisé à (0,0)
- `Point(x : Int, y : Int) : Point `-- créé un nouveau Point initialisé aux paramètres (`x``,y`)
- `Point(pt : Point) : Point` -- créé un nouveau Point initialisé comme le paramètre `pt`
- `deplacer(newX : Int, newY : Int)` -- déplace le point courant en (`newX`,`newY`) (i.e les nouvelles coordonnées du Point sont (`newX`,`newY`) )
- `distance(autrePt : Point) : Double` -- renvoie la distance entre le Point courant et le Point donné en paramètre
- `distanceOrigine() : Double` -- renvoie la distance à l'origine du Point courant
- `getX()` -- renvoie x du Point courant
- `getY()` -- renvoie y du Point courant
- `setX(int x)` -- modifie x du Point courant
- `setY(int y)` -- modifie y du Point courant
- `translater(int dx : Int, dy : Int)` -- translate le point courant de `dx` et `dy` (i.e les nouvelles coordonnées de mon Point sont (`x+dx`,`y+dy`) )


### Partie 2

Ajoutez à la suite de votre implémentation :

- déclarer un tableau de points __vide__ de taille 10.

- ajouter comme premier élément du tableau le point `p1`.

- développer une fonction `afficher(points : Array<Point?>)` qui affiche tous les points présents dans `points`.

- utiliser la fonction sur le tableau déclaré précédemment ; uniquement `p1` devrait s'afficher

- développer une fonction `remplir(points : Array<Point?>)` qui remplit le tableau de points de la manière suivante : le i-ème point du tableau est construit comme une copie de son prédécesseur sur laquelle on applique une translation (`dx`, `dy`), les valeurs de `dx` et `dy` étant tirées aléatoirement entre `1` et `10`.

Pour réaliser un tirage aléatoire, utiliser la fonction `Random.nextInt(start,end)` qui renvoie un entier aléatoire compris entre `start` et `end` inclus ; nécessite d'importer `kotlin.random.*`

- utiliser la fonction `remplir(...)` puis afficher le tableau de points obtenus .


### Partie 3 

**ATTENTION : ne semble pas fonctionner cette année sur les postes de l'IUT : pb de version de kotlin et de configuraiton de X11**

On fourni dans `lib/fenetre.jar`, une classe `Fenetre` capable d'afficher des points dans une fenetre, si on l'utilise correctement
    
- `Fenetre(titre : String)` -- construit une fenetre
- `ajouter(p : Point)` -- ajoute un point à la fenetre
- `montrer()` -- affiche la fenetre 

Implémentez les étapes suivantes :  

- créer un objet `Fenetre`
- ajouter tous les points de votre tableau de points à la fenetre avant de l'afficher.
- afficher l'objet  `Fenetre` créé précédemment


<!--
**Dans certaines salles de l'IUT, il semble manquer des librairies java/AWT ; ré-installez une JRE semble régler le problème :**

        sudo apt-get install default-jre
-->
