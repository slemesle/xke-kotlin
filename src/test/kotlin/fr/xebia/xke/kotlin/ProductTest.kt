package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 14/04/13
 * Time: 00:50
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test

class ProductTest {

    Test
    fun should_parse_productline(){
        val product = Product("LMUSB|20|Lance-missile USB")

        assert(product.ref == "LMUSB")
        assert(product.prix == 20.toFloat())
        assert(product.desc == "Lance-missile USB")
    }

    Test(expected = javaClass<RuntimeException>())
    fun should_validate_format(){
        Product("LMUSB|20Lance-missile USB")
    }

    Test
    fun should_increment_sellcount_on_addQuantity(){
        val product = Product("LMUSB|20|Lance-missile USB")
        assert(product.sellCount == 0)

        product addQuantity 1
        assert(product.sellCount == 1)
    }


    Test
    fun two_same_products_should_be_equal(){
        val p1 = Product("LMUSB|20|Lance-missile USB")
        val p2 = Product("LMUSB|20|Lance-missile USB")
        assert (p1 == p2, "$p1 and $p1 should be equals")
    }

    Test
    fun two_products_with_different_amount_should_not_be_equal(){
        val p1 = Product("LMUSB|10|Lance-missile USB")
        val p2 = Product("LMUSB|20|Lance-missile USB")
        assert (p1 != p2, "$p1 and $p1 should not be equals")
    }

    Test
    fun two_products_with_different_ref_should_not_be_equal(){
        val p1 = Product("LMUSB|10|Lance-missile USB")
        val p2 = Product("LMUSX|10|Lance-missile USB")
        assert (p1 != p2, "$p1 and $p1 should not be equals")
    }

    Test
    fun two_products_with_different_desc_should_not_be_equal(){
        val p1 = Product("LMUSB|10|Lance-missile USB")
        val p2 = Product("LMUSB|10|Lance-torpille USB")
        assert (p1 != p2, "$p1 and $p1 should not be equals")
    }

}