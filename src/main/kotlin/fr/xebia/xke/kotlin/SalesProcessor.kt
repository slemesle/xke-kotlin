package fr.xebia.xke.kotlin

/**
 * Created with IntelliJ IDEA.
 * User: slm
 * Date: 14/04/13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
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
        var ctx : ParsingContext = ParsingContext()

        fun processLines(it: String){

            when (ctx.state){

                ParserState.PRODUCT_COUNT -> {
                        tryLogAndThrow({ctx.productLine = it.toInt()}, "Bad file format on line ${ctx.lineCount} : was '$it' expecting [0-9]+ (product count)")
                        ctx.next()
                }

                ParserState.PRODUCT_LINE -> {
                    tryLogAndThrow({ putProduct(it) }, "Bad file format on line ${ctx.lineCount} expecting Product line")
                    ctx.dec()
                }

                ParserState.SALES_COUNT -> {
                    tryLogAndThrow({ctx.salesLine = it.toInt()},"Bad file format on line ${ctx.lineCount} : was '$it' expecting [0-9]+ (sales count)")
                    ctx.next()
                }

                ParserState.SALE_LINE -> {
                    tryLogAndThrow({ putSale(it) }, "Bad file format on line ${ctx.lineCount} expecting Sale line")
                    ctx.dec()
                }

                else -> {
                    log?.error("Bad line count ${ctx.lineCount} is more than expected")
                    throw FnagException("Bad line count ${ctx.lineCount} is more than expected")
                }
            }
            ctx.lineCount++
        }

        if (stream != null){
            stream.reader().forEachLine { processLines(it) }
        } else {
            lines forEach { processLines(it) }
        }

        if (ctx.countDown > 0) {
            log?.error("bad line count waiting for ${ctx.countDown} missing lines while reading ${ctx.state} we've read ${ctx.lineCount} lines")
            throw FnagException("Bad line count missing  ${ctx.countDown} lines")
        }

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
        val seller  = sellers.getOrPut("${s.seller}-${s.boutique}"){ Seller(s.boutique, s.seller) }
        val product = products get s.ref

        if (product != null){
            seller addAmount product.prix * s.quantity
            product addQuantity s.quantity
        }
    }

    public fun getTopProducts(): List<Product> = products.values() sortDescendingBy { it.sellCount } retainMaxOf { it.sellCount }

    public fun printTopProduct() {
        // Trie les produit par ordre décroissant de sellCount et retient les max sur sellCount
       val topSales = getTopProducts()
       topSales forEach { log?.info("TOPSALE|${it.ref}|${it.desc}|${it.sellCount}")}
    }

    public fun getTopSeller() : List<Seller> = sellers.values() sortDescendingBy { it.amount } retainMaxOf { it.amount }

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


    class ParsingContext(){
        var countDown : Int = 0
            private set
        var lineCount : Int = 0
        var productLine : Int = 0
            set (p : Int ){
                $productLine = p
                $countDown = p
            }
        var salesLine : Int = 0
            set (p : Int ){
                $salesLine = p
                $countDown = p
            }
        var state : ParserState = ParserState.PRODUCT_COUNT

        fun next() {
            state = state.next()
        }

        fun dec (){
            $countDown--
            if ($countDown == 0){
                next()
            }
        }

    }

}



