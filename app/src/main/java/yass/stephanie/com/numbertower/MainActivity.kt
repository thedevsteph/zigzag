package yass.stephanie.com.numbertower

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import yass.stephanie.com.numbertower.CalculatorUtils.NumberGenerator


class MainActivity : AppCompatActivity() {

    companion object {
        private val numberGenerator = NumberGenerator()
        private var squareNumbers: ArrayList<Int> = numberGenerator.generateEasyNumbers()
        lateinit var generatedNumbers: ArrayList<Int>
        private lateinit var viewModel: MainViewModel
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val plusSquare: TextView = findViewById(R.id.plus_operation_square)
        val minusSquare: TextView = findViewById(R.id.minus_operation_square)
        val divideSquare: TextView = findViewById(R.id.divide_operation_square)
        val multiplySquare: TextView = findViewById(R.id.multiply_operation_square)


        val targetNumber: TextView = findViewById(R.id.target_number)
        val firstSquare: TextView = findViewById(R.id.square1number)
        val secondSquare: TextView = findViewById(R.id.square2number)
        val thirdSquare: TextView = findViewById(R.id.square3number)
        val fourthSquare: TextView = findViewById(R.id.square4number)
        val fifthSquare: TextView = findViewById(R.id.square5number)

        val squareArray: ArrayList<TextView> =
            arrayListOf(firstSquare, secondSquare, thirdSquare, fourthSquare, fifthSquare)
        updateSquares(squareArray)
        generateTargetNumber(targetNumber)

        val operationsArray: ArrayList<TextView> =
            arrayListOf(plusSquare, minusSquare, divideSquare, multiplySquare)
        applyOperationsListener(operationsArray)


    }

    private fun applyOperationsListener(operationsArray: ArrayList<TextView>) {
        for (operationSquare in operationsArray) {
            operationSquare.setOnTouchListener(listener)
            operationSquare.bringToFront()
        }

    }

    private fun updateSquares(squares: ArrayList<TextView>) {
        generatedNumbers = viewModel.getGeneratedNumbers()
        for (i in generatedNumbers.indices) {
            squares[i].apply {
                text = generatedNumbers[i].toString()
                setOnTouchListener(listener)
                bringToFront()
            }
        }
    }

    private fun generateTargetNumber(textView: TextView) {
        textView.text = viewModel.generateTargetNumber(generatedNumbers).toString()
    }

    private var listener = View.OnTouchListener { view: View, motionEvent: MotionEvent ->

        when (motionEvent.action) {
            MotionEvent.ACTION_MOVE -> {
                view.y = motionEvent.rawY - view.height / 2
                view.x = motionEvent.rawX - view.width * 0.75F

                true
            }
        }
        true
    }
}
