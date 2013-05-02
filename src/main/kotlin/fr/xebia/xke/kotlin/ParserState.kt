package fr.xebia.xke.kotlin

/**
 *
 * Handles parser line state
 */

public enum class ParserState {
    PRODUCT_COUNT{
        override fun next() = PRODUCT_LINE
    }
    PRODUCT_LINE {
        override fun next() = SALES_COUNT
    }
    SALES_COUNT {
        override fun next() = SALE_LINE
    }
    SALE_LINE {
        override fun next() = EOF
    }
    EOF {
        override fun next () = EOF
    }
    abstract fun next () : ParserState
}