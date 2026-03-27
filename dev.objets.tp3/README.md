# TP3 > d'UML vers Kotlin

## diagrammes de classes vers code Kotlin


Il va s'agir d'implémenter les diagrammes de classes UML proposés.

Attention à bien respecter __SCRUPULEUSEMENT__ le nom des classes, des méthodes, des paramètres, etc. sinon, les tests ne compileront/s'exécuteront pas correctement. 


### Rappels Gradle

Pour compiler les sources Kotlin

	./gradlew classes

Pour exécuter (si nécessaire) la classe exécutable `MainKt`

	./gradlew run
	
Pour compiler les cas de tests fournis

	./gradlew testClasses		
	
Pour exécuter tous les cas de tests fournis

	./gradlew test	
	
> les détails de l'exécution des cas de test est consultable de manière détaillée dans [build/reports/tests/test/index.html](build/reports/tests/test/index.html)	
	
Pour exécuter une "classe" de cas de tests spécifique

	./gradlew test --tests TestXXXX

