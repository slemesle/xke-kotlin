package fr.xebia.xke.kotlin

/**
 * TODO - Exercice 1 Faire passer les tests SaleTest en implementant la classe Sale
 */


import org.junit.Test

class SaleTest {

    Test
    fun should_parse_sale() {
        val sale = Sale("Lyon|Alice|T127|2")
        assert(sale.boutique == "Lyon")
        assert(sale.seller == "Alice")
        assert(sale.ref == "T127")
        assert(sale.quantity == 2)
    }

    Test(expected = javaClass<FnagException>())
    fun should_validate_format(){
        Sale("ded|dee|dede|dede|dede")
    }
}