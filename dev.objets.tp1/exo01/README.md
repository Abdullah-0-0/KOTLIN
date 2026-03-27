Pour compiler  des fichiers source Kotlin nous allons utiliser le 
_compilateur Kotlin en ligne de commande_ accessible dans un terminal via la commande `kotlinc`.

1. Compilez le fichier source `Hello.kt` présent dans 
`src` : 

		kotlinc src/Hello.kt -d build


	i) on appelle le compilateur Kotlin `kotlinc`, ii) on lui demande de compiler le fichier `Hello.kt`présent dans le dossier `src/` et iii) on demande que le résultat de la compilation soit placé dans `build` (option `-d` pour `destination`)
	
	_Sur les postes de l'IUT un message similaire à celui-ci va peut-être apparaitre :_

		OpenJDK 64-Bit Server VM warning: Options -Xverify:none and -noverify were deprecated in JDK 13 and will likely be removed in a future release.


	**Ce n'est pas une erreur ; ce n'est qu'un message informatif : n'y prêter attention.**

2. Vérifiez dans `build` ce qui est apparut :

		ls -l build/` par exemple


3. Exécutez le fichier Bytecode `HelloKt.class` compilé précédemment :

		kotlin -cp build HelloKt

	i) la commande `kotlin` lance la machine virtuelle Java (avec quelques options spécifiques à Kotlin) ; ii) on lui indique qu'il y a dans `build/` des fichiers `.class`  à utiliser (option `-cp` pour `class-path`) et iii) on lui demande d'essayer d'exécuter
le fichier compilé `HelloKt.class` ; ATTENTION, il faut **ommettre** l'extension `.class`.

> pour avoir plus d'information à propos des commandes `kotlinc` ou `kotlin`, n'hésitez pas à les appeler dans un terminal avec l'option `-help` 
