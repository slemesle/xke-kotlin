package fr.xebia.xke.kotlin

import org.junit.Test;
import java.util.ArrayList

/**
 * TODO Exercice 4 Faites passer ces tests
 */


class SalesProcessorTest {

    Test
    fun should_give_expected_result_by_file() {
        val file = this.javaClass.getResourceAsStream("/sells-log.txt")
        if (file != null){
            val processor = SalesProcessor(file)
            val products = processor.getTopProducts()
            assert(products.size == 1, "Top products size should be 1")
            assert(products.first().ref == "T127")
            assert(products.first().desc == "T-shirt 'no place like 127.0.0.1'")
            assert(products.first().sellCount == 4)

            val sellers = processor.getTopSeller()
            assert(sellers.size == 1)
            assert(sellers.first().name == "Alice")
            assert(sellers.first().boutique == "Lyon")
            assert(sellers.first().amount == 229.98.toFloat())
        } else
            assert(false, "Failed to retrieve sells-log.txt file")
    }

    Test
    fun should_give_expected_result_by_list() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Lance-missile USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "5",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)

        val products = processor.getTopProducts()
        assert(products.size == 1, "Top products size should be 1")
        assert(products.first().ref == "T127")
        assert(products.first().desc == "T-shirt 'no place like 127.0.0.1'")
        assert(products.first().sellCount == 4)

        val sellers = processor.getTopSeller()
        assert(sellers.size == 1)
        assert(sellers.first().name == "Alice")
        assert(sellers.first().boutique == "Lyon")
        assert(sellers.first().amount == 229.98.toFloat())

    }


    Test
    fun should_return_multiple_top_products_when_needed() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Clé USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "2",
                "Paris|Bob|LMUSB|1",
                "Paris|Bob|MKB|1")

        val processor = SalesProcessor(lines = lines)

        val products = processor.getTopProducts()
        assert(products.size == 2, "Top products size should be 2")

        var product = Product("LMUSB|20|Clé USB")
        product addQuantity 1

        assert (product in products, "Le produit $product doit-être dans le top : $products")

        product = Product("MKB|200|Clavier mécanique")
        product addQuantity 1
        assert (product in products, "Le produit $product doit-être dans le top : $products")
    }

    Test
    fun should_return_multiple_top_seller_when_needed() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Clé USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "3",
                "Paris|Bob|LMUSB|1",
                "Paris|Kenny|LMUSB|1",
                "Marseille|Jean|T127|1")

        val processor = SalesProcessor(lines = lines)

        val sellers = processor.getTopSeller()
        assert(sellers.size == 2, "Top sellers size should be 2")

        var seller = Seller("Paris", "Bob", 20.0)

        assert (seller in sellers, "Le vendeur $seller doit-être dans le top : $sellers")

        seller = Seller("Paris", "Kenny", 20.0)
        assert (seller in sellers, "Le vendeur $seller doit-être dans le top : $sellers")
    }

    Test
    fun should_distinguish_seller_based_on_name_and_city() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Clé USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "3",
                "Paris|Bob|LMUSB|1",
                "Marseille|Bob|LMUSB|1",
                "Marseille|Jean|T127|1")

        val processor = SalesProcessor(lines = lines)

        val sellers = processor.getTopSeller()
        assert(sellers.size == 2, "Top sellers size should be 2")

        var seller = Seller("Paris", "Bob", 20.0)

        assert (seller in sellers, "Le vendeur $seller doit-être dans le top : $sellers")

        seller = Seller("Marseille", "Bob", 20.0)
        assert (seller in sellers, "Le vendeur $seller doit-être dans le top : $sellers")
    }


    Test(expected = javaClass<NumberFormatException>())
    fun should_fail_on_product_count_lesser_than_real() {
        val lines = arrayListOf<String>(
                "2", // There is 3 products in list and not 2 :p
                "LMUSB|20|Lance-missile USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "5",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad sale count line")
    }

    Test(expected = javaClass<NumberFormatException>())
    fun should_fail_on_product_count_bad_format() {
        val lines = arrayListOf<String>("x", "LMUSB|20|Lance-missile USB")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad product count line")
    }

    Test(expected = javaClass<FnagException>())
    fun should_fail_on_product_count_more_than_real() {
        val lines = arrayListOf<String>(
                "4", // There is 3 products and not the 4 expected
                "LMUSB|20|Lance-missile USB",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "5",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad product line")
    }

    Test(expected = javaClass<FnagException>())
    fun should_fail_on_bad_product_line_format() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20z|Bad product format|", // Price 20z is not a float and there is a |
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "5",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad product line")
    }

    Test(expected = javaClass<FnagException>())
    fun should_fail_on_sale_count_more_than_real() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Bad product format",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "6",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad line count")
    }

    Test(expected = javaClass<FnagException>())
    fun should_fail_on_sale_count_less_than_real() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Bad product format",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "4",
                "Paris|Bob|LMUSB|1",
                "Lyon|Alice|MKB|1",
                "Lyon|Alice|T127|2",
                "Paris|Bob|T127|1",
                "Paris|Chuck|T127|1")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad line count")
    }

    Test(expected = javaClass<FnagException>())
    fun should_fail_on_bad_sale_line_format() {
        val lines = arrayListOf<String>(
                "3",
                "LMUSB|20|Bad product format",
                "MKB|200|Clavier mécanique",
                "T127|14.99|T-shirt 'no place like 127.0.0.1'",
                "1",
                "Paris|Bob|LMUSB|1s")

        val processor = SalesProcessor(lines = lines)
        assert(false, "Should indicate bad sale line format")
    }


}