# Exercice 2


### Travail à faire

![](uml/galerie.png)

Le diagramme UML précédent illustre l'énoncé vu en TD précédemment.

> Notez toutes les précisions apportées par rapport à la correction du TD papier ; ici, on a un diagramme de **conception détaillée**, où tous les choix d'implémentation ont été fait.
> 
> **Ex :** Les cardinalités UML `0..*` ou `1..*` seront 
> traduites en Kotlin par des ` NutArray<>` avec une taille abitraire de 10.
> On a donc ajouté des compteurs comme attributs, afin
> de se positionner "facilement" dans les tableaux.


1. Commencez par implémenter le diagramme UML, sans vous occuper du corps des méthodes. Validez votre traduction via les cas de tests fournis dans `test/TestUmlXXX.kt`.

2. Une fois la traduction COMPLETEMENT **correcte**, implémentez le corps des méthodes.

3. Dans la fonction `main()`  donnez un scénario, définissant des oeuvres, des personnes, une galerie, etc. et utilisez toutes les méthodes possible afin de vérifier que toutes les méthodes implémentées se comportent comme prévu ; *un début de code vous est fourni en commentaires (auteur: un étudiant de l'année dernière dont je n'ai pas noté le nom, toutes les excuses).*

