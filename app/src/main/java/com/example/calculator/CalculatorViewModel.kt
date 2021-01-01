package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression


class CalculatorViewModel : ViewModel() {
    private var displayText: MutableList<String> = mutableListOf()
    //displayText is just what the user sees
    private var evalText: MutableList<String> = mutableListOf()
    //evalText does the actual under-the-hood calculations
    private var numberCreator: MutableList<String> = mutableListOf()
    //Creates the singular numbers
    private var numberList: MutableList<String> = mutableListOf()
    private var symbolList: MutableList<String> = mutableListOf()
    private var index1: Int = 0
    private var symbolUsed:Boolean = false
    private lateinit var answer: String
    private var ansUsed = false


    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    private val _evaluatedText = MutableLiveData<String>()
    val evaluatedText: LiveData<String>
        get() = _evaluatedText

    //This is the function for all number inputs (plus the decimal)
    fun addNum(num: String){
        displayText.add(num)
        if(numberCreator.size < 1) {
            numberList.add(index1, "")
        }
        numberCreator.add(num)
        numberList[index1] = displayList(numberCreator)
        evalText.add(num)
        symbolUsed = false
        updateText(num)
        evaluate()
    }

    private fun updateText(text: String = " "){
            when(text){
                "*" -> displayText.add("×")
                "/" -> displayText.add("÷")
                "+" -> displayText.add("+")
                "-" -> displayText.add("-")
                " " -> {
                    if (evalText.size > 0) {
                        evalText.removeLast()
                        displayText.removeLast()
                    }
                }
                "ans" -> displayText.add("ANS")
            }
            _inputText.value = displayList(displayText)
    }


    fun addSymbol(symbol: String){
        if(!symbolUsed) {
            numberCreator.clear()
            index1 ++
            symbolList.add(symbol)
            evalText.add(symbol)
            updateText(symbol)
            symbolUsed = true
        }
    }

    fun clear(){
        evalText.clear()
        displayText.clear()
        Log.i("size", "${evalText.size}")
        if (evalText.size >= 0){
            updateText()
        }
        evaluate(true)
    }

    fun delete(){
        if (evalText.size > 0){
            updateText()
        }
        evaluate()
    }

    private fun trimTrailingZero(value: String?): String? {
        return if (!value.isNullOrEmpty()) {
            if (value.indexOf(".") < 0) {
                value

            } else {
                value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
            }

        } else {
            value
        }
    }

    private fun evaluate(clear:Boolean = false){
        val e = Expression(
            displayList(evalText)
        )

        answer = e.calculate().toString()

        if(!e.calculate().isNaN() && numberList.size > 2 && symbolList.size > 1){
            _evaluatedText.value = trimTrailingZero(e.calculate().toString()).toString()
        }//^This is the answer preview^

        if (clear){
            _evaluatedText.value = ""
        }
    }

    fun answer(){
        if(answer.isNotBlank() && ansUsed){
            evalText.add("(${answer})")
            Log.i("answer", evalText.toString())
            updateText("ans")
            ansUsed = false
        }
    }

    fun solve(){
        answer = _evaluatedText.value.toString()
        _inputText.value = _evaluatedText.value
        _evaluatedText.value = ""
        displayText.clear()
        evalText.clear()
    }

    private fun displayList(list: MutableList<String>):String{
        return list.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(",", "")
            .replace(" ", "")
    }
}