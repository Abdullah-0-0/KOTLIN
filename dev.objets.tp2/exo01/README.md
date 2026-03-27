# Vehicule

	exo01
	├── README.md
	├── build
	├── classes
	│   └── Vehicule.class
	├── src
	│   └── UtiliseVehicule.kt

Dans `classes/` vous avez une classe `Vehicule` qui vous est **uniquement** fournie sous sa forme **compilée**. 
Les méthodes fournies par la classe `Vehicule` vous sont données à la fin de ce fichier

Vous travaillerez dans `src/UtiliseVehicule.kt`. 

1. Quelle est la bonne ligne de commande pour compiler `UtiliseVehicule.kt` en utilisant `Vehicule.class` et en envoyant le resultat dans `build/`  ? <!--kotlinc ./src/UtiliseVehicule.kt -cp classes/  -d build -->

2. Quelle est la bonne ligne de commande pour éxécuter `UtiliseVehiculeKt`, tout en restant dans le dossier `exo01/` bien entendu ? <!--kotlin -cp classes/:build/ UtiliseVehiculeKt -->

3. Implémentez le *scénario* suivant dans `UtiliseVehicule.kt` : 

- créer une voiture de couleur rouge à quatre roues ne pouvant dépasser 180km/h,
- afficher la voiture
- démarrer la voiture, la faire accélérer de 50km/h, 
- afficher la voiture
- la faire accélérer de 40km/h.
- faire afficher sa vitesse
- créer un autre véhicule
- démarrer, accélérer, ...
- comparer les vitesses des deux véhicules.
- ...
- arrêter les deux vehicules 


## Documentation de `Vehicule`

#### constructeur 
- `Vehicule(nomVehicule : String, couleurInitiale : String, nombreRoues : Int, vitesseMaximum : Double) : Vehicule` -- Créé un nouveau véhicule initialisé aux paramètres donnés

#### méthodes
- `accelerer(accelereration : Double)` -- Accélère le véhicule si celui-ci est en marche, 
sans dépasser la vitesse maximum
- `arreter()` -- Arrête le véhicule
- `decelerer(decelereration : Double)` -- Ralentit le véhicule si celui-ci est en marche.
- `demarrer()` -- Démarre le véhicule
- `estEnMarche() : Boolean` -- renvoie `true` si le véhicule est en marche, `false` dans le cas contraire.
- `vaPlusVite(autre : Vehicule) : Boolean` -- Compare la vitesse du véhicule sur lequel s'applique la méthode avec la vitesse du véhicule autre donné en paramètre.
- `vitesse() : Double` -- Donne la vitesse du Véhicule