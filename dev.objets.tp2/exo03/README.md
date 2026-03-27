# Manipulation de String

Vous travaillerez dans `src/UtiliseString.kt`.


Implémentez le "scénario" suivant en utilisant les méthodes de `String` vues dans le document de cours [ci-joint](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/dev.objets.ressources/-/blob/main/CMs/03-kotlin-string.pdf?ref_type=heads): 

**ATTENTION : la version de kotlin installée dans les salles TPs 1.3) est ancienne et n'est pas complètment compatible avec les informations présentées dans le cours indiqué : ex `String.uppercase()` <-> `String.toUpperCase()`**


- Créer une `String` initialisée à `"anticonstitutionnellement"`.

- Quelle est la longueur de cette `String` ? (faire afficher le résultat).

- Afficher le troisième caractère, le 5ième caractère et le 20ième caractère.

- Afficher ligne à ligne chaque caractère dans l'ordre inverse.

- Extraire la sous-chaîne allant du deuxième caractère au quatrième, l'afficher.

- Afficher la première et la dernière position du caractère `'n'` dans la `String` de départ.

- Que se passe-t-il, si on demande la position d'un caractère non présent dans la chaîne ?

- Créer un nouvelle `String` initialisée à bonjour.

- Concaténer `" tout"` puis `" le monde"`, faire afficher la `String` résultat.

- Tirer 25 fois de suite aléatoirement un caractère parmi ceux de la `String` de départ; contaténez ces caractères. A la fin, afficher la chaine obtenue.

- Passer la `String` du départ en majuscule, et stocker le dans une nouvelle variable.

- Passer la `String` obtenue en minuscule, et stocker la dans une nouvelle variable.

- Comparer la `String` de départ ainsi que les deux `String` obtenues à l'aide des méthodes `equals()`, `equals(ignoreCase=true)` et `compareTo()` et des opérateurs `==`, `===`, `<=` et `>=`.
