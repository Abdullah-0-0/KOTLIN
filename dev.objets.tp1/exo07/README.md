Comme on l'a vu précédemment, les commandes de compilation deviennent vite très compliqué dès  que l'on a une structure de projet réaliste.

En pratique, il est nécessaire d'utiliser un outil d'automatisation des tâches de compilation/exécution/production du logiciel, comme [gradle](https://fr.wikipedia.org/wiki/Gradle) pour simplifier ces tâches.


La structure d'un projet Kotlin compatible avec gradle, se complexifie avec plusieurs fichiers spécifiques à gradle, mais les commandes de compilation/exécution seront elles très simplifiées.

	exo07
	├── README.md
	├── build.gradle.kts
	├── gradle
	│   └── wrapper
	│       ├── gradle-wrapper.jar
	│       └── gradle-wrapper.properties
	├── gradle.properties
	├── gradlew
	├── gradlew.bat
	├── settings.gradle.kts
	└── src
	    ├── main
	    │   └── kotlin
	    │       ├── Calculette.kt
	    │       └── Exo7.kt
	    └── test
	        └── kotlin
	            └── TestCalculette.kt


> Il n'est pas nécessaire pour le moment de comprendre le contenu des fichiers gradle.


#### compilation

Pour compiler les fichiers sources présents dans `src/main/kotlin/` : 

	./gradlew classes


> Lors de la première compilation de, Gradle va donwloader certains fichiers, etc. 

	Downloading https://services.gradle.org/distributions/gradle-8.4-bin.zip
	............10%............20%.............30%............40%.............50%............60%......
	......70%............80%.............90%............100%


Les exécutions suivantes seront plus rapide, Gradle ne recompilant que ce qui est nécessaire :

	BUILD SUCCESSFUL in 442ms
	2 actionable tasks: 1 executed, 1 from cache
	
Un dossier` build/` est créé contenant les fichiers compilés, entre autre.	


#### exécution
	
Pour exécuter la méthode main dans `Exo7.kt` : 

	./gradlew run
	
	
#### compilation des cas de tests	


Pour compiler les fichiers de tests présents dans `src/test/kotlin/` : 

	./gradlew testClasses
	
	
#### exécution des cas de tests 	

Pour exécuter les cas de tests :

 	./gradlew test


a comme résultat :

	TestCalculette > 2 x 3 == 6() FAILED
		org.opentest4j.AssertionFailedError at TestCalculette.kt:28

	5 tests completed, 1 failed

	FAILURE: Build failed with an exception.

C'est normal ; corrigez le cas de tests en erreur. Re-tester.
 	
Notez que dans `build/` plusieurs dossiers sont apparus contenant les résultats des cas de tests dans différents formats.
 	 	

#### ménage

Pour faire le ménage dans les fichiers compilés : 

	./gradlew clean

