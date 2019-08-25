package yass.stephanie.com.numbertower.CalculatorUtils

class NumberGenerator {

    companion object {
        private val largeNumberArray: ArrayList<Int> = arrayListOf(100, 200, 50)
        private val mediumNumberArray: ArrayList<Int> = arrayListOf(10, 20, 50)
        private val smallNumberArray: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5)
    }

    private fun getRandomLargeNumber(): Int = largeNumberArray.shuffled()[0]
    private fun getRandomMediumNumber(): Int = mediumNumberArray.shuffled()[0]
    private fun getRandomSmallNumber(): Int = smallNumberArray.shuffled()[0]

    fun generateEasyNumbers(): ArrayList<Int> {
        return arrayListOf(
            getRandomLargeNumber(),
            getRandomLargeNumber(),
            getRandomLargeNumber(),
            getRandomMediumNumber(),
            getRandomSmallNumber()
        )
    }

    private fun removeAndShuffle(array: ArrayList<Int>, position: Int): ArrayList<Int> {
        array.apply {
            removeAt(position)
            shuffled()
        }
        return array
    }

    private fun getNewPosition(numbersArray: ArrayList<Int>): Int {
        return if (numbersArray.size != 1) {
            (0 until numbersArray.size - 1).shuffled().first()
        } else {
            0
        }
    }

    fun getTargetNumber(numbersArray: ArrayList<Int>): Int {
        var dynamicArray = numbersArray

        var position: Int = (0 until numbersArray.size - 1).shuffled().first()
        var solver = Solver()

        fun updateNumbersArrayAndPosition() {
            dynamicArray = removeAndShuffle(dynamicArray, position)
            position = getNewPosition(dynamicArray)
        }
        solver.total = dynamicArray[position]
        updateNumbersArrayAndPosition()

        solver.divide(dynamicArray[position])
        updateNumbersArrayAndPosition()

        solver.add(dynamicArray[position])
        updateNumbersArrayAndPosition()

        solver.minus(dynamicArray[position])
        updateNumbersArrayAndPosition()

        solver.multiply(dynamicArray[position])

        return solver.total
    }

}