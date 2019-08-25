package yass.stephanie.com.numbertower

import android.arch.lifecycle.ViewModel
import yass.stephanie.com.numbertower.CalculatorUtils.NumberGenerator

class MainViewModel : ViewModel() {


    companion object {
        var numberGenerator = NumberGenerator()
    }


    fun getGeneratedNumbers(): ArrayList<Int> {
        return numberGenerator.generateEasyNumbers()
    }


    fun generateTargetNumber(numbers: ArrayList<Int>): Int {
        return numberGenerator.getTargetNumber(getGeneratedNumbers())
    }

}