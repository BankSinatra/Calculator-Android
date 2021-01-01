package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CalculatorViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        binding.btn0.setOnClickListener {
            viewModel.addNum("0")
        }

        binding.btn1.setOnClickListener {
            viewModel.addNum("1")
        }

        binding.btn2.setOnClickListener {
            viewModel.addNum("2")
        }

        binding.btn3.setOnClickListener {
            viewModel.addNum("3")
        }

        binding.btn4.setOnClickListener {
            viewModel.addNum("4")
        }

        binding.btn5.setOnClickListener {
            viewModel.addNum("5")
        }

        binding.btn6.setOnClickListener {
            viewModel.addNum("6")
        }

        binding.btn7.setOnClickListener {
            viewModel.addNum("7")
        }

        binding.btn8.setOnClickListener {
            viewModel.addNum("8")
        }

        binding.btn9.setOnClickListener {
            viewModel.addNum("9")
        }

        binding.btnDecimal.setOnClickListener {
            viewModel.addNum(".")
        }

        //Symbol Buttons
        binding.btnPlus.setOnClickListener {
            viewModel.addSymbol("+")
        }
        binding.btnSubtract.setOnClickListener {
            viewModel.addSymbol("-")
        }
        binding.btnMultiplication.setOnClickListener {
            viewModel.addSymbol("*")
        }
        binding.btnDivide.setOnClickListener {
            viewModel.addSymbol("/")
        }

        //Number Manipulation Buttons
        binding.btnDelete.setOnClickListener {
            viewModel.delete()
        }

        binding.btnSolve.setOnClickListener {
            viewModel.solve()
        }

        binding.btnClear.setOnClickListener {
            viewModel.clear()
        }

        binding.btnAnswer.setOnClickListener {
            viewModel.answer()
        }

        //TextFields
        viewModel.inputText.observe(this, {input ->
            binding.inputLine.text = input
        })

        viewModel.evaluatedText.observe(this, {output ->
            binding.evalLine.text = output
        })

    }
}