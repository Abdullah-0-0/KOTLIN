#### Compilation d'un projets avec plusieurs fichiers

Compilez les sources kotlin fournis : 

	kotlinc src/Exo5.kt -d build

ne compile pas car il faut déjà avoir compiler `Calculette.kt`

	kotlinc src/Calculette.kt -d build

compile correctement, mais 

	kotlinc src/Exo5.kt -d build

ne compile toujours pas : il ne trouve pas les méthodes
`add()` et `mult()` utilisées  dans `Exo5.kt` mais déclarées dans `Calculette.kt` : **Il y a une dépendance entre `Exo5.kt` et `Calculette.kt`**

pour compiler correctement, il faut utiliser la ligne de commande suivante :

	kotlinc src/Exo5.kt -cp build -d build

Il faut avoir ajouté une nouvelle option `-cp` (pour `class-path`) afin d'indiquer au compilateur Koltin où trouver le fichier compilé `CalculetteKt.class` dont `Exo5.kt` a besoin.

On peut aussi compiler tout en une seule fois : 

	kotlinc src/*.kt -cp build -d build


On dit au compilateur kotlin de compiler tous les fichiers `.kt` qu'il trouve dans `src/` en utilisant les `.class` compilés qu'il va trouver dans `build/` et de les envoyer au fur et mesure dans `build/`. Notez également que le compilateur gère alors les dépendances, en compilant les fichiers dans le bon ordre pour lui.


### Exécution


On peut exécuter `Exo5Kt.class` : 

	kotlin -cp build Exo5Kt

Dans `build/` il y a maintenant deux fichiers `.class` ; imaginez ce que cela donnera quand on aura 10, 20... voir 100 fichiers Koltin à compiler. Difficile de retrouver quel fichier il faut exécuter et difficile à "distribuer" facilement ;-)


### Création / exécution d'un JAR

On va maintenant compiler tous les fichiers Kotlin pour 
les placer dans un fichier archive `.jar` : 

	kotlinc src/*.kt  -cp build -d build/exo5.jar

> un fichier `.jar` est en fait `.zip` contenant des des fichiers Bytcode `.class`. Vous pouvez le dézipper. Constatez qu'il contient des `.class`, ainsi qu'un fichier `MANIFEST` contenant l'indication du fichier à exécuter

On peut toujours exécuter le fichier `Exo5Kt.class` : 

	kotlin -cp build/exo5.jar Exo5Kt
	
On doit juste préciser  que l'archive `.jar` est dans le `class-path` pour que `Exo5Kt` soit trouvable.	
	
On peut aussi exécuter le .jar ainsi 

	kotlin build/exo5.jar	
	
Ici, on va regarder dans le `MANIFEST` quel fichier doit être exécuté
		

#### JAR compatible Java

Il peut être intéressant d'ajouter l'option `-include-runtime` ; cela ajouter des dépendances nécessaires à rendre le code compilé complètement compatible avec une machien virtuelle java standard.

	kotlinc src/*.kt  -include-runtime -cp build -d build/exo5-complete.jar

>  si vous dézipper le `.jar` vous verrez l'apparition d'un dossier `kotlin/` contenant toutes les librairies standard de Kotlin.

On peut maintenant exécuter l'archive `.jar` en utilisant une JRE standard : 

	java -jar build/exo5-complete.jar

