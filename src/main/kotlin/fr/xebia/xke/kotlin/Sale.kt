package fr.xebia.xke.kotlin

/**
 *
 * boutique|vendeur|réf. produit|quantité vendue
 */


import java.util.StringTokenizer

public class Sale (line : String) {
    val boutique: String
    val seller: String
    val ref: String
    val quantity: Int

    {
        if (!line.matches(".+\\|.+\\|.+\\|[0-9]+"))
            throw FnagException("Invalid sale line format: '$line' expecting 'boutique|vendeur|réf. produit|quantité vendue' ")

        val tok = StringTokenizer(line, "|")
        boutique = tok.nextToken()
        seller =tok.nextToken()
        ref = tok.nextToken()
        quantity = tok.nextToken().toInt()
    }

}