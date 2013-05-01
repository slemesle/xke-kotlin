package fr.xebia.xke.kotlin

/**
 * TODO - Exercice 4 : Implementer le processor
 */

import java.io.File
import java.util.HashMap
import java.util.Comparator
import java.util.ArrayList
import java.io.InputStream
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class SalesProcessor(stream : InputStream? = null , lines : List<String> = ArrayList()) {

    val log : Logger? = LoggerFactory.getLogger(javaClass<SalesProcessor>())

    val products : HashMap<String, Product> = HashMap();
    val sellers: HashMap<String, Seller> = HashMap();


    {
            // TODO Exercice avec stream.reader().forEachLine et lines.forEach,
            // Parsez les ligne et pour determiner les Top Products et Top Sellers

    }



    /**
     * Store a product from a string to products map
     */
    private fun putProduct(line : String){
        val p = Product(line)
        products.put(p.ref, p)
    }

    /**
     * Store a seller from a sale string to seller map
     * also maintain sold amount and product sold quantity
     */
    private fun putSale(line : String){
        val s = Sale(line)
        // TODO : Ajoutez ou mettez a jour le seller et le produit pour cette vente
    }

    // TODO  a l'aide des fonctions définies dans CollectionExtension.kt recuperez la liste des Top Product
    public fun getTopProducts(): List<Product> = arrayListOf()


    public fun printTopProduct() {
        // Trie les produit par ordre décroissant de sellCount et retient les max sur sellCount
       val topSales = getTopProducts()
       topSales forEach { log?.info("TOPSALE|${it.ref}|${it.desc}|${it.sellCount}")}
    }

    // TODO  a l'aide des fonctions définies dans CollectionExtension.kt recuperez la liste des Top Product
    public fun getTopSeller() : List<Seller> = arrayListOf()

    public fun printTopSeller() {
        // Trie les seller par ordre décroissant et retient les max de amount
        val topSales = getTopSeller()

        topSales forEach { log?.info("TOPSELLER|${it.boutique}|${it.name}|${it.amount}") }
    }

    /**
     *
     */
    public inline fun tryLogAndThrow(f: () -> Unit, message : String) {
        try {
            f()
        } catch(e: Exception) {
            log?.error("$message", e)
            throw e
        }
    }

}



