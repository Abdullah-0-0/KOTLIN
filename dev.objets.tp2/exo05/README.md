## Chiffrement 

Implémentez la méthode `chiffrement(original : String, decalage : Int) : String` 

qui réalise un chiffrement de la chaine de caractère en réalisant un simple décalage : cette méthode
de chiffrement "très simple" est également connue sous le nom de
["chiffre de César"](https://fr.wikipedia.org/wiki/Chiffrement\_par\_d%C3%A9calage)

Quelques indications :

- on commencera par passer la chaine originale en majuscule
- vous pouvez utiliser un tableau de caractères représentant l'alphabet
    - cela permet d'avoir rapidement la lettre correspondant à une position, 
    - cela permet aussi de déterminer la position d'une lettre
    - tips: insérer un caractère vide dans la premiere case du tableau peut faciliter la vie
- construisez la chaine chiffrée par concaténation sucessive

Testez votre implémentation en utilisant les cas de tests fournis.


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

