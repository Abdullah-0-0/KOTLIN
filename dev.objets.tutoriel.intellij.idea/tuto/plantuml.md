# Installer des plugins : exemple de PlantUML

Ouvrez le menu `Preferences >` et choisissez la catégorie `Plugins`

![](img/plantuml_market.png)

Dans le `Marketplace`, recherchez le plugin "*plantuml4idea*" et installez le 
(le nom du pluign à changer récemment ; page web du [plugin](https://plugins.jetbrains.com/plugin/7017-plantuml4idea)). 

**NB : il faudra redémarrer IntelliJ.**

![](img/plantuml_plantuml.png)



> PlantUML est un outil mode texte, de modélisation de diagrammes UML, en particulier les diagrammes de classes. Le "dessin" du diagramme est automatiquement généré par placement semi-automatiquement des différens éléments graphiques : [https://plantuml.com](https://plantuml.com/fr-dark/)

Ajoutez dans IntelliJ un nouveau fichier avec comme extension `XXX.plantuml`, puis commencez à l'éditer ; la documentation concernant l'édition d'un diagramme de classes est disponible [ici](https://plantuml.com/fr-dark/class-diagram) :

	@startuml
	
	Class HelloWorld
	
	@enduml
	
![](img/plantuml_edit.png)



D'autres plugins comme **SpotBugs** peuvent vous être utiles. A vous de chercher les plugins qui pourraient vous servir.


[corriger des erreurs possible dans les dépendances ](tuto/dependancies.md)
