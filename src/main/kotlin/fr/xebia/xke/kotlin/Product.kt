package fr.xebia.xke.kotlin

/**
 * TODO : Exercice 2 le format a parser est :
 * LMUSB|20|Lance-missile USB
 *
 * Commencez par parser la chaine puis implementez les methodes necessaires
 */

import java.util.StringTokenizer

public class Product (productLine : String) {

    val ref: String = ""
    val prix : Float = 0.0
    val desc : String = ""
    var sellCount = 0 // TODO : sellCount ne doit pas etre modifiable en dehors de la classe Ajoutez un setter privé
        private set (count){
            $sellCount = count
        }

    {
        // TODO Exercice 2 - 1 : Parser la ligne format: ref|prix|desc
    }

    fun addQuantity(count : Int){
        // TODO modifiez le backingField en ajoutant count a sellCount (IE $sellCount)
    }

     // TODO definir une methode expression toString
    fun toString ()  = "Product"

    // TODO implementer l'operateur ==
    fun equals (product : Any?) = false

}