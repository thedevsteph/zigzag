package yass.stephanie.com.numbertower.CalculatorUtils

class Solver(startingNumber: Int = 0) {


    var total: Int = startingNumber


    fun add(number: Int) {
        total += number
    }

    fun minus(number: Int) {
        total -= number
    }

    fun divide(number: Int) {
        total /= number
    }


    fun multiply(number: Int) {
        total *= number
    }

    fun resetTotal() {
        total = 0
    }

}