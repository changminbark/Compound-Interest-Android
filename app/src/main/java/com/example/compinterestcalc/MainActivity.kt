package com.example.compinterestcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import androidx.core.widget.doAfterTextChanged
import com.example.compinterestcalc.databinding.ActivityMainBinding
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val principalInput get() = binding.principalInput
    private val yearInput get() = binding.yearInput
    private val interestBar get() = binding.rateBar
    private val interestText get() = binding.interestVal
    private val outputVal get() = binding.output



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        setUpListeners()

    }

    private fun setUpListeners() {
        binding.principalInput.doAfterTextChanged {
            calculateCompoundInterest()
        }
        binding.yearInput.doAfterTextChanged {
            calculateCompoundInterest()
        }
        binding.rateBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                calculateCompoundInterest()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun calculateCompoundInterest() {
        var principal = principalInput.text.toString().toDoubleOrNull() ?: 0.0
        var years = yearInput.text.toString().toIntOrNull() ?: 0
        var rate = interestBar.progress.toDouble() / 100.0

        var compoundInterest = principal * (1 + rate).pow(years) - principal

        interestText.text = interestBar.progress.toString() + "%"
        outputVal.text = String.format("%.2f", principal + compoundInterest)
    }

}