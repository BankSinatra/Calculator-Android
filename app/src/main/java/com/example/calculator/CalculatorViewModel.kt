package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CalculatorViewModel : ViewModel() {
    private var evalText: MutableList<String> = mutableListOf()
    private var displayText: MutableList<String> = mutableListOf()
    private var numberCreator: MutableList<String> = mutableListOf()
    private var symbolUsed:Boolean = false
    private lateinit var answer: String

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    private val _evaluatedText = MutableLiveData<String>()
    val evaluatedText: LiveData<String>
        get() = _evaluatedText



    private fun resetdisplay(){
        displayText.clear()
    }

    fun addNum(num:String){
        numberCreator.add(num)
        evalText.add(num)
        displayText.add(num)
        symbolUsed = false
        updateText(num)
    }

    private fun updateText(text: String){
            when(text){
                "*" -> displayText.add("×")
                "/" -> displayText.add("÷")
                "+" -> displayText.add("+")
                "-" -> displayText.add("-")
            }
            _inputText.value = displayText.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "")
    }


    fun addSymbol(symbol:String){
        if(!symbolUsed) {
            numberCreator.clear()
            evalText.add(symbol)
            updateText(symbol)
            symbolUsed = true
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