package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 01/05/13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test


class SellerTest {


    Test
    fun create_seller_with_default_amount_should_return_amount_0(){
        val seller = Seller("Paris", "Alice")
        assert(seller.amount == 0.0.toFloat(), "Default amount for seller should be 0.0")
        assert(seller.boutique == "Paris", "Boutique should be Paris")
        assert(seller.name == "Alice", "Name should be Alice")
    }


    Test
    fun create_seller_with_provided_amount_should_return_amount(){
        val seller = Seller("Paris", "Alice", 42.0)
        assert(seller.amount == 42.0.toFloat(), "Amount for seller should be 42.0")
        assert(seller.boutique == "Paris", "Boutique should be Paris")
        assert(seller.name == "Alice", "Name should be Alice")
    }

    Test
    fun seller_should_override_toString(){
        val seller = Seller("Paris", "Alice", 42.0)
        assert("$seller" == "Seller {boutique:Paris, name:Alice, amount:42.0}", "toString on seller should return:'Seller { ... }")
    }

    Test
    fun addAmount_should_sum_amount(){
        val seller = Seller("Paris", "Alice", 42.0)
        seller addAmount 10.0
        assert(seller.amount == 52.0.toFloat(), "Amount 42.0 + 10.0 == 52.0")
    }


    Test
    fun two_same_seller_should_be_equal(){
        val s1 = Seller("Paris", "Alice")
        val s2 = Seller("Paris", "Alice")
        assert (s1 == s2, "$s1 and $s2 should be equal")
    }

    Test
    fun two_seller_with_different_name_should_not_be_equal(){
        val s1 = Seller("Paris", "Joe")
        val s2 = Seller("Paris", "Alice")
        assert (s1 != s2, "$s1 and $s2 should not be equal")
    }

    Test
    fun two_seller_with_different_boutique_should_not_be_equal(){
        val s1 = Seller("Marseille", "Alice")
        val s2 = Seller("Paris", "Alice")
        assert (s1 != s2, "$s1 and $s2 should not be equal")
    }

    Test
    fun two_seller_with_different_amount_should_not_be_equal(){
        val s1 = Seller("Marseille", "Alice")
        val s2 = Seller("Marseille", "Alice", 42.0)
        assert (s1 != s2, "$s1 and $s2 should not be equal")
    }


}