Reprenons l'exercice prédécent, mais avec une architecture de projet un peu plus compiquée que précédemment :

	exo06
	├── README.md
	├── lib
	│   └── junit-platform-console-standalone-1.8.2.jar
	└── src
	    ├── main
	    │   ├── Calculette.kt
	    │   └── Exo6.kt
	    └── test
	        └── TestCalculette.kt


Le dossier `src/` contient maintenant deux sous-dossiers `main/` et `test/` contenant respectivement, le code du projet et des cas de tests.

Le dossier `lib/` contient une librairie `junit-platform-console-standalone-1.8.2.jar` qui sera nécessaire pour compiler et exécuter des cas de tests

> Vous rappelerez de [Junit](https://fr.wikipedia.org/wiki/JUnit) plus tard dans le semestre à l'occasion d'une ressource dédiée à la conception et au codage de tests. Pour le moment, nous serons simplement utilisateur.




Les commandes de compilation deviennent plus complexes.


Pour compiler le code du projet :

	kotlinc src/main/*.kt -include-runtime -cp build -d build/exo6.jar

Pour compiler les cas de tests : 

	kotlinc src/test/*.kt -cp build:build/exo6.jar:lib/junit-platform-console-standalone-1.8.2.jar -d build/

Pour exécuter le code du projet

	  java -jar build/exo6.jar

Pour exécuter les cas de tests : 

	 java -jar lib/junit-platform-console-standalone-1.8.2.jar --class-path build:build/exo6.jar --select-class TestCalculette

L'un des cas de tests est en erreur. Corrigez le est relancer la compilation/exécution.


> Les commandes deviennent très vite compliquées, et encore, on n'a que 2 fichiers de source et un fichier contenant des cas de tests.
