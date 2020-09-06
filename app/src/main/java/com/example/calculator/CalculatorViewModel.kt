package com.example.calculator

import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException

class CalculatorViewModel : ViewModel() {
    private lateinit var evalText: MutableList<String>
    private lateinit var displayText: MutableList<String>
    private lateinit var numberCreator: MutableList<String>
    private var symbolused:Boolean = false
    private lateinit var answer: String

    init {

    }

    private fun resetdisplay(){
        displayText.clear()
    }

    private fun addNum(num:String){
        numberCreator.add(num)
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


    private fun addSymbol(symbol:String){
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
        evalText.toString()
    }
}