package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 14/04/13
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */

public class Seller(val boutique : String, val name : String, amount : Float = 0.0 ) {

    var amount : Float
        private set

    {
        $amount = amount
    }

    fun addAmount (amount : Float){
        this.amount += amount
    }

    fun toString () : String  = "Seller {boutique:$boutique, name:$name, amount:$amount}"

    fun equals (s : Any?) =
            if (s is Seller)
                boutique == s.boutique && name == s.name && amount == s.amount
            else false

}