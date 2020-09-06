package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
            Log.i("number", "0")
        }

        binding.btn1.setOnClickListener {
            Log.i("number", "1")
        }

        binding.btn2.setOnClickListener {
            Log.i("number", "2")
        }

        binding.btn3.setOnClickListener {
            Log.i("number", "3")
        }

        binding.btn4.setOnClickListener {
            Log.i("number", "4")
        }

        binding.btn5.setOnClickListener {
            Log.i("number", "5")
        }

        binding.btn6.setOnClickListener {
            Log.i("number", "6")
        }

        binding.btn7.setOnClickListener {
            Log.i("number", "7")
        }

        binding.btn8.setOnClickListener {
            Log.i("number", "8")
        }

        binding.btn9.setOnClickListener {
            Log.i("number", "9")
        }


    }
}