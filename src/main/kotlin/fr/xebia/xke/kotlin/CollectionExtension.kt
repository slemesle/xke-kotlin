package fr.xebia.xke.kotlin

import java.util.ArrayList
import java.util.Comparator

/**
 * Extensions des collections pour le programme Fnag
 */

/**
 * Filter a sorted collection by retaining only values >= to first item
 * f is a function expression returning the value used in tests
 */
public inline fun  <T, R: Comparable<R>> Collection<T>.retainMaxOf(f: (T) -> R) : List<T>{
    if(notEmpty()){
        val value = f(this.first())
        return filter { value <= f(it) }
    }
    return ArrayList()
}

/**
 * Copies all elements into a [[List]] and sorts it by descending value of compare_function(element)
 * E.g. arrayList("two" to 2, "one" to 1).sortBy({it._2}) returns list sorted by second element of tuple
 */
public inline fun <T, R: Comparable<R>> Iterable<T>.sortDescendingBy(f: (T) -> R) : List<T> {
    val sortedList = toCollection(ArrayList<T>())
    val sortBy: Comparator<T> = comparator<T> {(x: T, y: T) ->
        val xr = f(x)
        val yr = f(y)
        yr compareTo xr
    }
    java.util.Collections.sort(sortedList, sortBy)
    return sortedList
}



public fun String.isPalindrome(): Boolean {

    val mid = this.length / 2
    var length = this.length
    if (length >= 2)
        for (i in 0..mid){
            if (this.charAt(i) != this.charAt(length -i -1)){
                return false
            }
        }
    return true
}


