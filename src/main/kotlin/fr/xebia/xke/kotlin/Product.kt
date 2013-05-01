package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 14/04/13
 * Time: 00:10
 * LMUSB|20|Lance-missile USB
 */

import java.util.StringTokenizer

public class Product (productLine : String) {

    val ref: String
    val prix : Float
    val desc : String
    var sellCount = 0
        private set (count){
            $sellCount = count
        }

    {
        if (!productLine.matches(".+\\|[0-9]+\\.?[0-9]{0,2}\\|.+"))
            throw FnagException("Invalid line format: '$productLine' expecting 'réf. produit|prix|description' ")

        val tok = StringTokenizer(productLine, "|")
        ref = tok.nextToken()
        prix =tok.nextToken().toFloat()
        desc = tok.nextToken()
    }

    fun addQuantity(count : Int){
        $sellCount += count
    }

    fun toString ()  = "Product {ref:$ref, prix:$prix, desc:$desc, sellCount:$sellCount}"

    fun equals (product : Any?) =
       if (product is Product)
        ref == product.ref && prix == product.prix && desc == product.desc && sellCount == product.sellCount
       else false

}