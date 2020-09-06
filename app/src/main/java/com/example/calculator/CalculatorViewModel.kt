package com.example.calculator

import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression
import java.lang.NumberFormatException

class CalculatorViewModel : ViewModel() {
    private lateinit var evalText: MutableList<String>
    private lateinit var displayText: MutableList<String>
    private lateinit var numberCreator: MutableList<String>
    private var symbolused:Boolean = false
    private lateinit var answer: String

    init {
        evalText.clear()
        displayText.clear()

    }

    private fun resetdisplay(){
        displayText.clear()
    }

    fun addNum(num:String){
        numberCreator.add(num)
        evalText.add(num)
        displayText.add(num)
        symbolused = false
    }

    private fun updateText(text: String){
        try {
            text.toFloat()
            numberCreator.add(text)
            displayText.add(text)
        } catch(a:NumberFormatException){
            when(text){
                "*" -> displayText.add("×")
                "/" -> displayText.add("÷")
                "+" -> displayText.add("+")
                "-" -> displayText.add("-")
                "." -> displayText.add(".")
            }
        }
    }


    fun addSymbol(symbol:String){
        if(!symbolused) {
            evalText.add(numberCreator.toString())
            numberCreator.clear()
            evalText.add(symbol)
            updateText(symbol)
            symbolused = true
        }
    }

    private fun clear(){
        evalText.clear()
        displayText.clear()
    }

    private fun delete(){
        evalText.removeLast()
    }

    private fun solve(){
        val expression = Expression(evalText.toString())
        evalText.toString()
    }
}