
# XKE Kotlin

Réalisation de l'exercice Fnag !

## Sujet

La FNAG est une chaîne de magasins spécialisés dans les articles pour geeks et geekettes. Chaque mois, les boutiques communiquent leurs résultats, qui sont agrégés dans un fichier global. Celui-ci commence par un inventaire :

la première ligne contient un entier N indiquant le nombre de produits.
les N lignes suivantes sont des enregistrements au format :
réf. produit|prix|description
Suivent les résultats des ventes :

la première ligne contient un entier M indiquant le nombre de ventes.
les M lignes suivantes sont des enregistrements au format :
boutique|vendeur|réf. produit|quantité vendue
Les prix comportent au maximum 2 chiffres après la virgule. Le séparateur décimal est le point. Tous les autres champs sont alphanumériques, ils ne contiennent pas de barre verticale. Un vendeur ne travaille que dans une seule boutique.

## Objectif
Vous êtes chargé(e) d'analyser le fichier pour en extraire des statistiques :

le produit le plus vendu, avec son nombre de ventes. Il sera affiché au format :
TOPSALE|réf. produit|description|nombre de ventes
le meilleur vendeur, avec le montant total de ses ventes. Il sera affiché au format :
TOPSELLER|boutique|vendeur|total des ventes
En cas d'ex-aequo, tous les enregistrements à égalité seront affichés (l'ordre n'a pas d'importance).

### Exemple

Données en entrée :

    3
    LMUSB|20|Lance-missile USB
    MKB|200|Clavier mécanique
    T127|14.99|T-shirt 'no place like 127.0.0.1'
    5
    Paris|Bob|LMUSB|1
    Lyon|Alice|MKB|1
    Lyon|Alice|T127|2
    Paris|Bob|T127|1
    Paris|Chuck|T127|1

Résultat attendu :

    TOPSALE|T127|T-shirt 'no place like 127.0.0.1'|4
    TOPSELLER|Lyon|Alice|229.98


### Hypothèses / contraintes

N < 100, M < 1000.
le programme doit s'exécuter sur une JVM 1.6 ou supérieure.
les données en entrée peuvent être injectées sous une autre forme qu'un fichier (par exemple un test automatisé).

# Exercices :

Faites passer les tests du projet dans l'ordre des TODO fournit par Exercice.

Bonus :

Créer une fonction d'extension sur la classe String pour determiner si c'est un palindrome :).

## Liens utiles

+ [Kotlin Web Demo](http://kotlin-demo.jetbrains.com/)
+ [Kotlin Docs Home](http://confluence.jetbrains.com/display/Kotlin/Welcome)
+ [Kotlin Doc API](http://jetbrains.github.io/kotlin/versions/snapshot/apidocs/index.html)