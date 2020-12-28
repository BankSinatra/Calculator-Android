package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression


class CalculatorViewModel : ViewModel() {
    private var evalText: MutableList<String> = mutableListOf()
    private var displayText: MutableList<String> = mutableListOf()
    private var numberCreator: MutableList<String> = mutableListOf()
    private var symbolUsed:Boolean = false
    private lateinit var answer: String
    private var ansUsed = false


    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    private val _evaluatedText = MutableLiveData<String>()
    val evaluatedText: LiveData<String>
        get() = _evaluatedText


    fun addNum(num: String){
        numberCreator.add(num)
        evalText.add(num)
        displayText.add(num)
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
            _inputText.value = displayText.toString().replace("[", "").replace("]", "").replace(
                ",",
                ""
            ).replace(" ", "")
    }


    fun addSymbol(symbol: String){
        if(!symbolUsed) {
            numberCreator.clear()
            evalText.add(symbol)
            updateText(symbol)
            symbolUsed = true
            ansUsed = true
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
            evalText.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "")
        )

        answer = e.calculate().toString()
        if (clear){
            _evaluatedText.value = ""
        }
        if(!e.calculate().isNaN()){
            _evaluatedText.value = trimTrailingZero(e.calculate().toString()).toString()
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
}