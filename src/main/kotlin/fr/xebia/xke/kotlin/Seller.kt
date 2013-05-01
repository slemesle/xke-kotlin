package fr.xebia.xke.kotlin

/**
 * TODO Exercice 3 - implementez cette classe
 */

public class Seller(val boutique : String, val name : String, amount : Float = 0.0 ) {

    var amount : Float = 0.0 // TODO le setter doit-etre privé

    {
        // TODO affecter amount au backingField
    }

    fun addAmount (amount : Float){
        this.amount += amount
    }

    fun toString () : String  = "Seller "

    fun equals (s : Any?) = false

}