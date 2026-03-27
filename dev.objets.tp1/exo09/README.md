Implémentez les fonctions 

* `somme(tab : NutArray<Int>)`, 
* `recherche(tab : NutArray<Int>, v :Int)`, 
* `estEnsemble(tab : NutArray<Int>)` et 
* `envers(tab : NutArray<Int>)`

et validez votre code en passant les cas de tests fournis

Implémentez la fonction  `egalite(tab1 : Array<Int>, tab2 : Array<Int>)`, en réutilisant  la fonction `estEnsemble(...)` dans `egalite(...)` pour vous assurer que les tableaux à vérifier décrivent bien des ensembles.
Si ce n'est pas le cas, l'égalité sera forcément fausse.

Validez votre code en passant les cas de tests fournis. 

> Si vous éxécuter tous les cas de tests, il y a 45 cas de tests, ce qui n'est pas très lisible. Vous pouvez préciser quels cas de tests vous souhaiter éxécuter, par exemple :

    ./gradlew test --tests TestSomme

n'éxecute que les 4 cas de tests contenus dans le fichier `TestSomme.Kt` et concernant les tests de la méthode `somme(...)`.