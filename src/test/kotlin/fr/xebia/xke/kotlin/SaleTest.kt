package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 14/04/13
 * Time: 01:15
 * To change this template use File | Settings | File Templates.
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