package fr.xebia.xke.kotlin.web


import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import fr.xebia.xke.kotlin.Product
import fr.xebia.xke.kotlin.Seller
import org.springframework.http.ResponseEntity
import fr.xebia.xke.kotlin.SalesProcessor
import org.springframework.http.HttpStatus

Controller
RequestMapping(array("/rest/*"))
public class FnagController {


    RequestMapping(value = array("/process"), method = array(RequestMethod.POST))
    fun process(RequestBody lines : List<String>) : ResponseEntity<FnagStats> {
        val processor = SalesProcessor(lines= lines)
        return ResponseEntity(FnagStats(processor.getTopProducts(), processor.getTopSeller()), HttpStatus.OK)
    }


}

public class FnagStats(val topProducts : List<Product>, val topSellers : List<Seller>) {}